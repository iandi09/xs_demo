<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

<html>

<head>

<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/signin.css" rel="stylesheet">

</head>

<body>

<form action="/j_spring_security_logout" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>


<nav class="navbar navbar-inverse">
	<div class="container">
		
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li>
					<a class="navbar-brand" href="/home">XS Demo</a>
				</li>
				<c:if test="${!loggedIn}">
				<li class="active">
					<a href="/user_list">Benutzerliste</a>
				</li>
				<li>
					<a href="/add_user">Benutzerdaten ändern</a>
				</li>
				<c:if test="${isAdmin}">
				<li>
					<a href="/add_user">Benutzer anlegen</a>
				</li>
				<li style="margin-left: 450px;">
					<a href="javascript:formSubmit();">Logout (${username})</a>
				</li>
				</c:if>
				</c:if>
			</ul>
		</div>
	</div>
</nav>