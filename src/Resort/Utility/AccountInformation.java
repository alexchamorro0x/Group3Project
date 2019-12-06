package Resort.Utility;

/**
 * Class containg the users account information.
 */
public class AccountInformation {
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String address;
  private String state;
  private String zipCode;
  private String creditCardNumber;
  private String cvv;
  private String passWord;
  private String ccExpMonth;
  private String ccExpYear;
  private boolean isManager;

  /**
   * Gets a string containing the users ccv number
   * @return String containing users ccv
   */
  public String getCvv() {
    return cvv;
  }

  /**
   * Sets a string containing the users ccv number
   * @param cvv String containing users ccv
   */
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  /**
   * Gets a string containing the users password
   * @return String containing users password
   */
  public String getPassWord() {
    return passWord;
  }

  /**
   * Sets a string containing the users password
   * @param passWord String containing users password
   */
  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  private int userId;

  /**
   * Gets a string containing the users cred card number
   * @return String containing users credit card
   */
  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  /**
   * Sets a string containing the users cred card number
   * @param creditCardNumber String containing users credit card
   */
  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  /**
   * Gets a string containing the users first name
   * @return String containing users first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets a string containing the users first name
   * @param firstName String containing users first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets a string containing the users last name
   * @return String containing users last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets a string containing the users last name
   * @param lastName String containing users last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets a string containing the users user name
   * @return String containing users user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets a string containing the users user name
   * @param userName String containing users user name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets a string containing the users email address
   * @return String containing users email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets a string containing the users email address
   * @param email String containing users email address
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets a string containing the users street address
   * @return String containing users street address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets a string containing the users street address
   * @param  address String containing users street address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets a string containing the users state
   * @return String containing users state
   */
  public String getState() {
    return state;
  }

  /**
   * Gets a string containing the users state
   * @param state String containing users state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets a string containing the users zip code
   * @return String containing users zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets a string containing the users zip code
   * @param  zipCode String containing users zip code
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets a int containing the users user id
   * @return int containing users user id
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Sets a string containing the users user id
   * @param userId String containing users user id
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Gets a string containing the users credit card expiration month
   * @return String containing users credit card expiration month
   */
  public String getCcExpMonth() {
    return ccExpMonth;
  }

  /**
   * Sets a string containing the users credit card expiration month
   * @param ccExpMonth String containing users credit card expiration month
   */
  public void setCcExpMonth(String ccExpMonth) {
    this.ccExpMonth = ccExpMonth;
  }

  /**
   * Gets a string containing the users credit card expiration year
   * @return String containing users credit card expiration year
   */
  public String getCcExpYear() {
    return ccExpYear;
  }

  /**
   * Sets a string containing the users credit card expiration year
   * @param ccExpYear String containing users credit card expiration year
   */
  public void setCcExpYear(String ccExpYear) {
    this.ccExpYear = ccExpYear;
  }

  /**
   * Sets the boolean flagging the users account as a manager
   * @return boolean containg the users manager status.
   */
  public boolean isManager() {
    return isManager;
  }

  /**
   * Gets the boolean flagging the users account as a manager
   * @param manager boolean containg the users manager status.
   */
  public void setManager(boolean manager) {
    isManager = manager;
  }
}
