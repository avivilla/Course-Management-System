<%@ page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
<link rel="stylesheet" href="css/liststyle.css" type="text/css">
<style>
</style>
</head>
<body>
	<ul>
		<li><a href="logout">Log Out</a></li>
		<li><a class="active">My Courses</a></li>


		<li style="float: left; font-size: 15px"><a>Ecademy</a></li>
	</ul>
  <h2 align="center">My Courses</h2>
	<table >
		<tr>
			<th align="center">Course Code</th>
			<th align="center">Course Title</th>
			<th></th>
		</tr>
	<%
			int count = 0;
			String color = "#F9EBB3";
			if (request.getAttribute("courselist") != null) {
				ArrayList al = (ArrayList) request.getAttribute("courselist");
				System.out.println(al);
				Iterator itr = al.iterator();
				while (itr.hasNext()) {

					if ((count % 2) == 0) {
						color = "#eeffee";
					}
					count++;
					ArrayList pList = (ArrayList) itr.next();
		%>
		<tr>
			<td align="center"><%=pList.get(0)%></td>
			<td align="center"><%=pList.get(1)%></td>
			<td align="center"><form action="CourseDetails">
					<input type="hidden" name="code" value="<%=pList.get(0)%>">
					<input type="submit" value="details"
						class="btn btn-secondary btn-lg1 scroll">
				</form></td>
		</tr>
		<%
			}
			}
			if (count == 0) {
		%>
		<tr>
			<td colspan=4 align="center" style="background-color: #eeffee"><b>You are not taking any course
					</b></td>
		</tr>
		<%
			}
		%> 
	</table>

</body>
</html>