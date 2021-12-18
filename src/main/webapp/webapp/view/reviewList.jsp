<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.ReviewDTO,java.util.HashMap" %>

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
			HashMap<ReviewDTO,String> reviews = (HashMap<ReviewDTO,String>) request.getAttribute("reviews");
			for (HashMap.Entry<ReviewDTO,String> it : reviews.entrySet()){
				%><tr><td><%out.print(it.getValue());%></td></tr><%
			}
		
		%>
		</tbody>
	</table>
</body>
</html>