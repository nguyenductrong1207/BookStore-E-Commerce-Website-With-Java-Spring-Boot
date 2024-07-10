package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.bookstore.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
