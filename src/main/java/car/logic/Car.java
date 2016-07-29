package car.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
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
	
	public Car(int carId, String carName, String modelName, int yearOfIssue, int showroomId, int mileage,
			boolean reserved) {
		this.carId = carId;
		this.carName = carName;
		this.modelName = modelName;
		this.yearOfIssue = yearOfIssue;
		this.showroomId = showroomId;
		this.mileage = mileage;
		this.reserved = reserved;
	}

	@Id
	@Column(name = "car_id", unique = true, nullable = false)
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	@Column(name = "carName", nullable = false)
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Column(name = "modelName", nullable = false)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "yearOfIssue", nullable = false)
	public int getYearOfIssue() {
		return yearOfIssue;
	}

	public void setYearOfIssue(int yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}

	@Column(name = "showroom_id", nullable = false)
	public int getShowroomId() {
		return showroomId;
	}

	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}

	@Column(name = "mileage", nullable = false)
	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	@Column(name = "reserved", nullable = false)
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
