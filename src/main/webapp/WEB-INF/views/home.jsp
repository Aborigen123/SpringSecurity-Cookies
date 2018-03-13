<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.username" var="user"/>
User authentication: ${user}

<form:form action="/logout" method="POST">
<input type="submit" value="logout">
</form:form>


</sec:authorize>



<sec:authorize access="!isAuthenticated()">
<h2>
Show login page: <a href="/login">Login</a>
</h2>
	</sec:authorize>
	
	<a href="/user">User</a>
	<a href="/admin">Admin</a>
	<a href="/products">Products</a>
	
</body>
</html>