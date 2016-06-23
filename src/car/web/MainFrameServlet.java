package car.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.web.forms.MainFrameForm;
import car.logic.Car;
import car.logic.ManagementSystem;
import car.logic.Showroom;
import car.web.forms.CarForm;

@WebServlet(
	    name = "MainFrameServlet",
	    description = "Main frame servlet",
	    urlPatterns = {"/main"}
	)
public class MainFrameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        
        //Add
        if (answer == 1) {
            try {
                Car s = new Car();
                s.setCarId(0);
                s.setYearOfIssue(2016);
                s.setReserved(false);
                Collection showrooms = ManagementSystem.getInstance().getShowrooms();
                CarForm sForm = new CarForm();
                sForm.initFromCar(s);
                sForm.setShowrooms(showrooms);
                req.setAttribute("car", sForm);
                getServletContext().getRequestDispatcher("/CarFrame.jsp").forward(req, resp);
                return;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }

        }

        //Edit
        if (answer == 2) {
            try {
                if (req.getParameter("carId") != null) {
                    int carId = Integer.parseInt(req.getParameter("carId"));
                    Car s = ManagementSystem.getInstance().getCarById(carId);
                    Collection showrooms = ManagementSystem.getInstance().getShowrooms();
                    CarForm sForm = new CarForm();
                    sForm.initFromCar(s);
                    sForm.setShowrooms(showrooms);
                    req.setAttribute("car", sForm);
                    getServletContext().getRequestDispatcher("/CarFrame.jsp").forward(req, resp);
                    return;
                }
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        
        String gs = req.getParameter("showroomId");
        
        //MoveGroup
        if (answer == 3) {
            String newGs = req.getParameter("newShowroomId");
            try {
            	Showroom g = new Showroom();
                g.setShowroomId(Integer.parseInt(gs));
                Showroom ng = new Showroom();
                ng.setShowroomId(Integer.parseInt(newGs));
                ManagementSystem.getInstance().moveCarsToShowroom(g, ng);
                gs = newGs;
            } catch (SQLException sql_e) {
                throw new IOException(sql_e.getMessage());
            }
        }
        
		// Reserve/Release
		if ((answer == 4) || (answer == 5)) {
			try {
				if (req.getParameter("carId") != null) {
					int carId = Integer.parseInt(req.getParameter("carId"));
					Car car = ManagementSystem.getInstance().getCarById(carId);
					if (answer == 4) {
						car.setReserved(true);
					} else {
						car.setReserved(false);
					}
					ManagementSystem.getInstance().updateCar(car);
				}
			} catch (SQLException sql_e) {
				throw new IOException(sql_e.getMessage());
			}
		}
		
		
		int showroomId = -1;
        if (gs != null) {
            showroomId = Integer.parseInt(gs);
        }
        
        MainFrameForm form = new MainFrameForm();
        try {
            List<Showroom> showrooms = ManagementSystem.getInstance().getShowrooms();
            Showroom g = new Showroom();
            g.setShowroomId(showroomId);
            if (showroomId == -1) {
                Iterator<Showroom> i = showrooms.iterator();
                g = i.next();
            }
            Collection<Car> cars = null;
            if (req.getParameter("getFreeList") != null) {
            	cars = ManagementSystem.getInstance().getFreeCarsFromShowroom(g);            	
            } else{
            	cars = ManagementSystem.getInstance().getCarsFromShowroom(g);            	
            }
            
            form.setShowroomId(g.getShowroomId());
            form.setShowrooms(showrooms);
            form.setCars(cars);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("MoveGroup") != null) {
            return 3;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("carId") != null) {
                Car s = new Car();
                s.setCarId(Integer.parseInt(req.getParameter("carId")));
                ManagementSystem.getInstance().deleteCar(s);
            }
            return 0;
        }
        if (req.getParameter("Reserve") != null) {
            return 4;
        }
        if (req.getParameter("Release") != null) {
            return 5;
        }
        return 0;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
