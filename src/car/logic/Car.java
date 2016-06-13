package car.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Car implements Comparable<Car> {

	private int carId;
	private String carName;
	private String modelName;
	private int yearOfIssue;
	private int showroomId;
	private int mileage;
	private boolean reserved;

	public Car() {
	}
	
	public Car(ResultSet rs) throws SQLException {
		setCarId(rs.getInt(1));
		setCarName(rs.getString(2));
		setModelName(rs.getString(3));
		setYearOfIssue(rs.getInt(4));
		setShowroomId(rs.getInt(5));
		setMileage(rs.getInt(6));
		setReserved(rs.getBoolean(7));
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

	@Override
	public String toString() {
		return "Car [carName=" + carName + ", modelName=" + modelName + ", yearOfIssue=" + yearOfIssue + ", mileage="
				+ mileage + "]";
	}

	@Override
	public int compareTo(Car o) {
		int result;
		result = carName.compareTo(o.carName);
		if (result != 0)
			return result;
		result = modelName.compareTo(o.modelName);
		if (result != 0)
			return result;
		result = Integer.compare(mileage, o.mileage);
		return result;
	}
	
	

}
