<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="newPase" method="post">
		<%
			int nPases = Integer.parseInt(request.getParameter("nPases"));
			for(int i = 0; i<nPases;i++){
				out.print("<input type=\"date\" placeholder=\"Fecha\" required=\"required\" name=\"date-"+i  +"\"/>" );
				out.print("<input type=\"time\" placeholder=\"Hora\" required=\"required\" name=\"time-"+i  +"\" />" );
			}
		
		%>
		<%
			if (request.getParameter("tipo").equals("t")) {
				%>		
				<input type="number" placeholder="Dia" name="day" required="required">
				<%
			}
		%>
		<input type="submit" value="New Pases">
	</form>
</body>
</html>