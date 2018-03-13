<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

	<h1>Login page</h1>
	<c:if test="${param.fail}"></c:if>
	<p style="color: red">
	Fail to autorization
	</p>
	
	
	<form:form action="/login"  method="POST">
	<label>E-mail addres:</label>
	<input type = "email" name = "email">
	
	<%-- <form:input path="email"/> --%>
	<br>
	<label>Password:</label>
	<input type = "password" name = "password">
	<br>
	
	<%-- <form:input path="password"/> --%>
	
	<label>Remember Me:</label>
	<input type = "checkbox" name = "rememberme">
	<br>
	
	<input type="submit" value="Login">
	
</form:form>
</body>
</html>
