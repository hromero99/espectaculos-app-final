<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!-- Here we create the account if no account with the specified mail/user exists -->

<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Log in</title>
    </head>

    <body>
                <!-- We simply put the message on screen -->
				<%
				request.getParameter("message");
					if (request.getParameter("message") != null){
						out.print("<p>"+ request.getParameter("message") +"</p>");
					}
				%>               

                <!-- now we ask for data and we POST it. We send it to ../controller/loginController.jsp for now -->
			<form method="post" action="login">
                    <label for="email">Email: </label>
                    <input type="email" name="email" value="i72roloh@uco.es" placeholder="Email"></br>

                    <label for="password">Password: </label>
                    <input type="password" name="password" value="password" placeholder="Password"></br>

                    <!-- send button -->
                    <input type="submit" value="Submit">
                </form>
            <%
        %>
    </body>

</html>