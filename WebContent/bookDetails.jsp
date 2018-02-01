
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Just some stuff you need -->
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
			PUBHUB <small>Book Details - ${book.isbn13 }</small>
		</h1>
		<hr class="book-primary">

		<form action="UpdateBook" method="post" class="form-horizontal">

			<input type="hidden" class="form-control" id="isbn13" name="isbn13"
				required="required" value="${book.isbn13 }" />

			<div class="form-group">
				<label for="title" class="col-sm-4 control-label">Title</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Title" required="required" value="${book.title }" />
				</div>
			</div>
			<div class="form-group">
				<label for="author" class="col-sm-4 control-label">Author</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="author" name="author"
						placeholder="Author" required="required" value="${book.author }" />
				</div>
			</div>
			<div class="form-group">
				<label for="price" class="col-sm-4 control-label">Price</label>
				<div class="col-sm-5">
					<input type="number" step="0.01" class="form-control" id="price"
						name="price" placeholder="Price" required="required"
						value="${book.price }" />
				</div>
			</div>

			<c:forEach var="tag" items="${tags}">

				<div class="tag-form form-group" id="${tag}">
					<!-- list of tags  -->
					<label for="tags" class="col-sm-4 control-label">Tag</label>
					<div class="tagDiv col-sm-5">
						<input type="text" class="tag form-control" name="tag"
							required="required" value="${tag}" readonly />
						<button type="button" class="DeleteTag" value="${tag}">Delete
							Tag</button>
					</div>
				</div>

			</c:forEach>


			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-info">Update</button>
				</div>
			</div>
		</form>

		<form action="AddTagtoBook.jsp" method="post">
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-info" id="Add">AddTag</button>
					<input type="hidden" name="isbn13" value="${book.isbn13}" />
				</div>
			</div>
		</form>


	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />
