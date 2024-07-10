package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.bookstore.entity.user.User;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;

    private float shippingCost;

    @Transient
    public float getSubtotal(){
        return book.getSalePrice() * quantity;
    }
}
