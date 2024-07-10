package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.bookstore.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameContainingIgnoreCase(String name);

    @Query("SELECT b FROM Book b JOIN b.categories c " +
            "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND LOWER(c.name) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Book> findByNameAndCategory(String name, String categoryName);
}
