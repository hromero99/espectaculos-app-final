package es.uco.pw.serverlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.data.dao.EspectaculoDAO;
import es.uco.pw.display.javabean.CustomerBean;


/**
 * Servlet implementation class ListarEspectaculos
 */
@WebServlet(name="showList",urlPatterns="/show/list")
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //List all shows

		// Get session object to get url parameters
		HttpSession session = request.getSession();
		CustomerBean bean = (CustomerBean) session.getAttribute("customerBean");
		
		// Check if user is authenticated
		if (bean == null) {
			request.setAttribute("exception", "Necesitas estar autenticado");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request,response);
		} else {
			List<String> espectaculosInformation = new ArrayList<String>();
			String urlDB = getServletContext().getInitParameter("DBurl");
	        String userDB = getServletContext().getInitParameter("DBuser");
	        String passDB = getServletContext().getInitParameter("DBpass");
	        String file = getServletContext().getInitParameter("properties");
	        
	        java.util.Properties prop = new java.util.Properties();
	        prop.load(getServletContext().getResourceAsStream(file));
	
	        EspectaculoDAO sDao = new EspectaculoDAO(urlDB,userDB,passDB,prop);
	        ArrayList<EspectaculoDTO> shows = sDao.getAll();
	      	for (EspectaculoDTO it: shows) {
	      		espectaculosInformation.add(it.toCsv());
	      	}
	      	request.setAttribute("shows", espectaculosInformation);
	      	request.getRequestDispatcher("/view/user/shows.jsp").forward(request, response);
		
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
