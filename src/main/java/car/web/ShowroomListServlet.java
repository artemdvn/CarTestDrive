package car.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.logic.ManagementSystem;
import car.logic.Showroom;
import car.web.forms.ShowroomForm;
import car.web.forms.ShowroomListForm;

@WebServlet(
	    name = "ShowroomListServlet",
	    description = "Showroom list servlet",
	    urlPatterns = {"/showrooms"}
	)
public class ShowroomListServlet extends HttpServlet
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
			Showroom s = new Showroom();
			s.setShowroomId(0);
			Collection showrooms = ManagementSystem.getInstance().getShowrooms();
			ShowroomForm sForm = new ShowroomForm();
			sForm.initFromShowroom(s);
			req.setAttribute("showroom", sForm);
			getServletContext().getRequestDispatcher("/ShowroomFrame.jsp").forward(req, resp);
			return;
		}

		// Edit
		if (answer == 2) {
			if (req.getParameter("showroomId") != null) {
				int showroomId = Integer.parseInt(req.getParameter("showroomId"));
				Showroom s = ManagementSystem.getInstance().getShowroomById(showroomId);
				ShowroomForm sForm = new ShowroomForm();
				sForm.initFromShowroom(s);
				req.setAttribute("showroom", sForm);
				getServletContext().getRequestDispatcher("/ShowroomFrame.jsp").forward(req, resp);
				return;
			}
		}
        
        ShowroomListForm form = new ShowroomListForm();
        List<Showroom> showrooms = ManagementSystem.getInstance().getShowrooms();
        form.setShowrooms(showrooms);
        
        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/ShowroomList.jsp").forward(req, resp);
    }

    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Edit") != null) {
            return 2;
        }
        if (req.getParameter("Delete") != null) {
            if (req.getParameter("showroomId") != null) {
                Showroom s = new Showroom();
                s.setShowroomId(Integer.parseInt(req.getParameter("showroomId")));
                ManagementSystem.getInstance().removeCarsFromShowroom(s);
                ManagementSystem.getInstance().deleteShowroom(s);
            }
            return 0;
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
