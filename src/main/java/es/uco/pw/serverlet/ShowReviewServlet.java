package es.uco.pw.serverlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.data.dao.ReviewDAO;

/**
 * Servlet implementation class ShowReviewServlet
 */
@WebServlet(name="ShowReview",urlPatterns="/ShowReview")
public class ShowReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get information from get request to retrieve Review information
		if (request.getParameter("r") == null) {
			RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
			view.forward(request, response);

		}
		int reviewId = Integer.parseInt(request.getParameter("r"));
		PrintWriter out = response.getWriter();
		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");   
        java.util.Properties prop = new java.util.Properties();
        prop.load(getServletContext().getResourceAsStream(file));
		ReviewDAO rDao = new ReviewDAO(urlDB,userDB,passDB,prop);
		ReviewDTO review = rDao.getById(reviewId);
		if (review == null) {
			//TODO: Here need to add the error message and redirect to servlet
			RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
			view.forward(request, response);
		}
		out.println(review.getText());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// For this functionality, the post method isn't necessary
		doGet(request, response);
	}

}
