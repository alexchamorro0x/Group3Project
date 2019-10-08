package Resort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Database CRUD(Create, Retrieve, Update, Delete) class. Handles the methods for making connections
 * to the database and manipulating the data in it.
 */
public class DatabaseCrud {

  /**
   * Creates a connection to the database and uses it to add a new account to the ACCOUNTS table.
   * @param firstName
   * @param lastName
   * @param password
   * @return
   */
  public boolean addUser(String firstName, String lastName, String password) {
    Connection connection = ConnectionFactory.getConnection();
    try {
      PreparedStatement ps = connection.prepareStatement("INSERT INTO PUBLIC.ACCOUNTs(FIRSTNAME, LASTNAME, PASSWORD) values (?, ?, ?);");
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setString(3, password);
      int returnCode = ps.executeUpdate();

      if(returnCode == 1) {
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


}
