<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<div class="container">
	<form class="form-signin" action="/j_spring_security_check" method="POST">
		<h2 class="form-signin-heading">Please sign in</h2>
		<label for="inputUsername" class="sr-only">User name</label> 
		<input type="text" id="inputUsername" class="form-control" name="username"
			placeholder="User name" required autofocus> 
		<label for="inputPassword" class="sr-only">Password</label> 
		<input type="password" id="inputPassword" class="form-control" name="password"
			placeholder="Password" required>
		<div class="checkbox">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Signin
		</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</div>

</body>
</html>