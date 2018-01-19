package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

/**
 * Interface for our Data Access Object to handle database queries related to Book tags.
 */
public interface TagDAO {

	public boolean addTag(String tag, Book book); // 1 
	public boolean removeTag(String tag, Book book); // 2

	public List<Book> getAllBooksWithTag(String tag); // 4
	public List<String> getAllTagsFromBook(Book book); // 3
	
}