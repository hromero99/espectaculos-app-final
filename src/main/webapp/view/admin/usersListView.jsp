<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<link rel="stylesheet" href="static/css/users.css">	
        <link rel="stylesheet" href="static/css/users.css">
		<title>Listado de usuarios</title>
</head>

<body>
	<%@include file="/includes/header.jsp" %>

		 <main>
            <div class="main">
                <div class="list-users">
                    <ul class="list-users__items">
                    	<%
							if(request.getAttribute("usersList") == null){
								out.print("<p>No existe ningún usuario en el sistema</p>");
							}
							else{
								List<String> usersList = (List<String>) request.getAttribute("usersList");
								for (String it: usersList){
									String[] userInfo = it.split(",");
									%><li class="list-users__item">
										<p><%out.print(userInfo[0]);%></p>
										<p><%out.print(userInfo[1]);%></p>
										<p><%out.print(userInfo[2]);%></p>
										<p><%out.print(userInfo[3]);%></p>
										<p><%if (userInfo[4].equals("1")){
											out.print("Es Administrator");
										}else{
											out.print("No es Administrador");
										}
										%></p>
										<p><%out.print(userInfo[5]);%></p>
										<p><%out.print(userInfo[6]);%></p>
										<p><%out.print(userInfo[7]);%></p>
										
									</li>
								
								<%}%>
							<%}
							
						%>

                    </ul>

                </div>
            </div>
        </main>
		

</body>
</html>