package car.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagementSystem {

    private static Connection con;
    private static ManagementSystem instance;
    private static DataSource dataSource;
    
    private ManagementSystem() {
    }

    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
        	try {
                instance = new ManagementSystem();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/CarsDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    public static synchronized ManagementSystem getInstance(Connection connection) {
        if (instance == null) {
        	instance = new ManagementSystem();
            con = connection;
        }
        return instance;
    }

    public List<Showroom> getShowrooms() throws SQLException {
        List<Showroom> showrooms = new ArrayList<Showroom>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT showroom_id, nameShowroom, address FROM showrooms");
		while (rs.next()) {
			Showroom sr = new Showroom();
			sr.setShowroomId(rs.getInt(1));
			sr.setNameShowroom(rs.getString(2));
			sr.setAddress(rs.getString(3));

			showrooms.add(sr);
		}
		rs.close();
		stmt.close();
		return showrooms;
    }

    public Collection<Car> getAllCars() throws SQLException {
        Collection<Car> cars = new ArrayList<Car>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT car_id, carName, modelName, yearOfIssue, "
				+ "showroom_id, mileage, reserved FROM cars " + "ORDER BY carName, modelName, mileage");
		while (rs.next()) {
			Car st = new Car(rs);
			cars.add(st);
		}
		rs.close();
		stmt.close();
		return cars;
    }

    public Collection<Car> getCarsFromShowroom(Showroom showroom) throws SQLException {
        Collection<Car> cars = new ArrayList<Car>();
		PreparedStatement stmt = con.prepareStatement("SELECT car_id, carName, modelName, yearOfIssue, " 
						+ "showroom_id, mileage, reserved FROM cars "
						+ "WHERE showroom_id=? " + "ORDER BY carName, modelName, mileage");
		stmt.setInt(1, showroom.getShowroomId());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Car car = new Car(rs);
			cars.add(car);
		}
		rs.close();
		stmt.close();
		return cars;
    }
    
    public Collection<Car> getFreeCarsFromShowroom(Showroom showroom) throws SQLException {
        Collection<Car> cars = new ArrayList<Car>();
		PreparedStatement stmt = con.prepareStatement("SELECT car_id, carName, modelName, yearOfIssue, " 
						+ "showroom_id, mileage, reserved FROM cars "
						+ "WHERE showroom_id=? AND reserved=? " + "ORDER BY carName, modelName, mileage");
		stmt.setInt(1, showroom.getShowroomId());
		stmt.setBoolean(2, false);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Car car = new Car(rs);
			cars.add(car);
		}
		rs.close();
		stmt.close();
		return cars;
    }
    
    public Car getCarById(int carId) throws SQLException {
    	Car car = null;
        PreparedStatement stmt = con.prepareStatement("SELECT car_id, carName, modelName,"
                + "yearOfIssue, showroom_id, mileage, reserved FROM cars WHERE car_id = ?");
        stmt.setInt(1, carId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	car = new Car(rs);
        }
        rs.close();
        stmt.close();
        return car;
    }
    
    public Showroom getShowroomById(int showroomId) throws SQLException {
    	Showroom s = null;
        PreparedStatement stmt = con.prepareStatement("SELECT showroom_id, nameShowroom, address"
                + " FROM showrooms WHERE showroom_id = ?");
        stmt.setInt(1, showroomId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
        	s = new Showroom(rs);
        }
        rs.close();
        stmt.close();
        return s;
    }

    public void moveCarsToShowroom(Showroom oldShowroom, Showroom newShowroom) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE cars SET showroom_id=? " 
				+ "WHERE showroom_id=?");
		stmt.setInt(1, newShowroom.getShowroomId());
		stmt.setInt(2, oldShowroom.getShowroomId());
		stmt.execute();
		stmt.close();        
    }

    public void removeCarsFromShowroom(Showroom showroom) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM cars WHERE showroom_id=?");
		stmt.setInt(1, showroom.getShowroomId());
		stmt.execute();
		stmt.close();        
    }

    public void insertCar(Car car) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO cars "
				+ "(carName, modelName, yearOfIssue, showroom_id, mileage, reserved) " 
				+ "VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setString(1, car.getCarName());
		stmt.setString(2, car.getModelName());
		stmt.setInt(3, car.getYearOfIssue());
		stmt.setInt(4, car.getShowroomId());
		stmt.setInt(5, car.getMileage());
		stmt.setBoolean(6, car.isReserved());
		stmt.execute();
		stmt.close();        
    }

    public void updateCar(Car car) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE cars SET " 
				+ "carName=?, modelName=?, yearOfIssue=?, "
				+ "showroom_id=?, mileage=?, reserved=? " 
				+ "WHERE car_id=?");
		stmt.setString(1, car.getCarName());
		stmt.setString(2, car.getModelName());
		stmt.setInt(3, car.getYearOfIssue());
		stmt.setInt(4, car.getShowroomId());
		stmt.setInt(5, car.getMileage());
		stmt.setBoolean(6, car.isReserved());
		stmt.setInt(7, car.getCarId());
		stmt.execute();
		stmt.close();        
    }

    public void deleteCar(Car car) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM cars WHERE car_id=?");
		stmt.setInt(1, car.getCarId());
		stmt.execute();
		stmt.close();       
    }
    
    public void insertShowroom(Showroom s) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO showrooms "
				+ "(nameShowroom, address) " 
				+ "VALUES (?, ?)");
		stmt.setString(1, s.getNameShowroom());
		stmt.setString(2, s.getAddress());
		stmt.execute();
		stmt.close();        
    }

    public void updateShowroom(Showroom s) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE showrooms SET " 
				+ "nameShowroom=?, address=? " 
				+ "WHERE showroom_id=?");
		stmt.setString(1, s.getNameShowroom());
		stmt.setString(2, s.getAddress());
		stmt.setInt(3, s.getShowroomId());
		stmt.execute();
		stmt.close();        
    }
    
    public void deleteShowroom(Showroom s) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM showrooms WHERE showroom_id=?");
		stmt.setInt(1, s.getShowroomId());
		stmt.execute();
		stmt.close();       
    }
}