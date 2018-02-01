

	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
		<%
			//display message if present
			if (request.getAttribute("message") != null){
				out.println("<p class=\"alert alert-danger\">" + request.getAttribute("message") + "</p>");
			}
		%>
		<h1>PUBHUB <small>Add Tag</small></h1>
		<hr class="book-primary">
		
              <form action="AddTagtoBook" method="post" class="form-horizontal">
				<div class="tag-form form-group">
				<label for="tags" class="col-sm-4 control-label">Enter new tag for book</label>
					<div class="tagDiv col-sm-5">
		     		 <input type="text" class="tag form-control" name="tag" required="required" />
		     		 <input type="hidden" name="isbn13" value="<%= request.getParameter("isbn13")%>">
		     		  <button type="submit" class="SubmitTag" >Submit Tag</button>
		   			</div>
		   			</div>
				</form>
     </div>
	</header>
	
	

	<!-- Footer -->
	<jsp:include page="footer.jsp" />