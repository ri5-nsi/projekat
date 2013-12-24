<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New team</title>


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
<jsp:include page="CreateTeam.jsp"></jsp:include>
<div>
<p>Add new team:</p>

	<form role="form" action="Teams.jsp" >
      <div class="input-group input-group-lg">
        <input name="id_projekta" type="hidden" value="id_projekta">
       
        <input name="team_name" type="text" placeholder="Team name" class="form-control">
        <input type="submit" value="ADD">
      </div>
      
    
    </form>



</div>
</body>
</html>