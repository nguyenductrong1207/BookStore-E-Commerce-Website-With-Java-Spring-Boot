package project.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import project.bookstore.entity.user.User;

import static lombok.AccessLevel.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    @Column(length = 50)
    private String province;

    @Column(length = 50)
    private String district;

    @Column(length = 50)
    private String ward ;

    private String description;
    
    @OneToOne(mappedBy = "address")
    private User user;

    @OneToOne(mappedBy = "orderAddress")
    private Order order;
}
