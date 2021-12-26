<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.dto.ReviewDTO,java.util.List" %>


<%@include file="/includes/header.jsp"%>

	<table>
	
		<tbody>
		<%
			List<String> reviews = (List<String>) request.getAttribute("reviews");
			for (String it: reviews){
				String[] data = it.split(",");
				%><tr>
					<td><%out.print(data[0]);%></td>
					<td><%out.print(data[1]);%></td>
					<td><%out.print(data[2]);%></td>	
				</tr>
			<%
			}		
		%>
		</tbody>
	</table>
<%@include file="/includes/footer.jsp"%>
