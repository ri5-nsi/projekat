<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teams</title>


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

<div>


	<div >
      <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Teams preview</div>
        <div class="panel-body">
          <a href="">Add team</a>
        </div>

        <!-- Table -->
        <table class="table">
          <thead>
            <tr>
           
              <th>#</th>
              <th>Team name</th>
             
            </tr>
          </thead>
          <tbody>
           
           
             <c:forEach items="${teams}" var="team">
         		<tr>
        	  <td>1</td>
              <td>${team.name}</td>
               </tr>
    		</c:forEach>
              
              
           
           
          </tbody>
        </table>
      </div>
    </div>



</div>
</body>
</html>