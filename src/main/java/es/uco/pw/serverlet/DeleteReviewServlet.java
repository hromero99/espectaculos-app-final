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

import es.uco.pw.business.dto.ReviewDTO;
import es.uco.pw.data.dao.ReviewDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet(name="DeleteReview",urlPatterns="/DeleteReview")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("r") == null) {
			RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
			session.setAttribute("exception", "Url mal formada");
			view.forward(request, response);
		}
		else {
			int reviewId = Integer.parseInt(request.getParameter("r"));
			PrintWriter out = response.getWriter();
			// Need to check if user is logged, otherwise this operation can't be execute
			
			CustomerBean bean = (CustomerBean)session.getAttribute("customerBean");
			if (bean == null) {
				
				RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
				session.setAttribute("exception", "Debes de estar autenticado");
	
				view.forward(request, response);
			} 
			else {
					
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
					session.setAttribute("exception", "No se encuentra la Review");
		
					RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
					view.forward(request, response);
				}
				else {
				
					if (review.getCreator() == bean.getId()) {
						if (rDao.delete(review.getId())) {
							out.println("Review eliminada correctamente");
						}else {
							out.println("Ha ocurrido un error eliminado la critica");
						}
					}
					else {
						session.setAttribute("exception", "No eres el dueño");
						RequestDispatcher view = request.getRequestDispatcher("errorPage.jsp");
						view.forward(request, response);
					}
				}
			}
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
