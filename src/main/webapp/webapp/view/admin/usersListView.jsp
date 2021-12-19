<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de usuarios</title>
</head>
<body>
	<%@include file="/includes/header.html" %>
	<table>
		<tbody>
			<%
				if(request.getAttribute("usersList") == null){
					out.print("<p>No existe ningún usuario en el sistema</p>");
				}
				else{
					List<String> usersList = (List<String>) request.getAttribute("usersList");
					for (String it: usersList){
						String[] userInfo = it.split(",");
						%><tr>
							<td><%out.print(userInfo[0]);%></td>
							<td><%out.print(userInfo[1]);%></td>
							<td><%out.print(userInfo[2]);%></td>
							<td><%out.print(userInfo[3]);%></td>
							<td><%if (userInfo[4].equals("1")){
								out.print("Es Administrator");
							}else{
								out.print("No es Administrador");
							}
							%></td>
							<td><%out.print(userInfo[5]);%></td>
							<td><%out.print(userInfo[6]);%></td>
							<td><%out.print(userInfo[7]);%></td>
							
						</tr>
					
					<%}%>
				<%}
				
			%>
		</tbody>
	</table>
</body>
</html>