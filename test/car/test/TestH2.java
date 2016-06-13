package car.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import org.h2.tools.DeleteDbFiles;
import org.junit.Test;

import car.logic.Car;
import car.logic.ManagementSystem;

public class TestH2 {

	@Test
	public void testCarTestDrive() {
		Connection conn = null;
		Statement stat = null;
		try {
			DeleteDbFiles.execute("~", "test", true);

			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test");
			stat = conn.createStatement();

			stat.execute("create table showrooms(showroom_id int primary key auto_increment, nameShowroom varchar(255), address varchar(255))");
			stat.execute("insert into showrooms values(1, 'Nissan Center', 'Zaporizke shosse str. 22')");
			stat.execute("insert into showrooms values(2, 'Laguna', 'Dnipro, Slobozhanski avn. 127')");
			
			stat.execute("create table cars(car_id int primary key auto_increment, carName varchar(255), modelName varchar(255), yearOfIssue int, showroom_id int, mileage int, reserved boolean)");
			stat.execute("insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved) values('Nissan', 'Note', 2013, 1, 7500, false)");
			stat.execute("insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved) values('Nissan', 'X-Trail', 2015, 1, 8800, false)");
			stat.execute("insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved) values('Nissan', 'GT-R', 2016, 1, 200, false)");

			ManagementSystem ms = ManagementSystem.getInstance(conn);
			
			Collection<Car> cars = ms.getAllCars();
			assertEquals(3, cars.size());
			
			Collection<Car> cars1 = ms.getCarsFromShowroom(ms.getShowroomById(1));
			assertEquals(3, cars1.size());
			
			Collection<Car> cars2 = ms.getCarsFromShowroom(ms.getShowroomById(2));
			assertEquals(0, cars2.size());
			
			ms.moveCarsToShowroom(ms.getShowroomById(1), ms.getShowroomById(2));
			
			Collection<Car> cars3 = ms.getCarsFromShowroom(ms.getShowroomById(2));
			assertEquals(3, cars3.size());
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
