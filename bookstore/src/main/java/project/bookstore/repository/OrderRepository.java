package project.bookstore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Book;
import project.bookstore.entity.Order;
import project.bookstore.entity.user.User;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByUser(User user, Sort sort);

    //    @Query("SELECT o FROM Order o WHERE o.firstName LIKE %?1% OR"
//            + " o.lastName LIKE %?1% OR o.address LIKE %?1% OR"
//            + " CAST(o.paymentMethod AS string) LIKE %?1% OR CAST(o.orderStatus AS string) LIKE %?1% OR"
//            + " o.user.firstName LIKE %?1% OR o.user.lastName LIKE %?1%")
//    public Page<Order> findAll(String keyword, Pageable pageable);
    public Long countById(Integer id);
}
