package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

   public Long countById(Integer id);

   boolean existsByTitle(String title);

}
