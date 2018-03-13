<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Register</h2>

<form:form action="/register" method="POST" modelAttribute="registerModel">
<Label>E-mail address</Label>
<form:input path="email"/>
<br>
<Label>Password</Label>
<form:password path="password"/>
<br>
<Label>Comfirmation Password</Label>
<form:password path="passwordConfirmation"/>
<br>
<input type = "submit" value="Register new user">
</form:form>

</body>
</html>