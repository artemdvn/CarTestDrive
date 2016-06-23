package car.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.logic.Car;
import car.logic.ManagementSystem;
import car.logic.Showroom;
import car.web.forms.MainFrameForm;

@WebServlet(
	    name = "CarFrameServlet",
	    description = "Car frame servlet",
	    urlPatterns = {"/edit"}
	)
public class CarFrameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String carId = req.getParameter("carId");
        if (carId != null && req.getParameter("OK") != null) {
            try {
                if (Integer.parseInt(carId) > 0) {
                    updateCar(req);
                }
                else {
                    insertCar(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        
        String gs = req.getParameter("showroomId");
        String ys = req.getParameter("yearOfIssue");
        int showroomId = -1;
        if (gs != null) {
            showroomId = Integer.parseInt(gs);
        }
        int year = -1;
        if (ys != null) {
            year = Integer.parseInt(ys);
        }
        MainFrameForm form = new MainFrameForm();
        try {
            Collection showrooms = ManagementSystem.getInstance().getShowrooms();
            Showroom g = new Showroom();
            g.setShowroomId(showroomId);
            if (showroomId == -1) {
                Iterator i = showrooms.iterator();
                g = (Showroom) i.next();
            }
            Collection cars = ManagementSystem.getInstance().getCarsFromShowroom(g);
            form.setShowroomId(g.getShowroomId());
            form.setYearOfIssue(year);
            form.setShowrooms(showrooms);
            form.setCars(cars);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        } catch (Exception e) {
        	throw new IOException(e.getMessage());
		}
        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void updateCar(HttpServletRequest req) throws SQLException, ParseException {
        Car s = prepareCar(req);
        ManagementSystem.getInstance().updateCar(s);
    }

    private void insertCar(HttpServletRequest req) throws SQLException, ParseException {
        Car s = prepareCar(req);
        ManagementSystem.getInstance().insertCar(s);
    }

    private Car prepareCar(HttpServletRequest req) throws ParseException {
        Car s = new Car();
        s.setCarId(Integer.parseInt(req.getParameter("carId")));
        s.setCarName(req.getParameter("carName").trim());
        s.setModelName(req.getParameter("modelName").trim());
        s.setYearOfIssue(Integer.parseInt(req.getParameter("yearOfIssue").trim()));
        s.setShowroomId(Integer.parseInt(req.getParameter("showroomId").trim()));
        s.setMileage(Integer.parseInt(req.getParameter("mileage").trim()));
        s.setReserved(Boolean.parseBoolean(req.getParameter("reserved").trim()));
        return s;
    }
}
