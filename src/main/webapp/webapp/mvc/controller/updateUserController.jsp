<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, es.uco.pw.data.dao.userDAO,es.uco.pw.data.dao.userDAO,java.util.ArrayList" %>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
	String urlDB = getServletContext().getInitParameter("DBurl");
	String userDB = getServletContext().getInitParameter("DBuser");
	String passDB = getServletContext().getInitParameter("DBpass");
	String file = getServletContext().getInitParameter("properties");
	java.io.InputStream myIO = application.getResourceAsStream(file);
	//create properties object empty
	java.util.Properties prop = new java.util.Properties();
	//load file content
	prop.load(myIO);
	
	String nextPageMessage = "";
	
	UserDTO user = new UserDTO(
			customerBean.getId(),
			request.getParameter("name"),
			request.getParameter("surnames"),
			request.getParameter("nick"),
			request.getParameter("email"),
			customerBean.getAdministrator(), //TODO: Use administrator
			request.getParameter("password")
	);

	//PAss content to DAO for future query get
	userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
	uDao.update(customerBean.getId(), user);
			%>
	<jsp:setProperty property="id" value="<%=user.getId()%>" name="customerBean" ></jsp:setProperty>   	
	<jsp:setProperty property="emailUser" value="<%=user.getEmail()%>" name="customerBean" ></jsp:setProperty>   	
    <jsp:setProperty property="administrator" value="<%=customerBean.getAdministrator()%>" name="customerBean" ></jsp:setProperty>
    <jsp:setProperty property="name" value="<%=user.getName()%>" name="customerBean" ></jsp:setProperty>
    <jsp:setProperty property="surnames" value="<%=user.getSurnames()%>" name="customerBean" ></jsp:setProperty>
    <jsp:setProperty property="nick" value="<%=user.getNick()%>" name="customerBean" ></jsp:setProperty>   	
	<jsp:setProperty property="password" value="<%=user.getPassword()%>" name="customerBean" ></jsp:setProperty>   	
	<%
	String returnValue = "";
	ArrayList<UserDTO> users = uDao.getAll();
	String temp ="";
	if (customerBean.getAdministrator()){
		for (UserDTO u: users){
			String lastLogging = uDao.getLastLog(u.getId());
			if (lastLogging.equals("")){
				temp =  String.format("%s,El usuario no ha accedido\n",u.getEmail());
			}
			else{
	    		temp = String.format("%s,%s\n",u.getEmail(),lastLogging);
			}
			returnValue += temp;
		}
	}
	else{
		UserDTO userFromDB = uDao.getById(customerBean.getId());
		// The date of creation for user
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
    	returnValue = df.format(userFromDB.getCreationDate());
	}
	

%>
<jsp:forward page="/index.jsp">
    <jsp:param value="<%=returnValue%>" name="message"/>
</jsp:forward>
