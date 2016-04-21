package car.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.logic.Car;
import car.logic.ManagementSystem;
import car.logic.Showroom;
import car.web.forms.MainFrameForm;
import car.web.forms.ShowroomListForm;

public class ShowroomFrameServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String showroomId = req.getParameter("showroomId");
        if (showroomId != null && req.getParameter("OK") != null) {
            try {
                if (Integer.parseInt(showroomId) > 0) {
                    updateShowroom(req);
                }
                else {
                    insertShowroom(req);
                }
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
        }
        
        ShowroomListForm form = new ShowroomListForm();
        try {
            Collection showrooms = ManagementSystem.getInstance().getShowrooms();
            form.setShowrooms(showrooms);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        } catch (Exception e) {
        	throw new IOException(e.getMessage());
		}
        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/ShowroomList.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void updateShowroom(HttpServletRequest req) throws SQLException, ParseException {
    	Showroom s = prepareShowroom(req);
        ManagementSystem.getInstance().updateShowroom(s);;
    }

    private void insertShowroom(HttpServletRequest req) throws SQLException, ParseException {
    	Showroom s = prepareShowroom(req);
        ManagementSystem.getInstance().insertShowroom(s);;
    }

    private Showroom prepareShowroom(HttpServletRequest req) throws ParseException {
    	Showroom s = new Showroom();
        s.setShowroomId(Integer.parseInt(req.getParameter("showroomId")));
        s.setNameShowroom(req.getParameter("nameShowroom").trim());
        s.setAddress(req.getParameter("address").trim());
        return s;
    }
}
