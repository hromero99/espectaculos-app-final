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

import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.data.dao.EspectaculoDAO;
import es.uco.pw.data.dao.ReviewDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ReviewEvaluate
 */
@WebServlet(name="reviewEvaluate",urlPatterns="/reviewEvaluate")
public class ReviewEvaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewEvaluate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get session object to get url parameters
		HttpSession session = request.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
		// Check if user is authenticated
		if (bean == null) {
			request.setAttribute("exception", "Necesitas estar autenticado");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request,response);
		}
		else {
		 	response.setContentType("text/html");
			// Check if url parameter don't exists
			if (request.getParameter("r") == null) {
				request.setAttribute("exception", "Url invalida");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
			// If url is correct
			else {
				//Read information from context
				String urlDB = getServletContext().getInitParameter("DBurl");
		        String userDB = getServletContext().getInitParameter("DBuser");
		        String passDB = getServletContext().getInitParameter("DBpass");
		        String file = getServletContext().getInitParameter("properties");   
		        java.util.Properties prop = new java.util.Properties();
		        prop.load(getServletContext().getResourceAsStream(file));
				PrintWriter out = response.getWriter();

		        
				int reviewId = Integer.parseInt(request.getParameter("r"));
				ReviewDAO rDAO = new ReviewDAO(urlDB,userDB,passDB,prop);
				ReviewDTO review = rDAO.getById(reviewId);
				if (review == null) {
					out.println("<h1>La reseña no existe</h1>");
				}
				else {					
					out.println("Evaluación de la reseña "+ review.getText());
					out.println("<form action='reviewEvaluate' method='POST' id=\"evaluateForm\">");
	        		out.println("<input name=\"review_id\" value=\""+review.getId()+"\" type=hidden />");
	        		out.println("<input name=\"espectaculo_id\" value=\""+review.getEspectacle()+"\" type=hidden />");

					out.println("<select id=\"points\" name=\"points\">");
	        		out.println("<option value=\"1\">1</option>");
	        		out.println("<option value=\"2\">2</option>");
	        		out.println("<option value=\"3\">3</option>");
	        		out.println("<option value=\"4\">4</option>");
	        		out.println("<option value=\"5\">5</option>");
		        	out.println("</select>");
		        	out.println("<input type=\"submit\"/>");
		        	out.println("</form>");
				}
				
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get information from request
		int points = Integer.parseInt(request.getParameter("points"));
		int review = Integer.parseInt(request.getParameter("review_id"));
		int espectacle = Integer.parseInt(request.getParameter("espectaculo_id"));

		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");   
        java.util.Properties prop = new java.util.Properties();
        prop.load(getServletContext().getResourceAsStream(file));
        HttpSession session = request.getSession();
        CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
        ReviewDAO rDAO = new ReviewDAO(urlDB,userDB,passDB,prop);
		rDAO.evaluateReview(bean.getId(), points, review, espectacle);
		// TODO: Redirect to next screen, ?????????
	}

}
