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
					for (UserDTO it: (List<UserDTO>)request.getAttribute("usersList")){
						%><tr>
							<td><%out.print(it.getName());%></td>
							<td><%out.print(it.getSurnames());%></td>
							<td><%out.print(it.getNick());%></td>
							<td><%out.print(it.getEmail());%></td>
							<td><%if (it.isAdministrator()){
								out.print("Es Administrator");
							}else{
								out.print("No es Administrador");
							}
							%></td>
							<td><%out.print(it.getPassword());%></td>
						</tr>
					
					<%}%>
				<%}
				
			%>
		</tbody>
	</table>
</body>
</html>