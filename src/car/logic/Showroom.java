package car.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Showroom {

	private int showroomId;
	private String nameShowroom;
	private String address;
	
	public Showroom() {
	}
	
	public Showroom(ResultSet rs) throws SQLException {
		setShowroomId(rs.getInt(1));
		setNameShowroom(rs.getString(2));
		setAddress(rs.getString(3));
	}

	public int getShowroomId() {
		return showroomId;
	}

	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}

	public String getNameShowroom() {
		return nameShowroom;
	}

	public void setNameShowroom(String nameShowroom) {
		this.nameShowroom = nameShowroom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Showroom [nameShowroom=" + nameShowroom + "]";
	}

}
