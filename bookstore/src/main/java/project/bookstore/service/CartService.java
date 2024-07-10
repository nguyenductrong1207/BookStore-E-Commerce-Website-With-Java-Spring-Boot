package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;
import project.bookstore.entity.user.User;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.CartRepository;
import project.bookstore.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    public Integer addBookToCart(Integer bookId, Integer quantity, User user) {
        Integer updateQuantity = quantity;
        Book book = new Book(bookId);

        Cart cart = cartRepository.findByUserAndBook(user, book);

        if (cart != null) {
            updateQuantity = cart.getQuantity() + quantity;
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setBook(book);
        }
        cart.setQuantity(updateQuantity);
        cartRepository.save(cart);

        return updateQuantity;
    }

    public List<Cart> listCart(User user) {
        return cartRepository.findByUser(user);
    }

    public float updateQuantity(Integer bookId, Integer quantity, User user) {
        cartRepository.updateQuantity(quantity, user.getId(), bookId);
        Book book = bookRepository.findById(bookId).get();
        return book.getSalePrice() * quantity;
    }

    public void removeBook(Integer bookId, User user) {
        cartRepository.deleteByUserAndBook(user.getId(), bookId);
    }

    public int getTotalNumberOfItems(User user) {
        List<Cart> carts = listCart(user);
        return carts.size();
    }

    public void deleteCartItemsByUserId(Long userId) {
        cartRepository.deleteCartItemByUser(userId);
    }
}
