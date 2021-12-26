<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action='ReviewPublish' method='POST' id="reviewForm">
		<select id="especaculo" name="espectaculo">
			<%
				List<String> info = (List<String>) request.getAttribute("esp");
				for (String x : info){
					String[] values = 	x.split(",");
					out.print("<option value=\""+values[0]+"\">"+values[1]+"</option>");
				}
				out.print(request.getParameter("e"));
			%>
		</select>
		<input type="text" name="review_text"/>
		<input type="submit"/>
	</form>
</body>
</html>