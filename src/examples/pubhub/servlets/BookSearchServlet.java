package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;
import examples.pubhub.dao.TagDAO;

/*
 * This servlet will take you to the search page for the Book Publishing module (level 100)
 */
@WebServlet("/BookSearch")
public class BookSearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the list of Books & tags from the Database
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
		
		TagDAO dao2 = DAOUtilities.getTagDAO();
		List<Book> bookWtags = dao2.getAllBooksWithTag("fiction");
		

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		request.getSession().setAttribute("bookwtags", bookWtags);
		
		request.getRequestDispatcher("SearchBook.jsp").forward(request, response);
	}
}
