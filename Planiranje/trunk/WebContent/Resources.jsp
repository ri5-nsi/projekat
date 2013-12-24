<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Resources</title>
<script>
document.getElementById('projects').onchange = function() {
	var input = document.getElementById('greska');
	   if(input.style.backgroundColor=="red") {
		   input.style.backgroundColor="white";
		   input.style.visibility="hidden";
	   }
   
   
};

function validacija() {
	var forma=document.getElementById('forma');
	var projektId=forma.projectId.value;
	if (projektId.length == 0 || projektId.localeCompare("null")==0){
		    forma.greska.style.visibility='visible';
		    forma.greska.value="Odaberite projekat";
		    forma.greska.style.backgroundColor="red";
		    forma.greska.focus();
	    	return false;
	}
	return true;
};
</script>


<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

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
<div class="panel panel-default">
<%
    String projectId = (String)request.getAttribute("projectId");
%>
<jsp:include page="Layout.jsp"></jsp:include>
<div class="panel-body">
<div class="panel-heading">Select a project to preview resources:</div>
<br>
<form>
<select name="projects" id="projects" style="width: 200px" onchange=this.form.submit() >
<c:forEach items="${projects}" var="project">
        <option value="${project.id}">${project.name }</option>
</c:forEach>
</select>

</form>

<div class="panel-heading">Displaying results for selected project:</div><br>
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
       <td><a href="/ProjectPlanning/resources?resourceId=${resource.id}&projectId=<%=projectId%>&delete=brisi">Delete</a></td>    
       <td><a href="UpdateResource.jsp?resourceId=${resource.id}&name=${resource.name}&quantity=${resource.quantity}">Update</a></td>
   </tr>
</c:forEach>
   </table>

  <form method="get" action="AddResource.jsp" id="forma" onsubmit="return validacija();" >
    <input type="hidden" name="projectId" id="projectId" value="<%=projectId%>" />
    <input type="submit" value="Add Resource" /> <br>
    <input type="text" id="greska" style="visibility:hidden" />
  </form>

   </div>
  </div>

</body>
</html>