package car.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "showrooms")
public class Showroom {

	private int showroomId;
	private String nameShowroom;
	private String address;
	
	public Showroom() {
	}
	
	public Showroom(int id, String name, String address) {
		this.showroomId = id;
		this.nameShowroom = name;
		this.address = address;		
	}

	@Id
	@Column(name = "showroom_id", unique = true, nullable = false)
	public int getShowroomId() {
		return showroomId;
	}

	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}

	@Column(name = "nameShowroom", nullable = false)
	public String getNameShowroom() {
		return nameShowroom;
	}

	public void setNameShowroom(String nameShowroom) {
		this.nameShowroom = nameShowroom;
	}

	@Column(name = "address", nullable = false)
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
