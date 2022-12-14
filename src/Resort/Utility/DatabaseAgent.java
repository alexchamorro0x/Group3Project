package Resort.Utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.Scene;

/**
 * Database CRUD(Create, Retrieve, Update, Delete) class. Handles the methods for making connections
 * to the database and manipulating the data in it.
 */
public class DatabaseAgent {

  // prepare string for database and driver
  private static final String JDBCDRIVER = "org.h2.Driver";
  private static final String DBURL = "jdbc:h2:.\\res\\ResortDB";
  private static final String USER = "";
  private static final String PASS = "";

  /**
   * Makes a connection to the database and returns it to the caller.
   *
   * @return Connection to the database
   */
  public static Connection getConnection() {
    try {
      // register JDBC driver
      Class.forName(JDBCDRIVER);
      // get a connection
      Connection conn = DriverManager.getConnection(DBURL, USER, PASS);
      // return the connection
      return conn;
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException("Could not connect to database", e);
    }
  }



  /**
   * Creates a connection to the database and uses it to add a new account to the ACCOUNTS table.
   *
   * @param firstName
   * @param lastName
   * @param password
   * @return
   */
  public static boolean addUser(String username, String firstName, String lastName, String password,
      String email, String address, String state, String zipCode, String creditCardNumber,
      String cvv, String expMonth, String expYear) {
    Connection connection = getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO PUBLIC.ACCOUNTs(FIRSTNAME, LASTNAME, PASSWORD, USERNAME, EMAIL, ADDRESS, "
              + "STATE, ZIPCODE, CREDITCARDNUMBER, CVV, CCEXPMONTH, CCEXPYEAR) values (?,?,?,?,?,?,?,?,?,?,?,?);");
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setString(3, password);
      ps.setString(4, username);
      ps.setString(5, email);
      ps.setString(6, address);
      ps.setString(7, state);
      ps.setString(8, zipCode);
      ps.setString(9, creditCardNumber);
      ps.setString(10, cvv);
      ps.setString(11, expMonth);
      ps.setString(12, expYear);
      int returnCode = ps.executeUpdate();

      //close the prepared statement
      ps.close();

      if (returnCode == 1) {
        return true;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // close the connection
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    // return false if update failed
    return false;

  }

  /**
   * takes the username name and password the user enters and checks if it exists in the database
   *
   * @param username
   * @param password
   * @return
   */
  public static boolean checkLoginInformation(String username, String password) {
    //establish a connection to the database
    Connection connection = getConnection();
    String databasePassword = "";

    try {
      //Read first names and passwords into result set
      String sql = ("SELECT (PASSWORD) FROM ACCOUNTS WHERE USERNAME = '" + username + "'");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      //Loop through database and read all the values into accounts
      while (resultSet.next()) {
        databasePassword = resultSet.getString("PASSWORD");
      }
      //close the statement and the result set created
      statement.close();
      resultSet.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
//      try {
//        if (connection != null) {
//          connection.close();
//        }
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }

      if (databasePassword.equals(password)) {
        return true;
      } else {
        return false;
    }

  }

  public static boolean checkIfManager(String username) {
    //establish a connection to the database
    Connection connection = getConnection();
    String sql = "select ISMANAGER from ACCOUNTS where USERNAME=?;";
    // variable to hold manager status
    Boolean isManager = false;
    try {
      // make the prepared statement
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      // set username in prepared statement
      preparedStatement.setString(1, username);

      ResultSet resultSet = preparedStatement.executeQuery();

      //Loop through database and read all the values into accounts
      while (resultSet.next()) {
        isManager = resultSet.getBoolean("ISMANAGER");
      }
      //close the statement and the result set created
      preparedStatement.close();
      resultSet.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return isManager;

  }

  /**
   * method gets the account information for a specific username in the database
   * @return AccountInformation
   */
  public static AccountInformation getAccountInformation(String username) {
    //establish a connection to the database
    Connection connection = getConnection();
    AccountInformation returnAccountInformation = new AccountInformation();

    try {
      //Read first names and passwords into result set
      String sql = ("SELECT * FROM PUBLIC.ACCOUNTS WHERE USERNAME = '" + username + "'");
      Statement statement = connection.createStatement();
      ResultSet resultSetForAccount = statement.executeQuery(sql);


      //Loop through and read all the values into returnAccountInformation
      while (resultSetForAccount.next()) {

        returnAccountInformation.setUserName(resultSetForAccount.getString("USERNAME"));
        returnAccountInformation.setFirstName(resultSetForAccount.getString("FIRSTNAME"));
        returnAccountInformation.setLastName(resultSetForAccount.getString("LASTNAME"));
        returnAccountInformation.setEmail(resultSetForAccount.getString("EMAIL"));
        returnAccountInformation.setAddress(resultSetForAccount.getString("ADDRESS"));
        returnAccountInformation.setState(resultSetForAccount.getString("STATE"));
        returnAccountInformation.setZipCode(resultSetForAccount.getString("ZIPCODE"));
        returnAccountInformation.setUserId(resultSetForAccount.getInt("ACCOUNTID"));
        returnAccountInformation.setCreditCardNumber(resultSetForAccount.getString("CREDITCARDNUMBER"));
        returnAccountInformation.setCvv(resultSetForAccount.getString("CVV"));
        returnAccountInformation.setPassWord(resultSetForAccount.getString("PASSWORD"));
        returnAccountInformation.setCcExpMonth(resultSetForAccount.getString("CCEXPMONTH"));
        returnAccountInformation.setCcExpYear(resultSetForAccount.getString("CCEXPYEAR"));
        returnAccountInformation.setManager(resultSetForAccount.getBoolean("ISMANAGER"));

      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      }

    // return the account information class
    return returnAccountInformation;
    }

  /**
   * Returns a list of the available room for a specified data range and room type
    * @param checkIn Checkin date for the search.
   * @param checkOut Check out date for the search.
   * @param roomType The room type for the search
   * @return An ArrayList of available rooms.
   * @throws SQLException
   */
  public static ArrayList<AvailableRoom> getAvailableRooms(String checkIn, String checkOut,
      String roomType)
      throws SQLException {
    ArrayList<AvailableRoom> returnResults = new ArrayList<AvailableRoom>();

    // connect to database
    Connection connection = getConnection();

    try {
        // if room type is all execute this prepared statement
      PreparedStatement preparedStatement;
        if (roomType == "all") {
          preparedStatement = connection.prepareStatement(
              "select ROOMNUMBER, ROOMTYPE from ROOMS where ROOMNUMBER not in"
                  + "((select RESERVATIONS.ROOMNUMBER from RESERVATIONS where (CHECKIN < ?))"
                  + "intersect"
                  + "(select RESERVATIONS.ROOMNUMBER from RESERVATIONS where (CHECKOUT > ?)));"
          );
          preparedStatement.setString(1, checkOut);
          preparedStatement.setString(2, checkIn);
        } else {
          preparedStatement = connection.prepareStatement(
              "select ROOMNUMBER, ROOMTYPE from ROOMS where ROOMNUMBER not in"
                  + "((select RESERVATIONS.ROOMNUMBER from RESERVATIONS where (CHECKIN < ?))"
                  + "intersect"
                  + "(select RESERVATIONS.ROOMNUMBER from RESERVATIONS where (CHECKOUT > ?)))"
                  + "AND ROOMTYPE = ?;"
          );
          preparedStatement.setString(1, checkOut);
          preparedStatement.setString(2, checkIn);
          preparedStatement.setString(3, roomType);
        }

        ResultSet searchResult = preparedStatement.executeQuery();

        while (searchResult.next()) {
          AvailableRoom queryResult = new AvailableRoom(searchResult.getString("ROOMTYPE"),
              searchResult.getInt("ROOMNUMBER"));
          returnResults.add(queryResult);
        }

        // close statements
        preparedStatement.close();
        searchResult.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return returnResults;

  }

  /**
   * Inserts a reservation into the database reservation table.
   * @param userName String Booking users username.
   * @param roomNumber Int containing room number for the booking.
   * @param checkin Date Checkin date for the booking.
   * @param checkOut Date Check out date for the booking.
   */
  public static void insertIntoReservations(String userName, int roomNumber, Date checkin, Date checkOut) {

    AccountInformation accountInformation = getAccountInformation(userName);
      Connection connection = getConnection();
      try {
        PreparedStatement ps = connection.prepareStatement(
            "INSERT into RESERVATIONS(USERID, ROOMNUMBER, CHECKIN, CHECKOUT) "
                + "VALUES ( ?,?,?,? );");
        ps.setInt(1, accountInformation.getUserId());
        ps.setInt(2, roomNumber);
        ps.setDate(3, checkin);
        ps.setDate(4, checkOut);
        ps.executeUpdate();

        //close the prepared statement
        ps.close();

      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        // close the connection
        try {
          if (connection != null) {
            connection.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
  }

  /**
   * Inserts a reservation into the database reservation table. Overloaded to take a string for the roomnumber
   * @param userName String Booking users username.
   * @param roomNumber String containing room number for the booking.
   * @param checkin Date Checkin date for the booking.
   * @param checkOut Date Check out date for the booking.
   */
  public static void insertIntoReservations(String userName, String roomNumber, Date checkin, Date checkOut) {

    AccountInformation accountInformation = getAccountInformation(userName);
    Connection connection = getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT into RESERVATIONS(USERID, ROOMNUMBER, CHECKIN, CHECKOUT) "
              + "VALUES ( ?,?,?,? );");
      ps.setInt(1, accountInformation.getUserId());
      ps.setString(2, roomNumber);
      ps.setDate(3, checkin);
      ps.setDate(4, checkOut);
      ps.executeUpdate();

      //close the prepared statement
      ps.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // close the connection
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Gets all of the bookings from the database.
   * @return An ArrayList of all the bookings in the database.
   * @throws SQLException
   */
  public static ArrayList<Booking> getAllBookings() throws SQLException {
    ArrayList<Booking> allBookings = new ArrayList<>();

    Connection connection = getConnection();

    try {
      PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement("SELECT USERID, USERNAME, RESERVATIONS.ROOMNUMBER, "
          + "CHECKIN, CHECKOUT, RESERVATIONID, ROOMTYPE from RESERVATIONS inner join ACCOUNTS on RESERVATIONS.USERID = ACCOUNTS.ACCOUNTID inner join ROOMS on RESERVATIONS.ROOMNUMBER = ROOMS.ROOMNUMBER");

      ResultSet searchResult = preparedStatement.executeQuery();

      while (searchResult.next()) {
        String userName = searchResult.getString("USERNAME");
        Date checkOut = searchResult.getDate("CHECKOUT");
        Date checkIn = searchResult.getDate("CHECKIN");
        String roomType = searchResult.getString("ROOMTYPE");
        int roomNumber = searchResult.getInt("ROOMNUMBER");
        int reservationId = searchResult.getInt("RESERVATIONID");
        AccountInformation accountInformation = getAccountInformation(userName);
        Booking booking = new Booking(checkIn.toString(), checkOut.toString(), roomType,
            String.valueOf(roomNumber), String.valueOf(reservationId), accountInformation);
        allBookings.add(booking);
      }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return allBookings;
  }

  /**
   * Gets a list of the bookings for a specific user.
   * @param userName String contains the users username for teh search.
   * @return An arraylist of bookings.
   */
  public static ArrayList<Booking> getUserBookings(String userName) {
    AccountInformation accountInformation = getAccountInformation(userName);
    ArrayList<Booking> returnResults = new ArrayList<Booking>();

    // connect to database
    Connection connection = getConnection();

    try {
      // if room type is all execute this prepared statement
      PreparedStatement preparedStatement;

      preparedStatement = connection.prepareStatement(
          "SELECT USERID, RESERVATIONS.ROOMNUMBER, CHECKIN, CHECKOUT, RESERVATIONID, ROOMTYPE from RESERVATIONS inner join ROOMS on RESERVATIONS.ROOMNUMBER = ROOMS.ROOMNUMBER"
              + " WHERE USERID = ?;"
      );


      preparedStatement.setInt(1, accountInformation.getUserId());

      ResultSet searchResult = preparedStatement.executeQuery();

      while (searchResult.next()) {
        Date checkOut = searchResult.getDate("CHECKOUT");
        Date checkIn = searchResult.getDate("CHECKIN");
        String roomType = searchResult.getString("ROOMTYPE");
        int roomNumber = searchResult.getInt("ROOMNUMBER");
        int reservationId = searchResult.getInt("RESERVATIONID");
        Booking userBooking = new Booking(checkIn.toString(), checkOut.toString(), roomType,
            String.valueOf(roomNumber), String.valueOf(reservationId), accountInformation);
        returnResults.add(userBooking);
      }

      // close statements
      preparedStatement.close();
      searchResult.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return returnResults;
  }

  /**
   * Removes the specified booking from the database.
   * @param reservationID Int containg the reservationID for the booking to be deleted.
   */
  public static void deleteReservation(int reservationID) {
    //'DELETE from RESERVATIONS where RESERVATIONID = ?'
    Connection connection = getConnection();
    try {
      PreparedStatement preparedStatement;

      preparedStatement = connection.prepareStatement("DELETE from RESERVATIONS where "
          + "RESERVATIONID = ?");

      preparedStatement.setInt(1, reservationID);
      preparedStatement.execute();
      preparedStatement.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  /**
   * Deletes a specified account from the database.
   * @param accountId int containing the accountID to be deleted.
   */
  public static void deleteAccount(int accountId) {
    Connection connection = getConnection();
    try {
      PreparedStatement preparedStatement;

      preparedStatement = connection.prepareStatement("DELETE from ACCOUNTS where "
          + "ACCOUNTID = ?");

      preparedStatement.setInt(1, accountId);
      preparedStatement.execute();
      preparedStatement.close();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  }

  /**
   * Gets a list of all of the users in the database.
   * @return Returns an arraylist of all of the users in the database.
   * @throws SQLException
   */
  public static ArrayList<User> getUsers() throws SQLException {
    Connection connection = getConnection();

      ArrayList<User> userList = new ArrayList<>();

      PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement("SELECT USERNAME from ACCOUNTS");

      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()) {
        String userName = resultSet.getString("USERNAME");
        userList.add(new User(userName));
      }

      return userList;
  }

  /**
   * Updates an account in the database.
   * @param accountInformation AccountInformation clas containing the information to make the
   *                           database update.
   * @throws SQLException
   */
  public static void updateAccount(AccountInformation accountInformation) throws SQLException {
    Connection connection = getConnection();

    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ACCOUNTS SET "
        + "FIRSTNAME=?, LASTNAME=?, PASSWORD=?, USERNAME=?, EMAIL=?, ADDRESS=?, STATE=?, ZIPCODE=?, "
        + "CREDITCARDNUMBER=?, CVV=?, CCEXPMONTH=?, CCEXPYEAR=?, ISMANAGER=? where ACCOUNTID=?;");

    preparedStatement.setString(1, accountInformation.getFirstName());
    preparedStatement.setString(2, accountInformation.getLastName());
    preparedStatement.setString(3, accountInformation.getPassWord());
    preparedStatement.setString(4, accountInformation.getUserName());
    preparedStatement.setString(5, accountInformation.getEmail());
    preparedStatement.setString(6, accountInformation.getAddress());
    preparedStatement.setString(7, accountInformation.getState());
    preparedStatement.setString(8, accountInformation.getZipCode());
    preparedStatement.setString(9, accountInformation.getCreditCardNumber());
    preparedStatement.setString(10, accountInformation.getCvv());
    preparedStatement.setString(11, accountInformation.getCcExpMonth());
    preparedStatement.setString(12, accountInformation.getCcExpYear());
    preparedStatement.setBoolean(13, accountInformation.isManager());

    preparedStatement.setInt(14, accountInformation.getUserId());


    preparedStatement.execute();

    // close connections
    connection.close();
    preparedStatement.close();
  }

}








