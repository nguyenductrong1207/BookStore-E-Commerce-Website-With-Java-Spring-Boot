package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Book;
import project.bookstore.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Long countById(Integer id);

    @Query("select c from Category c where c.name =?1")
    public Category findByName(String categoryName);
}
