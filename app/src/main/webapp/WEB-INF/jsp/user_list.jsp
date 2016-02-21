<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<style>
	
.page-header {
	max-width: 1100px;
	margin: 0 auto;
	float:none;
}	
	
</style>

<div class="page-header col-md-6">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>User name</th>
				<th>E-Mail</th>
				<th>Additional info</th>
				<th>Admin</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.info}</td>
				<td>
					<c:choose>
						<c:when test="${user.vip}">Yes</c:when>
					<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${isAdmin}">
				<td style="width:20px;"><a href="/user_edit?user=${user.username}"><img src="/static/images/edit.png"/></a></td>
				</c:if>
			</tr>
			</c:forEach>
		</tbody>
		
	</table>
</div>

</body>
</html>
