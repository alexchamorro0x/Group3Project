package Resort.Utility;

/**\
 * Class containing the information for a bookable room
 */
public class AvailableRoom {
  private String roomType;
  private int roomNumber;

  /**
   * Constructor for available room object.
   * @param roomType String containing the room type for the availability
   * @param roomNumber int containing the room number for the availability
   */
  public AvailableRoom(String roomType, int roomNumber) {
    this.roomType = roomType;
    this.roomNumber = roomNumber;
  }

  /**
   * Gets the type of room for the available listing
   * @return String containing the room type.
   */
  public String getRoomType() {
    return roomType;
  }

  /**
   * Sets the type of room for the available listing
   * @param roomType String containing the room type.
   */
  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  /**
   * Gets room number for the available room listing.
   * @return int containing the room number
   */
  public int getRoomNumber() {
    return roomNumber;
  }

  /**
   * Sets room number for the available room listing.
   * @param roomNumber int containing the room number
   */
  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }
}