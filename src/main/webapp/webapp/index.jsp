<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<jsp:useBean id="customerBean" class="es.uco.pw.display.javabean.CustomerBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Espectaculos</title>
</head>
<body>
	
	<%
		if (customerBean.getEmailUser() == null){
			%>
			<a href="login">Login</a>
			<a href="register">Register</a>
			<%
		}
		else{%>
		<%
			response.sendRedirect("login");
		}%>
</body>
</html>