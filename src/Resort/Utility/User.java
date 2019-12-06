package Resort.Utility;

/**
 * This class is used to store user information.
 */
public class User {
  String userName;

  /**
   * Constructor for the User class
   * @param userName String containing the users username
   */
  public User(String userName) {
    this.userName = userName;
  }

  /**
   * Gets the username for the User class
   * @return String containing the users username
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the username for the User class
   * @param userName String containing the users username
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }
}
