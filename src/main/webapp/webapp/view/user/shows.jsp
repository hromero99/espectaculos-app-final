<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.UserDTO, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tbody>
		<%
			List<String> showsInformation = (List<String>) request.getAttribute("shows");
			for (String it: showsInformation){
				String[] showInfo = it.split(",");
				%><tr>
				<td><%=showInfo[1]%></td>
				<td><%=showInfo[2]%></td>
				<td><%=showInfo[3]%></td>
				<td><%=showInfo[4]%></td>
				<td><a href="ReviewShowList?e=<%=showInfo[0]%>">asd</a></td>	
				<td><a href="ReviewPublish?e=<%=showInfo[0]%>">Write Review</a></td>					
			</tr>
			<%}%>
		</tbody>
	</table>
</body>
</html>