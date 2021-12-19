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
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet(name="register", urlPatterns="/register")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/register.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Load information for database operations
		 //we store the username and call the DAO for that specific username
        String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");
        
        // create properties object empty
        java.util.Properties prop = new java.util.Properties();
        // load file content
        
        prop.load(getServletContext().getResourceAsStream(file));
        
        //PAss content to DAO for future query get
        userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
		// Get parameters from post request
		String name = request.getParameter("name");
		String surnames = request.getParameter("surnames");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		int status = uDao.create(String.format("%s,%s,%s,%s,%b,%s", name,surnames,nick,email,false,password));
		if (status == -1 ) {
			//TODO: Redirect to error message
		}
		else if (status == -2) {
			//TODO: Manage error to existing user
		}
		else {
			//User successful so create the bean to log the user
			CustomerBean bean = new CustomerBean();
        	bean.setEmailUser(email);
        	bean.setAdministrator(false);
        	bean.setId(status);
        	bean.setName(name);
        	bean.setSurnames(surnames);
        	HttpSession session = request.getSession();
    		session.setAttribute("customerBean", bean);
    		request.getRequestDispatcher("dashboard").forward(request, response);
		}
		

	}

}
