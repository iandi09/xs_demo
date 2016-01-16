<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<div class="container">
	<form:form class="form-signin" commandName="userCommand" method="POST">
		<h2 class="form-signin-heading">Benutzerdaten</h2>
		<label for="username" class="sr-only">Benutzername</label> 
		<input type="text" id="username" class="form-control" name="username"
			placeholder="Benutzername" required autofocus> 
		<label for="password" class="sr-only">Password</label> 
		<input type="password" id="password" class="form-control" name="password"
			placeholder="Password" required>
		<label for="password2" class="sr-only">Password</label> 
		<input type="password" id="password2" class="form-control" name="password2"
			placeholder="Password wiederholen" required>
		<label for="email" class="sr-only">Benutzername</label> 
		<input type="email" id="email" class="form-control" name="email"
			placeholder="E-Mail-Adresse" required> 
		<div class="checkbox">
			<label> 
				<input type="checkbox" value="remember-me">
				Admin?
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Anlegen
		</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form:form>
</div>

</body>
</html>