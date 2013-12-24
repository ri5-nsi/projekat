<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projects</title>

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

<script>
function displayDetails()
{
	var fields=document.getElementById("projects").value.split(",");
	document.getElementById("details").style.display="inline";
	document.getElementById("name").value=fields[0];
	document.getElementById("budget").value=fields[1];
	document.getElementById("code").value=fields[2];
	document.getElementById("description").value=fields[3];
	document.getElementById("startOn").value=fields[4];
	document.getElementById("endOn").value=fields[5];
	document.getElementById("id").value=fields[6];
	

	
}

function editProject()
{
	document.getElementById("editProject").style.display="none";
	document.getElementById("pushEdit").style.display="inline";
	document.getElementById("name").removeAttribute("readonly",0);
	document.getElementById("name").style.border="thin solid ";
	
	document.getElementById("budget").removeAttribute("readonly",0);
	document.getElementById("budget").style.border="thin solid ";
	
	document.getElementById("code").removeAttribute("readonly",0);
	document.getElementById("code").style.border="thin solid ";
	
	document.getElementById("description").removeAttribute("readonly",0);
	document.getElementById("description").style.border="thin solid ";
	
	document.getElementById("startOn").removeAttribute("readonly",0);
	document.getElementById("startOn").style.border="thin solid ";
	
	document.getElementById("endOn").removeAttribute("readonly",0);
	document.getElementById("endOn").style.border="thin solid ";
	}
</script>
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
<jsp:include page="Layout.jsp"></jsp:include>
<div class="panel-body">
<div class="panel-heading">Select a project to preview details:</div>
<select name="projects" id="projects" style="width: 200px" onchange=displayDetails()>
<c:forEach items="${projects}" var="project">
        <option value="${project.name}, ${project.budget}, ${project.code}, ${project.description}, ${project.startOn}, ${project.endOn}, ${project.id}">${project.name }</option>
    </c:forEach>
</select>
<div id="details" style="display:none">
<div class="panel-heading">Project details <br> </div>
<form method="post" action="projects">
<input type="hidden" name="id" id="id">
Name: <input id="name" type="text" treadonly style="border-style:none" name="name"></input> <br>
Code: <input id="code" type="text" readonly style="border-style:none" name="code"></input> <br>
Available budget: <input id="budget" type="text" readonly style="border-style:none" name="budget"></input> <br>
Description: <input id="description" type="text" readonly style="border-style:none" name="description"></input> <br>
Starts on: <input id="startOn" type="text" readonly style="border-style:none" name="startOn"></input> <br>
Ends on: <input id="endOn" type="text" readonly style="border-style:none" name="endOn"></input> <br>
<input type="submit" style="display:none" id="pushEdit" value="Save Changes">
<a id="editProject" onclick="editProject()">Edit</a>
</form>



</div>
</div>
</div>
</body>
</html>