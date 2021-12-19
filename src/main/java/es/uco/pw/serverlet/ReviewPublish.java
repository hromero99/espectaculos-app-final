package es.uco.pw.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.data.dao.EspectaculoDAO;
import es.uco.pw.data.dao.ReviewDAO;
import es.uco.pw.data.dao.userDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ReviewPublish
 */
@WebServlet(name="ReviewPublish",urlPatterns="/ReviewPublish")
public class ReviewPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewPublish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	response.setContentType("text/html");
		 	
			String urlDB = getServletContext().getInitParameter("DBurl");
	        String userDB = getServletContext().getInitParameter("DBuser");
	        String passDB = getServletContext().getInitParameter("DBpass");
	        String file = getServletContext().getInitParameter("properties");   
	        java.util.Properties prop = new java.util.Properties();
	        prop.load(getServletContext().getResourceAsStream(file));
	        EspectaculoDAO eDao = new EspectaculoDAO(urlDB,userDB,passDB,prop);
	        List<EspectaculoDTO> espectaculos = eDao.getEspectaculosCelerbated();
			PrintWriter out = response.getWriter();

	        if (espectaculos.size() == 0) {
	        	// Control when is empty
	        	out.println("No hay espectaculos celebrados");
	        }
	        else {
	        	// The espectaculos list contains all the espectaculos con pases celebrados con anterioridad
        		List<String> espectaculosInformation = new ArrayList<String>();
	        	for (EspectaculoDTO espectaculoIterator: espectaculos) {
	        		espectaculosInformation.add(espectaculoIterator.toCsv());
	        	}
	        	request.getRequestDispatcher("/view/user/reviewPublish.jsp").forward(request, response);
	        }
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Catch dbConnection information from context file
		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");   
        java.util.Properties prop = new java.util.Properties();
        prop.load(getServletContext().getResourceAsStream(file));
		// Catch information from form 
		
		// The value of espectaculo is a Id to avoid to repeat queries to database
		int espectaculo_id = Integer.parseInt(request.getParameter("espectaculo"));
		String review_text = request.getParameter("review");
		
		// As well as the form information need the user logged to create the Review Object, this can be found using the CustomerBean created in logging process
		
		HttpSession session = request.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
		if (bean == null) {
			//TODO: Manage the error page for users which aren't logged at this time
			// This a problem of ACL because this functionality is just for logged users
			
		}
		// Once the information of form is catch need to create a ReviewDao object to create the review
		ReviewDAO rDao = new ReviewDAO(urlDB,userDB,passDB,prop);
		// Use the information to create string of review
		rDao.create(String.format("%d,%d,%s,%d",espectaculo_id,0,review_text,bean.getId()));
		
		//TODO: the idea to redirect here is to show the review formated
		
		PrintWriter out = response.getWriter();
		out.println(espectaculo_id);
		out.println(review_text);
		
	}

}
