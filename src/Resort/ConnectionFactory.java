package Resort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that handles creating connections to the database.
 */
public class ConnectionFactory {

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

}
