<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, es.uco.pw.data.dao.userDAO, es.uco.pw.display.javabean.CustomerBean" %>
    
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    
    
<%

//First, we check to see if user is already logged in, if it is, we redirect him to userDashboard.js

String redirectPage="../../index.jsp";
String message="";

//Checking if user is NOT logged in

if((session.getAttribute("email")==null || session.getAttribute("email")=="")){
	 //we store the username and call the DAO for that specific username
    String urlDB = getServletContext().getInitParameter("DBurl");
    String userDB = getServletContext().getInitParameter("DBuser");
    String passDB = getServletContext().getInitParameter("DBpass");
    String file = getServletContext().getInitParameter("properties");
    //open the file  sql.properties and load as Input Stream
    java.io.InputStream myIO = application.getResourceAsStream(file);
    // create properties object empty
    java.util.Properties prop = new java.util.Properties();
    // load file content
    prop.load(myIO);
    
    //PAss content to DAO for future query get
    userDAO uDao = new userDAO(urlDB,userDB,passDB,prop);
    UserDTO userFromDB = new UserDTO();
    if (uDao.searchUserByEmail(request.getParameter("email")) == null){
    	String data =String.format("%s,%s,%s,%s,%b,%s",request.getParameter("name"),request.getParameter("surnames"),request.getParameter("nick"),request.getParameter("email"),false,request.getParameter("password"));
    	UserDTO user = new UserDTO(data);
    	int userId = uDao.create(data);
    	%>
    		<jsp:setProperty property="id" value="<%=userId%>" name="customerBean" ></jsp:setProperty>   	
                   		<jsp:setProperty property="emailUser" value="<%=user.getEmail()%>" name="customerBean" ></jsp:setProperty>   	
            		    <jsp:setProperty property="administrator" value="<%=user.isAdministrator()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="name" value="<%=user.getName()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="surnames" value="<%=user.getSurnames()%>" name="customerBean" ></jsp:setProperty>
            		    <jsp:setProperty property="nick" value="<%=user.getNick()%>" name="customerBean" ></jsp:setProperty>   	
            			<jsp:setProperty property="password" value="<%=user.getPassword()%>" name="customerBean" ></jsp:setProperty>   		
    	
    	<%

    	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
    	java.util.Date current = new java.util.Date();
    	message = df.format(current);
    	
    }
    else{
    	redirectPage="../../index.jsp";
    }
    		
	
}
//If user is already logged in
else{
	redirectPage="../../index.jsp";
}
%>

<jsp:forward page="<%=redirectPage %>">
    <jsp:param value="<%=message%>" name="message"/>
</jsp:forward>