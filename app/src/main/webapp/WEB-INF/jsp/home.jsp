<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/jsp/general/header.jsp" />

<style>

#home-container {
	max-width: 500px;
}

</style>

<script>

function search() {
	var query = $(searchField).val();
	if (query != '') {
		window.location.href = "/home?q=" + query;
	} else {
		window.location.href = "/home";
	}
}

</script>

<div id="home-container" class="form-signin">

	<h2 class="form-signin-heading">Suche</h2>

	<label for="search" class="sr-only">Suche</label> 
	<input type="text" id="searchField" class="form-control" placeholder="Suchbegriff" required>

	<button class="btn btn-lg btn-primary btn-block" onclick="javascript:search()">
		Suche starten
	</button>
	
	<c:if test="${not empty query}">
		<br>
		<p>Suche nach ${query}</p>
		<p>Nichts gefunden.</p>
	</c:if>

</div>

</body>
</html>