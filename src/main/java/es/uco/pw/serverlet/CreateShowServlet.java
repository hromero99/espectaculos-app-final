package es.uco.pw.serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.dto.EspectaculoDTO;
import es.uco.pw.data.dao.EspectaculoDAO;

/**
 * Servlet implementation class CreateShowServlet
 */
@WebServlet(name="CreateShow", urlPatterns="/show/create")
public class CreateShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/admin/createShowView.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String urlDB = getServletContext().getInitParameter("DBurl");
        String userDB = getServletContext().getInitParameter("DBuser");
        String passDB = getServletContext().getInitParameter("DBpass");
        String file = getServletContext().getInitParameter("properties");
	
        java.util.Properties prop = new java.util.Properties();
        prop.load(getServletContext().getResourceAsStream(file));
        EspectaculoDAO sDao = new EspectaculoDAO(urlDB,userDB,passDB,prop);

		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String cat = request.getParameter("cat");
		String loc = request.getParameter("loc");
		String TipoEspectaculo = request.getParameter("tipo");
        int NewEspectaculoID = sDao.create(title + "," +  desc + "," + cat + "," + loc);
        request.getRequestDispatcher("CreatePaseShow?e="+NewEspectaculoID+"&tipo="+TipoEspectaculo).forward(request, response);
        
	}

}
