package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItems_id")
    private Integer id;

    private Integer quantity;

    private float unitPrice;

    private float shippingCost;

    private float subtotal;

    private float productCost;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public String getFirstBookImage() {
        return book.getBook_image();
    }
}