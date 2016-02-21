<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<c:choose>
	<c:when test="${currentUrl == '/user_add'}">
		<c:set var="title" value="New user" />
		<c:set var="button" value="Create user" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Edit user" />
		<c:set var="button" value="Save" />
	</c:otherwise>
</c:choose>

<div class="container">
	<form:form id="userForm" class="form-signin" commandName="userCommand" method="post">
		<h2 class="form-signin-heading">${title}</h2>
		
		<form:input path="username" type="text" class="form-control" placeholder="User name" readonly="${!userCommand.newUser}"/>
		
		Cleartext: <p id="copied" style="display: inline;"></p>
		<form:input path="password" type="password" class="form-control" placeholder="Password"/> 
		
		<form:input path="password2" type="password" class="form-control" placeholder="Repeat password"/>
		
		<form:input path="email" type="email" class="form-control" placeholder="E-Mail" required="true"/>
		
		<form:input path="info" type="text" class="form-control" placeholder="Additional info"/>
			
		<div class="checkbox" <c:if test="${!isAdmin}">style="display:none"</c:if>>
			<label>
				<form:checkbox path="vip"/>Admin
			</label>	
		</div>
		
		<button class="btn btn-lg btn-primary btn-block">
			${button}
		</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<form:hidden path="newUser"/>
	</form:form>
</div>

</body>
</html>