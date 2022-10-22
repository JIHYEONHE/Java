package hw;
public class Hotel {
	private String name;
	private String roomNum;
	public Hotel(String name, String roomNum) {
		super();
		this.name = name;
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	@Override
	public String toString() {
		return "Hotel [name=" + name + ", roomNum=" + roomNum + "]";
	}
	
	
}



