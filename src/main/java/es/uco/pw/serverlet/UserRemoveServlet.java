package es.uco.pw.serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.data.dao.userDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class UserRemoveServlet
 */
@WebServlet("/user/remove")
public class UserRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Try to get userId to delete from url
		HttpSession session = request.getSession();
		if (request.getParameter("u") == null) {
			//TODO: Manage Error
		}
		else {
			int id = Integer.parseInt(request.getParameter("u"));
			//Need to check if user is authenticated
			CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
			if (bean == null) {
				//TODO: Manage Error
			}
			else {
				/* If user is authenticated need to check:
					1. If the user is try to delete his own id
					2. If the user is administrator, and can delete any user
				*/
				String urlDB = getServletContext().getInitParameter("DBurl");
		        String userDB = getServletContext().getInitParameter("DBuser");
		        String passDB = getServletContext().getInitParameter("DBpass");
		        String file = getServletContext().getInitParameter("properties");
		        java.util.Properties prop = new java.util.Properties();        
		        prop.load(getServletContext().getResourceAsStream(file));
		        
		        // Create DAO object to get the user lists
		        userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
				if (bean.getId() == id) {
					if (uDao.delete(id)) {
						//TODO: Redirect to success page
					}
					else {
						request.getRequestDispatcher("error.jsp").forward(request,response);
					}
				}
				else {
					if (bean.getAdministrator()) {
						if (uDao.delete(id)) {
							//TODO: Redirect to success page
						}
						else {
							request.getRequestDispatcher("error.jsp").forward(request,response);
						}
					}
				}
			}
			
		}
		
	}

}
