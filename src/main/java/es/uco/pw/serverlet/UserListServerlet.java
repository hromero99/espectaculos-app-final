package es.uco.pw.serverlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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
		List<String> UsersInformation = new ArrayList<String>();
		
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
        // The user variable has the plain user, instead need to get the last login date
        // We need to create a hashMap to pass also the last logging date
        
        for (UserDTO it: users) {
        	UsersInformation.add(it.toCsv() + ","+uDao.getLastLog(it.getId()));

        }
        //TODO: Replace with the html final table
        request.setAttribute("usersList", UsersInformation);   
        request.getRequestDispatcher("view/admin/usersListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This controller don't need a post request, is just a list
		doGet(request, response);
	}

}
