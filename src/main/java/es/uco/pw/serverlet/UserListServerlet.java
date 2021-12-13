package es.uco.pw.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.UserDTO;
import es.uco.pw.data.dao.userDAO;

/**
 * Servlet implementation class UserListServerlet
 */
@WebServlet(name="UserList",urlPatterns="/usersList")
public class UserListServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// This function is only for administrator user
		// TODO: To check
		
		response.setContentType("text/html");
        String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");
        java.util.Properties prop = new java.util.Properties();        
        prop.load(getServletContext().getResourceAsStream(file));
        
        // Create DAO object to get the user lists
        userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
        List<UserDTO> users = uDao.getAll();
        //TODO: Replace with the html final table
        PrintWriter out = response.getWriter();
        out.println("<table>");
        out.print("<tr>");
    	out.println("<td> Nombre de usuario</td>");        	
    	out.println("<td> Fecha de creación de usuario</td>");        	
    	out.println("<td> Último acceso del usuario</td>");       
    	out.println("</tr>");
        for (UserDTO UserIterator: users) {
        	out.print("<tr>");
        	out.println("<td>"+UserIterator.getName()+"</td>");        	
        	out.println("<td>"+UserIterator.getCreationDate()+"</td>"); 
        	out.println("<td>"+uDao.getLastLog(UserIterator.getId())+"</td>");       
        	out.println("</tr>");
        }
        out.println("</table>");        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This controller don't need a post request, is just a list
		doGet(request, response);
	}

}
