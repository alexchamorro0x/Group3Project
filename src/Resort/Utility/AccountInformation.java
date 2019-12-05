package Resort.Utility;

/**
 * This class stores a users account information used when retrieving or updating information
 * in the Accounts table of the database.
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
  private int userId;


  /**
   * Gets the Cvv number for the users creditcard.
   * @return String containing the ccv number for the users creditcard.
   */
  public String getCvv() {
    return cvv;
  }

  /**
   * Sets the Cvv number for the users creditcard.
   * @param cvv String containing the ccv number for the users creditcard.
   */
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  /**
   * Gets the users password.
   * @return String containing the users password.
   */
  public String getPassWord() {
    return passWord;
  }

  /**
   * Sets the users password.
   * @param passWord String containing the users password.
   */
  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  /**
   * Gets the users creditcard number.
   * @return String containing the users creditcard number.
   */
  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  /**
   * Sets the users creditcard number.
   * @param creditCardNumber String containing the users creditcard number.
   */
  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  /**
   * Gets the users first name.
   * @return String containing the users first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the users fire name.
   * @param firstName String containing the users first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the users last name.
   * @return String containing the users last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the users last name.
   * @param lastName String containing users last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * gets the username for the user.
   * @return String containing the users username.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the users userName.
   * @param userName String containing the users username.
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets the users email address.
   * @return String containing the users email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the users email
   * @param email String containing the users email address.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the street address for the user.
   * @return String containing the users street address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the street address for the user.
   * @param address String containing the users street address.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the state for the users address
   * @return String containing the users state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state for the users address.
   * @param state String for the state for the users address.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the users zipcode.
   * @return String containing the users zip code.
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Gets the users zipcode stored in the database.
   * @param zipCode String containing users zip code.
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets the users ID from the database.
   * @return int containing users id from the Accounts table.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Sets the users id from the database.
   * @param userId int userid from the Accounts table of the database.
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Gets the users creditcard expiration month
   * @return String containing creditcard expiration month.
   */
  public String getCcExpMonth() {
    return ccExpMonth;
  }

  /**
   * Set the exp month for the users credit card.
   * @param ccExpMonth month card expires.
   */
  public void setCcExpMonth(String ccExpMonth) {
    this.ccExpMonth = ccExpMonth;
  }

  /**
   * Gets the users creditcard expiration year
   * @return String containing creditcard expiration year.
   */
  public String getCcExpYear() {
    return ccExpYear;
  }

  /**
   * Set the exp year for the users credit card.
   * @param ccExpYear year card expires.
   */
  public void setCcExpYear(String ccExpYear) {
    this.ccExpYear = ccExpYear;
  }

  /**
   * Get the manager flag, will return 1 if user is a manager.
   * @return is manager boolean
   */
  public boolean isManager() {
    return isManager;
  }

  /**
   * Set if the specified account is a manager.
   * @param manager manager flag set if the user is a manager.
   */
  public void setManager(boolean manager) {
    isManager = manager;
  }
}
