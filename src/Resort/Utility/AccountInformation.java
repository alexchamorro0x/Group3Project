package Resort.Utility;

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

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  private int userId;

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getCcExpMonth() {
    return ccExpMonth;
  }

  public void setCcExpMonth(String ccExpMonth) {
    this.ccExpMonth = ccExpMonth;
  }

  public String getCcExpYear() {
    return ccExpYear;
  }

  public void setCcExpYear(String ccExpYear) {
    this.ccExpYear = ccExpYear;
  }

  public boolean isManager() {
    return isManager;
  }

  public void setManager(boolean manager) {
    isManager = manager;
  }
}
