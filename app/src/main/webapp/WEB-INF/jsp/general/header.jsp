<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>

<head>

<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/signin.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>

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
				<li <c:if test="${page == 'user_list'}">class="active"</c:if>>
					<a href="/user_list">Benutzerliste</a>
				</li>
				<li <c:if test="${page == 'user_edit'}">class="active"</c:if>>
					<a href="/user_edit">Benutzerdaten ändern</a>
				</li>
				<c:if test="${isAdmin}">
				<li <c:if test="${page == 'user_add'}">class="active"</c:if>>
					<a href="/add_user">Benutzer anlegen</a>
				</li>
				</c:if>
				<li style="position:absolute; right:100px;;">
					<a href="javascript:formSubmit();">Logout (${username})</a>
				</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>