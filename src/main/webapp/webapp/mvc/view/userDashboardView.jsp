<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
</head>
<body>
<%
if(customerBean != null){
	if (customerBean.getEmailUser() == null){
		%><jsp:forward page="/index.jsp">
    	<jsp:param value="Usuario no autorizado" name="message"/>
	</jsp:forward>
	<%}}
%>
<p>Welcome <%=customerBean.getEmailUser()%>!</p>
<%
    Date date=new Date();
    out.print("<p>Date: "+date.toString()+"</p>");
%>

<a href="/mvc/controller/logoutController.jsp">Logout</a>
</body>
</html>