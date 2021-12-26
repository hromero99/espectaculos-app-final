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
                 <h1>Login Form</h1>
                <div class="formcontainer">
                <hr/>
                <div class="container">
                  <label for="uname"><strong>Username</strong></label>
                  <input type="text" placeholder="Enter Username" name="email" required>
                  <label for="psw"><strong>Password</strong></label>
                  <input type="password" placeholder="Enter Password" name="password" required>
                </div>
                <button type="submit">Login</button>
                <div class="container" style="background-color: #eee">
                  <label style="padding-left: 15px">
                    <input type="checkbox"  checked="checked" name="remember"> Remember me
                  </label>
                  <span class="psw"><a href="#"> Forgot password?</a></span>
                </div>
             </form>
            <%
        %>
    </body>

</html>