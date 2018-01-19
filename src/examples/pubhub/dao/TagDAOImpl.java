package examples.pubhub.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Implementation for the TagDAO, responsible for querying the database for Tag objects.
 */
public class TagDAOImpl implements TagDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/
	
	
	@Override 
	public List<Book> getAllBooksWithTag(String tag) {
		
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT b.* FROM books b LEFT OUTER JOIN book_tags bt ON b.isbn_13 = bt.isbn_13 WHERE tag_name=?";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			//stmt.setString(1, "%" + tag + "%");
			stmt.setString(1, tag );
			
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				Book book = new Book();

				// Each variable in our Book object maps to a column in a row from our results.
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				
				// The SQL DATE datatype maps to java.sql.Date... which isn't well supported anymore. 
				// We use a LocalDate instead, because this is Java 8.
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				
				// The PDF file is tricky; file data is stored in the DB as a BLOB - Binary Large Object. It's
				// literally stored as 1's and 0's, so this one data type can hold any type of file.
				book.setContent(rs.getBytes("content"));
				
				// Finally we add it to the list of Book objects returned by this query.
				books.add(book);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return books;
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<String> getAllTagsFromBook(Book book) {
		List<String> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT tag_name FROM book_tags WHERE isbn_13=?";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, book.getIsbn13());
	
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// Populate list
				tags.add(rs.getString("tag_name"));
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the String list of tag names populated by the DB.
		return tags;
	}


	/*------------------------------------------------------------------------------------------------*/

	
	@Override 
	public boolean addTag(String tag, Book book) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags WHERE VALUES (?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag);
			
			// If we were able to add our book to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override 
	public boolean removeTag(String tag, Book book) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE (tag_name=? AND isbn_13=?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);
			stmt.setString(2, book.getIsbn13());
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
}
