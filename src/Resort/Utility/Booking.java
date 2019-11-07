package Resort.Utility;

public class Booking {

  private String checkIn = new String("");
  private String checkOut = new String("");
  private String roomType = new String("");
  private String roomNumber = new String("");
  private String bookingId = new String("");
  // account info
  private AccountInformation accountInformation;
  private String firstName = new String("");
  private String lastName = new String("");

  public Booking(String checkIn, String checkOut, String roomType, String roomNumber,
      String reservationId, AccountInformation accountInformation) {
    setCheckIn(checkIn);
    setCheckOut(checkOut);
    setRoomType(roomType);
    setRoomNumber(roomNumber);
    setBookingId(reservationId);
    setAccountInformation(accountInformation);
    this.firstName = accountInformation.getFirstName();
    this.lastName = accountInformation.getLastName();
  }

  public Booking() {
    this("", "", "", "", "",
        null);
  }

  public String getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(String checkIn) {
    this.checkIn = checkIn;
  }

  public String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(String checkOut) {
    this.checkOut = checkOut;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public String getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }

  public String getBookingId() {
    return bookingId;
  }

  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public AccountInformation getAccountInformation() {
    return accountInformation;
  }

  public void setAccountInformation(AccountInformation accountInformation) {
    this.accountInformation = accountInformation;
    this.firstName = accountInformation.getFirstName();
    this.lastName = accountInformation.getLastName();
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
}