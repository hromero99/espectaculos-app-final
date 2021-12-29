package es.uco.pw.serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatePaseShow
 */
@WebServlet(name="CreatePaseShow",urlPatterns="/CreatePaseShow")
public class CreatePaseShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePaseShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		String eId = request.getParameter("e");
		int Npases = -1;
		if (tipo.equals("m")) {
			Npases = 4 ;
			request.getRequestDispatcher("/view/admin/createShowPassView.jsp?nPases="+Npases).forward(request,response);
		}
		else if (tipo.equals("p")) {
			Npases = 1;
			request.getRequestDispatcher("/view/admin/createPasePuntual.jsp?nPases="+Npases).forward(request,response);
		}
		else {
			Npases = 2;
			request.getRequestDispatcher("/view/admin/createShowPassView.jsp?nPases="+Npases).forward(request,response);
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
