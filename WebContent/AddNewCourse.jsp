<%@ page import="java.util.*"%>
<!-- This UI is created by merging and modifying w3school's nav-bar and table template-->
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
		<li><a href="addteacher">Add Teacher</a></li>
		<li><a class="active" href="AddNewCourse">Add course</a></li>


		<li style="float: left; font-size: 15px"><a>Ecademy</a></li>
	</ul>
  <h2 align="center">Add New Course</h2>
	<table >
		<tr>
			<th align="center">Teacher ID</th>
			<th align="center">Teacher</th>
			<th></th>
		</tr>
	<!-- loop to show data -->
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
            <tr style="background-color:<%=color%>;">
           
                <td align="center"><%=pList.get(1)%></td>
                <td align="center"><%=pList.get(2)%></td>
             <td align="center"><form action="NewCourseForm">
             <input type = "hidden" name = "teacherid" value ="<%=pList.get(0)%>">
             <input type = "hidden" name = "teachername" value ="<%=pList.get(1)%>">
             <input type="submit"class="btn btn-secondary btn-lg1 scroll" value="add">
              </form></td>
                
            </tr>
            <%
                    }
                }
                if (count == 0) {
            %>
            <tr>
                <td colspan=4 align="center"
                    style="background-color:#eeffee"><b>No teacher to assign course</b></td>
            </tr>
            <%            }
            %>
	</table>

</body>
</html>