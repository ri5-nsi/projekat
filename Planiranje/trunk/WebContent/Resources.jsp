<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resources</title>
<style>
img
{
border:0;
opacity:0.7;
}
img:hover
{
opacity:1.0;
}
a
{
color: #A0A0A0;
text-decoration:none;
}
span
{
font-family:Verdana;
font-size:12px;
font-weight:bold;
padding-left:20px;
}
p
{
font-family:Verdana;
}
</style>
<link rel="stylesheet" type="text/css" href="styles/style.css" />

</head>
<body>

<jsp:include page="Layout.jsp"></jsp:include>

<p>Select a project to preview resources:</p>
<br>
<form>
<select name="projects" id="projects" style="width: 200px" onchange=this.form.submit() >
<c:forEach items="${projects}" var="project">
        <option value="${project.id}">${project.name }</option>
        
    </c:forEach>
</select>
</form>

<p>Displaying results for selected project:</p><br>
   <table border="1" style="margin-top:5px;">
   <tr>
   <th>Resource Name</th>
   <th>Quantity</th>
   <th>Delete</th>
   <th>Update</th>
   </tr>
   <c:forEach items="${resources}" var="resource">
   <tr>
       <td>${resource.name}</td>
       <td>${resource.quantity}</td>
       <td><a href="/ProjectPlanning/resources?resourceId=${resource.id}&projectId=nema&delete=brisi">Delete</a></td>    
       <td><a href="UpdateResource.jsp?resourceId=${resource.id}&name=${resource.name}&quantity=${resource.quantity}">Update</a></td>
   </tr>
</c:forEach>
   </table>
  
</body>
</html>