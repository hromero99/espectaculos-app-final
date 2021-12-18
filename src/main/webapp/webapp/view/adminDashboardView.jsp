<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="es.uco.pw.business.dto.UserDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
<p>Welcome Admin</p>
<p>List of users:</p>
<ul>
	<%
		String[] users = request.getParameter("message").split("\n");
		for (String userInformation:users){
			String[] userInfo = userInformation.split(",");
		%>
			<li><h3>Usuario: <%=userInfo[0]%></h3></li>
			<li><%=userInfo[1]%></li>
			<li><%=userInfo[2]%></li>
			<li><%=userInfo[3]%></li>
			<li><%=userInfo[4]%></li>

		<%}%>
</ul>
</body>
</html>