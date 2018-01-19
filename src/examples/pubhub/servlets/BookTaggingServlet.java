package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class BookTaggingServlet
 */
@WebServlet("/BookTagging")
public class BookTaggingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isSuccess = true;
		String isbn13 = request.getParameter("isbn13");

		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);

		TagDAO tagDao = DAOUtilities.getTagDAO();
		List<String> tags = tagDao.getAllTagsFromBook(book);

		String search = request.getParameter("tagName");
		for (String str : tags) {
			if (str.trim().contains(search)) {
				// ASSERT: found existing tag with isbn. Update failed.
				isSuccess = false;
				break;
			}
		}
		
		if (isSuccess) {
			isSuccess = tagDao.addTag(search, book);
		}

		if (isSuccess) {
			request.getSession().setAttribute("message", "Book tag successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem updating this book tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}
