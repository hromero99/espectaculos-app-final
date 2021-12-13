package es.uco.pw.serverlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class dashboard
 */
@WebServlet(name="dashboard", urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Dashboard() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
		if (bean == null) {
			RequestDispatcher disp = request.getRequestDispatcher("login");
			request.setAttribute("message", "Tienes que estar autenticado para acceder a esta parte");
			disp.forward(request,response);
		}
		// Redirect to view with all user information
		if (bean.getAdministrator() == true) {
			RequestDispatcher disp = request.getRequestDispatcher("dashboard/admin");
			disp.forward(request,response);
		}
		// Redirect to spectator interface
		else {
			RequestDispatcher disp = request.getRequestDispatcher("dashboard/user");
			disp.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
