<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<script>

function submitUser(newUser){
	var hidden = $("<input type='checkbox' name='newUser' value='" + newUser + "'/>");
	var hidden2 = $("<input type='hidden' value='on' name='_newUser'/>");
	$(userForm).append(hidden);
	$(userForm).append(hidden2);
	$(userForm).submit();
}

</script>

<c:choose>
	<c:when test="${page == 'user_add'}">
		<c:set var="title" value="Neuer Benutzer" />
		<c:set var="button" value="Anlegen" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Benutzer ändern" />
		<c:set var="button" value="Speichern" />
	</c:otherwise>
</c:choose>

<div class="container">
	<form:form id="userForm" class="form-signin" commandName="userCommand" method="post">
		<h2 class="form-signin-heading">${title}</h2>
		
		<form:input path="username" type="text" class="form-control" placeholder="Benutzername"/>
		
		<form:input path="password" type="password" class="form-control" placeholder="Passwort"/>
		
		<form:input path="password2" type="password" class="form-control" placeholder="Passwort wiederholen"/>
		
		<form:input path="email" type="email" class="form-control" placeholder="E-Mail"/>
		
		<form:input path="info" type="text" class="form-control" placeholder="Zusatzinfo"/>
			
		<div class="checkbox">
			<label>
				<form:checkbox path="vip"/>Admin
			</label>	
		</div>
		
		<button class="btn btn-lg btn-primary btn-block" onclick="javascript:submitUser(${page == 'user_add'})">
			${button}
		</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form:form>
</div>

</body>
</html>