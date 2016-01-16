<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
<form:form action="post" modelAttribute="loginCommand">
	<form:input path="username"/>
	<form:input path="password"/>
</form:form>
</body>
</html>