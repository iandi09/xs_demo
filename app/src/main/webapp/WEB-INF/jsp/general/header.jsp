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
	
	$(document).ready(function(){
		$("#password").keyup(function(){
			$("#copied").text($(this).val());
		})
	});
</script>

</head>

<body>

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>

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
				<li <c:if test="${currentUrl == '/user_list'}">class="active"</c:if>>
					<a href="/user_list">User list</a>
				</li>
				<li <c:if test="${currentUrl == '/user_edit'}">class="active"</c:if>>
					<a href="/user_edit">Edit user data</a>
				</li>
				<c:if test="${isAdmin}">
				<li <c:if test="${currentUrl == '/user_add'}">class="active"</c:if>>
					<a href="/user_add">Create new user</a>
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