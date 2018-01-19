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
 * Servlet to get list of books with tags
 */
@WebServlet("/queryTag")
public class QueryTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("tagQuery");
		

		TagDAO tagDao = DAOUtilities.getTagDAO();
		List<Book> bookList = tagDao.getAllBooksWithTag(name);
		
		request.getSession().setAttribute("books", bookList);
		
		
		request.getRequestDispatcher("searchTagResult.jsp").forward(request, response);

	}
}
