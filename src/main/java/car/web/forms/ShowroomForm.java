package car.web.forms;

import car.logic.Showroom;

public class ShowroomForm
{
    private int showroomId;
	private String nameShowroom;
	private String address;
	
    public void initFromShowroom(Showroom s) {
        this.showroomId = s.getShowroomId();
        this.nameShowroom = s.getNameShowroom();
        this.address = s.getAddress();
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

	 
    
}
