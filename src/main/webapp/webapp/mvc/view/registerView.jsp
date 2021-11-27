<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Usuario</title>
</head>
<body>

<form method="post" action="../controller/userRegisterController.jsp">
                    
                 
                                       
                     <label for="email">Nombre: </label>
                     <input type="text" name="name" value=<%=customerBean.getName() %> placeholder="Nombre"></br>  <label for="email"> </label>
                     
                     <label for="email">Apellidos: </label>
                     <input type="text" name="surnames" value=<%=customerBean.getSurnames() %> placeholder="Apellidos"></br>  <label for="email"> </label>
                     
                     <label for="email">Nombre de usuario (nick): </label>
                     <input type="text" name="nick" value=<%=customerBean.getNick() %> placeholder="Apellidos"></br>  <label for="email"> </label>
                     
                     <label for="email">Email: </label>
                     <input type="email" name="email"value=<%=customerBean.getEmailUser() %>  placeholder="Email"></br>

                     <label for="password">Password: </label>
                     <input type="password" name="password" value=<%=customerBean.getPassword() %> placeholder="Password"></br>
					
                    <!-- send button -->
                    <input type="submit" value="Submit"/>
</form>


</body>
</html>