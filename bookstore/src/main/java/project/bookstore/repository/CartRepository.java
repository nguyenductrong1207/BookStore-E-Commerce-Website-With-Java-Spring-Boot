package project.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;
import project.bookstore.entity.user.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUser(User user);

    public Cart findByUserAndBook(User user, Book book);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity= ?1 WHERE c.user.id = ?2 AND c.book.book_id = ?3")
    public void updateQuantity(Integer quantity, Long userId, Integer bookId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.id = ?1 AND c.book.book_id = ?2")
    public void deleteByUserAndBook(Long userId, Integer bookId);

    @Modifying
    @Query("delete from Cart c WHERE c.user.id = ?1")
    public void deleteCartItemByUser(Long userId);
}
