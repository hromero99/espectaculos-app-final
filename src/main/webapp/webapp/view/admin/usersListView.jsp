<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, java.util.HashMap"%>

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
					HashMap<UserDTO,String> usersList = (HashMap<UserDTO,String>) request.getAttribute("usersList");
					for (HashMap.Entry<UserDTO,String> it: usersList.entrySet()){
						%><tr>
							<td><%out.print(it.getKey().getName());%></td>
							<td><%out.print(it.getKey().getSurnames());%></td>
							<td><%out.print(it.getKey().getNick());%></td>
							<td><%out.print(it.getKey().getEmail());%></td>
							<td><%if (it.getKey().isAdministrator()){
								out.print("Es Administrator");
							}else{
								out.print("No es Administrador");
							}
							%></td>
							<td><%out.print(it.getKey().getPassword());%></td>
							<td><%out.print(it.getKey().getCreationDate());%></td>
							<td><%out.print(it.getKey().getPassword());%></td>
							<td><%out.print(it.getValue());%></td>
							
						</tr>
					
					<%}%>
				<%}
				
			%>
		</tbody>
	</table>
</body>
</html>