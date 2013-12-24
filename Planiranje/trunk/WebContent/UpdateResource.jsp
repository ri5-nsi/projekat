<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Resource</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="panel panel-default">
	<div class="panel-body">
 <form action="resources" >
  Name: <input id="name" name="name" value="<%=request.getParameter("name")%>" type="text" /><br>
  Quantity: <input id="quantity" name="quantity" value="<%=request.getParameter("quantity")%>" type="text" /><br>
  <input type="hidden" name="resourceId" value="<%=request.getParameter("resourceId")%>" /><br>
  <input type="submit" name="update" value="Save" />
  </form>
  </div>
  </div>
</body>
</html>