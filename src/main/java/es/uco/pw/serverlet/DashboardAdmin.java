package es.uco.pw.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.UserDTO;
import es.uco.pw.data.dao.*;
/**
 * Servlet implementation class DashboardAdmin
 */

@WebServlet(name="dashboardadmin",urlPatterns ="/dashboard/admin")
public class DashboardAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: Include the header for the Admin dashboard, setting the link to browser all functions

		// Configure the response object and create object for write
		// The charset must be UTF-8 for the accents and special characters
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		
		// Load database objects to use  userDAO class for database query
		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");
        //create properties object empty
        java.util.Properties prop = new java.util.Properties();
        // load file content
        prop.load(getServletContext().getResourceAsStream(file));
        //PAss content to DAO for future query get
        userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
        
        ArrayList<UserDTO> users = uDao.getAll();
        //TODO: Maybe before the users table need to import more html information in order to make dashboard interface
        
        // Iterate over the user list and show information on web
        for (UserDTO user: users) {
        	out.print("<td>"+user.getName()+"</td>");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
