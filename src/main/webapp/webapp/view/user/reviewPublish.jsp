<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				out.print(request.getParameter("e"));
			%>
		</select>
		<input type="submit"/>
	</form>
	<textarea form="reviewPublish" name="review_text">Insert Text</textarea>
</body>
</html>