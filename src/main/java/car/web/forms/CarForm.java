package car.web.forms;

import java.util.Collection;

import car.logic.Car;

public class CarForm
{
    private int carId;
	private String carName;
	private String modelName;
	private int yearOfIssue;
	private int showroomId;
	private int mileage;
	private boolean reserved;
    private Collection showrooms;
    
    public CarForm() {
	}

	public void initFromCar(Car st) {
        this.carId = st.getCarId();
        this.carName = st.getCarName();
        this.modelName = st.getModelName();
        this.yearOfIssue = st.getYearOfIssue();
        this.showroomId = st.getShowroomId();
        this.mileage = st.getMileage();
        this.reserved = st.isReserved();
    }

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getYearOfIssue() {
		return yearOfIssue;
	}

	public void setYearOfIssue(int yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}

	public int getShowroomId() {
		return showroomId;
	}

	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Collection getShowrooms() {
		return showrooms;
	}

	public void setShowrooms(Collection showrooms) {
		this.showrooms = showrooms;
	}  
    
}
