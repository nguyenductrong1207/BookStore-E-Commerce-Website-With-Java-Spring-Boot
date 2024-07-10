package project.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import project.bookstore.entity.user.User;
import project.bookstore.enums.OrderStatus;
import project.bookstore.enums.PaymentMethod;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDate createdDate = LocalDate.now();

    private int deliverDays;

    private float shippingCost;

    private float totalProductCost;

    private float total;

    private String note;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItems> orderItems= new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address orderAddress = new Address();

    public int getTotalQuantity() {
        return orderItems.stream().mapToInt(OrderItems::getQuantity).sum();
    }

    public String getFirstBookImage() {
        return orderItems.stream().findFirst().map(OrderItems::getFirstBookImage).orElse(null);
    }
}