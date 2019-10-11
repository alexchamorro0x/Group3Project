package Resort;

import java.util.Objects;

public class LoginAccount {

  private String firstName;
  private String password;

  public LoginAccount(String firstName, String password) {
    this.firstName = firstName;
    this.password = password;
  }

  public LoginAccount() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginAccount that = (LoginAccount) o;
    return Objects.equals(firstName, that.firstName)
        && Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, password);
  }

  @Override
  public String toString() {
    return "LoginAccount{" + "firstName='" + firstName + '\''
        + ", password='" + password + '\''
        + '}';
  }
}

