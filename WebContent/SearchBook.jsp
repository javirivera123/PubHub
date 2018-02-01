<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 	Just some stuff you need -->
<header>
	<div class="container">

		<c:choose>
			<c:when test="${not empty message }">
				<p class="alert ${messageClass}">${message }</p>
				<%
					session.setAttribute("message", null);
							session.setAttribute("messageClass", null);
				%>
			</c:when>
		</c:choose>

		<h1>
			PUBHUB <small>Book search by tag</small>
		</h1>
		<hr class="book-primary">

		<form action="queryTag" method="post" class="form-horizontal">
			<table>
				<tr>
					<td>Tags:</td>
					<td><input type="text" name="tagQuery" /></td>
				</tr>
				<tr>
					<td></td>
					<td><button type="submit">Search Tags</button></td>
				</tr>
			</table>
		</form>
	</div>
	
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />