package es.uco.pw.serverlet;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.business.dto.UserDTO;
import es.uco.pw.data.dao.EspectaculoDAO;
import es.uco.pw.data.dao.ReviewDAO;
import es.uco.pw.data.dao.userDAO;

/**
 * Servlet implementation class ReviewShowList
 */
@WebServlet(name="ReviewList",urlPatterns="/ReviewShowList")
public class ReviewShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewShowList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * For this function need to pass a lot of parameters and make multiple queries to database
	 * the best option is to return a list of Strings formed with all the review information
	 * inside, then split that string (using some delimiter like ,) and show in table format
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");   
        java.util.Properties prop = new java.util.Properties();
        prop.load(getServletContext().getResourceAsStream(file));
		ReviewDAO rDao = new ReviewDAO(urlDB,userDB,passDB,prop);
		EspectaculoDAO eDao = new EspectaculoDAO(urlDB,userDB,passDB,prop);
		userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);

		//Need to get the reviews for a show not for all shows
		
		if (request.getParameter("e") == null) {
			//TODO: Manage error
		}
		else {
			int id = Integer.parseInt(request.getParameter("e"));
			List<String> reviewsInformation = new ArrayList<String>();
			for (ReviewDTO it: rDao.getReviewForEspectaculo(id)) {
				String reviewData = "";
				EspectaculoDTO espectaculo = eDao.getById(it.getEspectacle());
				UserDTO user = uDao.getById(it.getCreator());
				reviewData += espectaculo.getTitulo()+",";
				reviewData += user.getName() + ",";
				reviewData += it.getText();
				reviewsInformation.add(reviewData);
			}
			
			// Set the map inside the request
			request.setAttribute("reviews", reviewsInformation);
			//Make the forward to View
			request.getRequestDispatcher("/view/reviewList.jsp").forward(request, response);
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
