package Resort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
  public boolean addUser(String firstName, String lastName, String password) {
    Connection connection = ConnectionFactory.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO PUBLIC.ACCOUNTs(FIRSTNAME, LASTNAME, PASSWORD) values (?, ?, ?);");
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setString(3, password);
      int returnCode = ps.executeUpdate();

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
   * takes the first name and password the user enters and checks if it exists in the database
   *
   * @param firstName
   * @param password
   * @return
   */
  public boolean checkLoginInformation(String firstName, String password) {
    //establish a connection to the database
    Connection loginConnection = ConnectionFactory.getConnection();

    //test account to compare with all accounts
    LoginAccount userAccount = new LoginAccount(firstName, password);
    //List of all accounts from the database
    ArrayList<LoginAccount> accounts = new ArrayList<LoginAccount>();

    try {
      //Read first names and passwords into result set
      String sql = ("SELECT PASSWORD, FIRSTNAME FROM RESORTDB.PUBLIC.ACCOUNTS");
      Statement statement = loginConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      //Loop through database and read all the values into accounts
      while (resultSet.next()) {
        LoginAccount checkAccount = new LoginAccount();
        checkAccount.setFirstName(resultSet.getString("FIRSTNAME"));
        checkAccount.setPassword(resultSet.getString("PASSWORD"));
        accounts.add(checkAccount);
      }
      System.out.println("Result Set: " + accounts.toString());
      boolean contains = accounts.contains(userAccount);

      //if the test account is found it returns true
      if (contains) {
        System.out.println(true);
        return true;
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      // close the connection
      try {
        if (loginConnection != null) {
          loginConnection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}





