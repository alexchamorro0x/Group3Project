package Resort.Utility;

/**
 * This class stores the information for a room booking.
 */
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

  /**
   * Constructor for making a booking object
   * @param checkIn date Checkin date for the booking
   * @param checkOut date Check out date for the booking.
   * @param roomType string The roomtyoe for the booking
   * @param roomNumber string containing the room number for the booking.
   * @param reservationId String containg the reservationID for the specific booking.
   * @param accountInformation AccountInformation for the booking user.
   */
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

  /**
   * Default constructor for the booking class.
   */
  public Booking() {
    this("", "", "", "", "",
        null);
  }

  /**
   * Returns the check in date for the booking
   * @return date for checking in
   */
  public String getCheckIn() {
    return checkIn;
  }

  /**
   * Sets the check in date for the booking
   * @param checkIn string containg the checkin date.
   */
  public void setCheckIn(String checkIn) {
    this.checkIn = checkIn;
  }

  /**
   * Returns a string containing the check out date for the booking.
   * @return String containing the check out date.
   */
  public String getCheckOut() {
    return checkOut;
  }

  /**
   * Sets the checkout date for the booking
   * @param checkOut String containing the checkout date.
   */
  public void setCheckOut(String checkOut) {
    this.checkOut = checkOut;
  }

  /**
   * Sets the room type for the booking.
   * @return String containg the room type for the booking.
   */
  public String getRoomType() {
    return roomType;
  }

  /**
   * Sets the Room type for the booking
   * @param roomType String containg the room type for the booking.
   */
  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  /**
   * Gets the room number for the booking.
   * @return String containing the room number for the booking.
   */
  public String getRoomNumber() {
    return roomNumber;
  }

  /**
   * Sets the room number for the booking.
   * @param roomNumber String contains the booked rooms roomnumber.
   */
  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }

  /**
   * Returns the BookingID for the booking
   * @return String containing the booking bookingID
   */
  public String getBookingId() {
    return bookingId;
  }

  /**
   * Sets the booking bookingID
   * @param bookingId String containing the booking bookingID
   */
  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  /**
   * Sets the Account Information for the user making the booking.
   * @return AccountInformation object containing the booking users information.
   */
  public AccountInformation getAccountInformation() {
    return accountInformation;
  }

  /**
   * Sets the account information for the user who requested the booking.
   * @param accountInformation AccountInformation object containing the booking bookingID
   */
  public void setAccountInformation(AccountInformation accountInformation) {
    this.accountInformation = accountInformation;
    this.firstName = accountInformation.getFirstName();
    this.lastName = accountInformation.getLastName();
  }


}