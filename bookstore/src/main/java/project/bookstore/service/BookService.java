package project.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.bookstore.entity.Book;
import project.bookstore.entity.Category;
import project.bookstore.entity.News;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.CategoryRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookReposiory;
    public Page<Book> getPaginatedBooks(Pageable pageable) {
        return bookReposiory.findAll(pageable);
    }
    public Book save(Book book) {
        return bookReposiory.save(book);
    }

    public List<Book> getAllBook() {
        return bookReposiory.findAll();
    }

    public Book getBookById(int id) {
        return bookReposiory.findById(id).get();
    }

    public void deleteById(int id) {
        bookReposiory.deleteById(id);
    }

    public List<Book> searchByBookNameAndCategory(String bookName, String categoryName) {
        if (categoryName.equalsIgnoreCase("All")) {
            return bookReposiory.findByNameContainingIgnoreCase(bookName);
        }
        return bookReposiory.findByNameAndCategory(bookName, categoryName);
    }
}
