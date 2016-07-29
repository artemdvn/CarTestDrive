package car.logic;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import car.util.HibernateUtil;

public class ManagementSystem {

    private static ManagementSystem instance;
    private static SessionFactory sf = HibernateUtil.getSessionFactory();
    
    private ManagementSystem() {
    }

    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
        	instance = new ManagementSystem();
        }
        return instance;
    }
    
    public List<Showroom> getShowrooms() {
    	Session session = sf.openSession();
    	session.beginTransaction();	
		Query query = session.createQuery("from Showroom");
        List<Showroom> showrooms = (List<Showroom>) query.list();		
		session.close();
		return showrooms;		
    }

    public Collection<Car> getAllCars() {
    	Session session = sf.openSession();
    	session.beginTransaction();	
		Query query = session.createQuery("from Car ORDER BY carName, modelName, mileage");
		Collection<Car> cars = (List<Car>) query.list();		
		session.close();
		return cars;		
    }

    public Collection<Car> getCarsFromShowroom(Showroom showroom){
    	Session session = sf.openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("from Car WHERE showroomId=:showroomId "
    			+ "ORDER BY carName, modelName, mileage");
    	query.setInteger("showroomId", showroom.getShowroomId());
		Collection<Car> cars = (List<Car>) query.list();
		session.close();
		return cars;        
    }
    
    public Collection<Car> getFreeCarsFromShowroom(Showroom showroom){
    	Session session = sf.openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("from Car "
    			+ "WHERE showroomId=:showroomId AND reserved=:reserved "
    			+ "ORDER BY carName, modelName, mileage");
    	query.setInteger("showroomId", showroom.getShowroomId());
    	query.setBoolean("reserved", false);
		Collection<Car> cars = (List<Car>) query.list();
		session.close();
		return cars;    
    }
    
    public Car getCarById(int carId) {
    	Session session = sf.openSession();
    	session.beginTransaction();
    	Query query = session.createQuery("from Car WHERE carId=:carId");
    	query.setInteger("carId", carId);
		Car car = (Car)query.uniqueResult();
		session.close();
		return car;
    }
    
    public Showroom getShowroomById(int showroomId) {    	
    	Session session = sf.openSession();
    	session.beginTransaction();    	
    	Query query = session.createQuery("from Showroom where showroomId = :showroomId");
    	query.setInteger("showroomId", showroomId);
        Showroom s = (Showroom) query.uniqueResult();        
		session.close();
		return s;
	}

    public void moveCarsToShowroom(Showroom oldShowroom, Showroom newShowroom) {
		Session session = sf.openSession();
    	session.beginTransaction();    	
    	Query query = session.createQuery("UPDATE Car SET showroomId = :newId "
    			+ "WHERE showroomId = :showroomId");
    	query.setInteger("newId", newShowroom.getShowroomId());
    	query.setInteger("showroomId", oldShowroom.getShowroomId());
    	query.executeUpdate(); 
    	session.getTransaction().commit();
		session.close();
    }

    public void removeCarsFromShowroom(Showroom showroom) {
		Session session = sf.openSession();
    	session.beginTransaction();    	
    	Query query = session.createQuery("DELETE FROM Car WHERE showroomId = :showroomId");
    	query.setInteger("showroomId", showroom.getShowroomId());
    	query.executeUpdate(); 
    	session.getTransaction().commit();
		session.close();
	}

    public void insertCar(Car car) {
    	Session session = sf.openSession();
    	session.beginTransaction();
    	session.save(car);
    	session.getTransaction().commit();
    	session.close();    	
	}

    public void updateCar(Car car) {
    	Session session = sf.openSession();
    	session.beginTransaction();
    	session.saveOrUpdate(car);
    	session.getTransaction().commit();
    	session.close();            
    }

    public void deleteCar(Car car) {
    	Session session = sf.openSession();
    	session.beginTransaction();
    	session.delete(car);
    	session.getTransaction().commit();
    	session.close();    	
	}
    
    public void insertShowroom(Showroom s) {
		Session session = sf.openSession();
    	session.beginTransaction();
    	session.save(s);
    	session.getTransaction().commit();
    	session.close();
    }

    public void updateShowroom(Showroom s) {
		Session session = sf.openSession();
    	session.beginTransaction();
    	session.saveOrUpdate(s);
    	session.getTransaction().commit();
    	session.close();
    }
    
    public void deleteShowroom(Showroom s) {
		Session session = sf.openSession();
    	session.beginTransaction();
    	session.delete(s);
    	session.getTransaction().commit();
    	session.close();
	}
}