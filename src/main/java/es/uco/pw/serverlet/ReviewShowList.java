package es.uco.pw.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.data.dao.EspectaculoDAO;
import es.uco.pw.data.dao.ReviewDAO;

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
		
		//Need to get the reviews for a show not for all shows
		
		List<ReviewDTO> reviews = rDao.getAll();
		EspectaculoDAO eDao = new EspectaculoDAO(urlDB,userDB,passDB,prop);
		HashMap<ReviewDTO, String> reviewList = new HashMap<ReviewDTO,String>(); 
		// Iterator over all reviews to get id and name of spectacle
		PrintWriter out = response.getWriter();
		for (ReviewDTO ReviewIterator: reviews) {
			String titulo = eDao.getById(ReviewIterator.getEspectacle()).getTitulo();
			reviewList.put(ReviewIterator, titulo);
		}
		
		// Set the map inside the request
		request.setAttribute("reviews", reviewList);
		//Make the forward to View
		request.getRequestDispatcher("/view/reviewList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
