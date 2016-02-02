<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<div class="container">
	<form class="form-signin" action="/j_spring_security_check" method="post">
		<h2 class="form-signin-heading">Bitte einloggen</h2>
		
		<label for="inputUsername" class="sr-only">Benutzername</label> 
		<input type="text" id="inputUsername" class="form-control" name="username"
			placeholder="Benutzername" required autofocus> 
			
		<label for="inputPassword" class="sr-only">Passwort</label> 
		<input type="password" id="inputPassword" class="form-control" name="password"
			placeholder="Passwort" required>
			
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Signin
		</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>

</body>
</html>