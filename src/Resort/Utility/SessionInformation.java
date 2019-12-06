package Resort.Utility;

/**
 * This class stores the information used for the logged in user as the navigate the application.
 */
public class SessionInformation {
  private String userName;
  private boolean isManager;

  /**
   * Gets the userName
   * @return String containing the users username
   */
  public String getUserName() {
    return userName;
  }


  /**
   * Sets a users username
   * @param userName String containing a username.
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Returns a boolean of true if the user is a manager.
   * @return boolean equal to true is the user is a manager.
   */
  public boolean isManager() {
    return isManager;
  }

  /**
   * Sets the users manager access.
   * @param manager boolean containing manager status.
   */
  public void setManager(boolean manager) {
    isManager = manager;
  }
}

