package Resort.Utility;

/**
 * This class contains the information for an unbooked room.
 */
public class AvailableRoom {
  private String roomType;
  private int roomNumber;

  /**
   * Constructor for the available room class.
   * @param roomType The type of room for the listing.
   * @param roomNumber The number for the listed room.
   */
  public AvailableRoom(String roomType, int roomNumber) {
    this.roomType = roomType;
    this.roomNumber = roomNumber;
  }

  /**
   * Gets the room number for the listing
   * @return String containing the room number for the listing.
   */
  public String getRoomType() {
    return roomType;
  }

  /**
   * Sets the room number for the listing.
   * @param roomType String containing the room number for the listing.
   */
  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  /**
   * Gets the room number for the listing.
   * @return int containing the room number for the listing.
   */
  public int getRoomNumber() {
    return roomNumber;
  }

  /**
   * Sets the room number for the listing
   * @param roomNumber int containing the room number for the listing.
   */
  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }
}