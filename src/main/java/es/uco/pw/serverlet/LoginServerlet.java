package es.uco.pw.serverlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.business.dto.UserDTO;
import es.uco.pw.data.dao.userDAO;

/**
 * Servlet implementation class LoginServerlet
 */
@WebServlet(name="login",urlPatterns="/login")
public class LoginServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Need to check if user is logged to redirect
		HttpSession session = request.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
		if (bean != null) {
			response.sendRedirect("dashboard");
		}
		RequestDispatcher vHeader = request.getRequestDispatcher("header.html");
		RequestDispatcher view = request.getRequestDispatcher("login.html");
		vHeader.include(request, response);
		view.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// First of all set the output of reponse beacuse allways return html file
		response.setContentType("text/html");
		
		String emailUser = request.getParameter("email");
		String emailPassword = request.getParameter("password");
    	
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
        UserDTO user = uDao.searchUserByEmail(emailUser);
        
        // The user isn't registered on database so redirect with error message
        if (user == null) {	
        	
        	PrintWriter out = response.getWriter();
			out.println("El usuario no existe, registrate en la aplicacion");
        	RequestDispatcher disp = request.getRequestDispatcher("login.html");
        	disp.include(request,response);
        }
        
        else {
	        // If the user is in database, need to check password
	        
	        if (emailPassword.equals(user.getPassword())) {
	        	// Create the customer Bean and assign to session
	        	CustomerBean bean = new CustomerBean();
	        	bean.setEmailUser(user.getEmail());
	        	bean.setAdministrator(user.isAdministrator());
	        	bean.setId(user.getId());
	        	bean.setName(user.getName());
	        	bean.setSurnames(user.getSurnames());
	        	HttpSession session = request.getSession();
	    		session.setAttribute("customerBean", bean);
	    		
	    		// When Customer bean is created and saved in sessions redirect to "dashboard"
	    		RequestDispatcher disp = request.getRequestDispatcher("dashboard");
	    		disp.forward(request,response);
	        }
	        
	        // If the credentials are invalid return to login with errors
	        
	        else {
	        	PrintWriter out = response.getWriter();
	        	RequestDispatcher disp = request.getRequestDispatcher("login.html");
	        	disp.include(request,response);
				out.println("La contraseña es incorrecta");

	        }
        }
        	
	}

}
