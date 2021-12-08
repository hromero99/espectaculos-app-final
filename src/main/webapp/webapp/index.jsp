<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page errorPage="errorPage.jsp" %>
<%@page import="java.util.Date" %>
    
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
			<a href="./mvc/view/loginView.jsp">Login</a>
			<a href="./mvc/view/registerView.jsp">Register</a>
			<%
		}
		else{%>

			<form method="post" action="../controller/updateUserController.jsp">
                                       
                     <label for="email">Nombre: </label>
                     <input type="text" name="name" value=<%=customerBean.getName() %> placeholder="Nombre"></br>  <label for="email"> </label>
                     
                     <label for="email">Apellidos: </label>
                     <input type="text" name="surnames" value=<%=customerBean.getSurnames() %> placeholder="Apellidos"></br>  <label for="email"> </label>
                     
                     <label for="email">Nombre de usuario (nick): </label>
                     <input type="text" name="nick" value=<%=customerBean.getNick() %> placeholder="Apellidos"></br>  <label for="email"> </label>
                     
                     <label for="email">Email: </label>
                     <input type="email" readonly="readonly" name="email"value=<%=customerBean.getEmailUser() %>  placeholder="Email"></br>

                     <label for="password">Password: </label>
                     <input type="password" name="password" value=<%=customerBean.getPassword() %> placeholder="Password"></br>
					
                    <!-- send button -->
                    <input type="submit" value="Submit"/>
                </form>
		<%
			if (customerBean.getAdministrator()){
				%>
				<ul>
					<%
						String[] users = request.getParameter("message").split("\n");
						for (String userInformation:users){
							String[] userInfo = userInformation.split(",");
						%>
							<li>
								<h3>Usuario: <%=userInfo[0]%></h3>
								<p>Acceso: <%=userInfo[1]%></p>
							</li>
											
						<%}%>
				</ul>
				
				
                
				<a href="../controller/logoutController.jsp">Logout</a>
				<%
			}
			else{%>
				<p>Welcome <%=customerBean.getEmailUser()%>!</p>
				<%
				    Date date=new Date();
				    out.print("<p>Actual Date: "+date.toString()+"</p>");
				    out.print("<p>Creation Date :"+ request.getParameter("message")+"</p>");
				%>
				

				<a href="../controller/logoutController.jsp">Logout</a>
			<%}
		}
	%>
</body>
</html>