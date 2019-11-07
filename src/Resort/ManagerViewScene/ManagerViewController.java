package Resort.ManagerViewScene;

import Resort.EditAccountScene.EditAccountController;
import Resort.TitleScene.TitleController;
import Resort.Utility.AccountInformation;
import Resort.Utility.AvailableRoom;
import Resort.Utility.Booking;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import Resort.Utility.User;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerViewController {

    @FXML
    private AnchorPane Name;

    @FXML
    private TableView<Booking> tvBooked;

    @FXML
    private TableColumn<?, ?> tcFirstName;

    @FXML
    private TableColumn<?, ?> tcLastName;

    @FXML
    private TableColumn<?, ?> tcCheckIn;

    @FXML
    private TableColumn<?, ?> tcCheckOut;

    @FXML
    private TableColumn<?, ?> tcRoomNumberBooked;

    @FXML
    private TableColumn<?, ?> tcRoomTypeBooked;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblState;

    @FXML
    private Label lblZipCode;

    @FXML
    private Label lblCreditCard;

    @FXML
    private TableView<AvailableRoom> tvVacant;

    @FXML
    private TableColumn<?, ?> tcRoomNumberVacant;

    @FXML
    private TableColumn<?, ?> tcRoomTypeVacant;

    @FXML
    private DatePicker dpCheckInBooked;

    @FXML
    private DatePicker dpCheckOutBooked;

    @FXML
    private DatePicker dpCheckInVacant;

    @FXML
    private DatePicker dpCheckOutVacant;

    @FXML
    private Button btnCancelBooking;

    @FXML
    private TableView<User> tvUsers;

    @FXML
    private TableColumn<?, ?> tcUsersUserName;

    // String to hold our username if logged in and if user is a manager
    private SessionInformation sessionInformation = new SessionInformation();

    // setter for session information
    public void setSessionInformation(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @FXML
    public void btnClickedHome(ActionEvent actionEvent) throws IOException {
        //get a reference to the window we are in
        Stage window = (Stage)  lblFirstName.getScene().getWindow();

        // declare and initialize a loader for the FXML scene we are going to
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../TitleScene/Title.fxml"));

        // create a parent class with our loader pointing at the new scene
        Parent title = loader.load();

        // get controller for Title page
        TitleController titleController = loader.getController();
        titleController.setSessionInformation(sessionInformation);
        // make the new scene we are going to
        Scene titleScene = new Scene(title);

        // initiate the scene change (no need to make changes to controller)
        window.setScene(titleScene);
    }

    // Variables to hold the managers selection of a booking and a potential available room to change to
    Booking selectedBooking;
    AvailableRoom selectedVacancy;

    /**
     * Method called to initialize the manager view scene
     */
    public void initialize() throws SQLException {
        // Associate Booked table with fields from Booking
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcRoomNumberBooked.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tcCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        tcCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        tcRoomTypeBooked.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        // Associate Vacant table with fields from AvailableRoom
        tcRoomNumberVacant.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tcRoomTypeVacant.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        // add a listener so that selecting roms in the booked table displays the users account info
        // as well as alternative rooms they could switch to
        tvBooked.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Booking>() {
                @Override
                public void changed(ObservableValue<? extends Booking> observable, Booking oldValue,
                    Booking newValue) {
                    if (newValue != null) {
                        updateDisplayedAccount(observable.getValue().getAccountInformation().getUserName());
                        // set the vacant room table to display alternative rooms that could be booked instead
                        dpCheckInVacant
                            .setValue(LocalDate.parse(observable.getValue().getCheckIn()));
                        dpCheckOutVacant
                            .setValue(LocalDate.parse(observable.getValue().getCheckOut()));
                        // set class variable in case a change of room is to be made
                        selectedBooking = observable.getValue();

                        try {
                            updateVacancySearch();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        // add a listener so that selections in the vacant table are candidates for a room swap
        tvVacant.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<AvailableRoom>() {
                @Override
                public void changed(ObservableValue<? extends AvailableRoom> observable,
                    AvailableRoom oldValue, AvailableRoom newValue) {
                    // set class variable in case a change of room is to be made
                    selectedVacancy = observable.getValue();
                }
            });

        // Update the booked table
        updateBookings();

        // Associate fields from user class to Users table view
        tcUsersUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));


        // Add a listener for user table to show booking for only the selected user and show the
        // account information for the user's account
        tvUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue,
                User newValue) {
                try {
                    // Only update if the new value is not null (Otherwise a crash will happen)
                    if(newValue != null) {
                        // Update the Booked table to display only the users bookings
                        updateBookings(observable.getValue().getUserName());
                        // Display the users information on the right side of teh screen
                        updateDisplayedAccount(observable.getValue().getUserName());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // fill the user table with usernames
        updateUserTable();

    }

    private void updateUserTable() throws SQLException {
        // clear the table
        tvUsers.getItems().clear();
        // Populate table with usernames
        for(User user: DatabaseAgent.getUsers()) {
            tvUsers.getItems().add(user);
        }
    }


    private void updateBookings() throws SQLException {
        // set selected booking to null
        selectedBooking = null;

        // clear the table for each change
        tvBooked.getItems().clear();
        //Fill bookings table
        for(Booking booking : DatabaseAgent.getAllBookings()) {
            tvBooked.getItems().add(booking);

        }
    }

    /**
     * Overloaded method for updating the booked table if a specific user is selected
     * @param userName
     */
    private void updateBookings(String userName) throws SQLException {
        selectedBooking = null;

        // clear the table for each change
        tvBooked.getItems().clear();
        //Fill bookings table
        for(Booking booking : DatabaseAgent.getUserBookings(userName)) {
            tvBooked.getItems().add(booking);

        }
    }

    private void updateVacancySearch() throws SQLException {
        // Clear the table for each search
        tvVacant.getItems().clear();
        ArrayList<AvailableRoom> availableRooms = DatabaseAgent.getAvailableRooms(
            dpCheckInVacant.getValue().toString(),
            dpCheckOutVacant.getValue().toString(),
            "all");

        for(AvailableRoom availableRoom : availableRooms) {
            tvVacant.getItems().add(availableRoom);
        }

    }

    public void updateDisplayedAccount(String userName) {
        AccountInformation displayAccount = DatabaseAgent.getAccountInformation(userName);
        // Set the information on the right side of scene with the selected booking's account information
        lblFirstName.setText(displayAccount.getFirstName());
        lblLastName.setText(displayAccount.getLastName());
        lblUserName.setText(displayAccount.getUserName());
        lblAddress.setText(displayAccount.getAddress());
        lblState.setText(displayAccount.getState());
        lblZipCode.setText(displayAccount.getZipCode());
        lblEmail.setText(displayAccount.getEmail());
        lblCreditCard.setText(displayAccount.getCreditCardNumber());

    }

    @FXML
    void clickSwapBooking(ActionEvent event) throws SQLException {
        if(selectedBooking != null && selectedVacancy != null) {
            DatabaseAgent.deleteReservation(Integer.parseInt(selectedBooking.getBookingId()));
            DatabaseAgent.insertIntoReservations(selectedBooking.getAccountInformation().getUserName(),
                selectedVacancy.getRoomNumber(), Date.valueOf(dpCheckInVacant.getValue()),
                Date.valueOf(dpCheckOutVacant.getValue()));

            // save index of booking so the new booking can be selected
            int bookingIndex = tvBooked.getSelectionModel().getSelectedIndex();

            // update the booked and vacant tables as well as the displayed user information

            updateBookings();

            // select previous index from booking table
            tvBooked.getSelectionModel().select(bookingIndex);

        }
    }

    @FXML
    void updateVacancy(ActionEvent event) throws SQLException {
        if(dpCheckInVacant.getValue() != null && dpCheckOutVacant.getValue() != null) {
            updateVacancySearch();
        }
    }

    @FXML
    void clickViewAllBookings(ActionEvent event) throws SQLException {
        tvUsers.getSelectionModel().clearSelection();
        updateBookings();
    }

    @FXML
    void clickCancelBooking(ActionEvent event) throws SQLException {
        DatabaseAgent.deleteReservation(Integer.parseInt(selectedBooking.getBookingId()));
        updateBookings();
    }

    @FXML
    void clickDeleteUser(ActionEvent event) throws SQLException {
        // Get the selected user from the Users table
        AccountInformation selectedAccount = DatabaseAgent.getAccountInformation(
            tvUsers.getSelectionModel().getSelectedItem().getUserName());
        // Delete the user from the database
        DatabaseAgent.deleteAccount(selectedAccount.getUserId());
        // Update the user table
        updateUserTable();

    }

    @FXML
    void clickChangeAccountInfo(ActionEvent event) throws IOException {
        // Only change scenes if a user is selected in users table
        if (tvUsers.getSelectionModel().getSelectedItem() != null) {
            Stage window = (Stage) lblFirstName.getScene().getWindow();

            // declare and initialize a loader for the FXML scene we are going to
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../EditAccountScene/EditAccount.fxml"));

            // create a parent class with our loader pointing at the new scene
            Parent editScene = loader.load();

            // get controller for Title page
            EditAccountController editAccountController = loader.getController();

            editAccountController.setSessionInformation(sessionInformation);
            editAccountController.setUserNameForManagerEdit(
                tvUsers.getSelectionModel().getSelectedItem().getUserName());

            // make the new scene we are going to
            Scene editAccountScene = new Scene(editScene);

            // initiate the scene change (no need to make changes to controller)
            window.setScene(editAccountScene);
        }
    }


}
