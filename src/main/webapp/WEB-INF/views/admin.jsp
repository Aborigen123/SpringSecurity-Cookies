<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@ include file = "taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Admin</h2>

<sec:authorize access="isAuthenticated()">
<sec:authentication property = "principal.username" var = "user" /> <!-- получаємо імя користувача витягуємо з нашої сесії (кукі) -->
<sec:authorize access="hasRole('ROLE_ADMIN')"> <!-- повідомлення виведе тільки адміну ще є команда hasAnyRole працюють ідентично-->
Hello, I am admin - ${user}  <%--  ${user} (user) беремо з var = "user" + виведе дані користувача  --%>
</sec:authorize>
</sec:authorize>


</body>
</html>