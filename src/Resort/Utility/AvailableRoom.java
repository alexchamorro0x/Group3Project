package Resort.Utility;

public class AvailableRoom {
  private String roomType;
  private int roomNumber;

  public AvailableRoom(String roomType, int roomNumber) {
    this.roomType = roomType;
    this.roomNumber = roomNumber;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType) {
    this.roomType = roomType;
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }
}

