package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 *  servlet to get to add tag page
 */
@WebServlet("/AddTagtoBook")
public class AddTagtoBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= true;
		String isbn13 = request.getParameter("isbn13");
		String tag = request.getParameter("tag");
		
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);
		TagDAO dao2 = DAOUtilities.getTagDAO();
		
		
		if(book != null){
			List<String> tags = dao2.getAllTagsFromBook(book);
			String search = tag;
			for (String str : tags) {
				if (str.trim().contains(search)) {
					// ASSERT: found existing tag with isbn. Update failed.
					isSuccess = false;
					break;
				}
			}
			if(isSuccess) {
			request.setAttribute("book", book);
			isSuccess = dao2.addTag(tag, book);
			}
		}else {
			//ASSERT: couldn't find book with isbn. Update failed.
			isSuccess = false;
		}
		
		if(isSuccess){
			request.getSession().setAttribute("message", "Book Tag successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem updating this book tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
		

	}
}