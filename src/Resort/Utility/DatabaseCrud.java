package Resort.Utility;

import java.sql.Connection;
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
public class DatabaseCrud {

  /**
   * Creates a connection to the database and uses it to add a new account to the ACCOUNTS table.
   *
   * @param firstName
   * @param lastName
   * @param password
   * @return
   */
  public boolean addUser(String username, String firstName, String lastName, String password,
      String email, String address, String state, String zipCode, String creditCardNumber,
      String cvv) {
    Connection connection = ConnectionFactory.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO PUBLIC.ACCOUNTs(FIRSTNAME, LASTNAME, PASSWORD, USERNAME, EMAIL, ADDRESS, "
              + "STATE, ZIPCODE, CREDITCARDNUMBER, CVV) values (?,?,?,?,?,?,?,?,?,?);");
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
  public boolean checkLoginInformation(String username, String password) {
    //establish a connection to the database
    Connection connection = ConnectionFactory.getConnection();
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

  /**
   * method gets the account information for a specific username in the database
   * @return AccountInformation
   */
  public AccountInformation getAccountInformation(String username) {
    //establish a connection to the database
    Connection connection = ConnectionFactory.getConnection();
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



      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      }

    // return the account information class
    return returnAccountInformation;
    }

}








