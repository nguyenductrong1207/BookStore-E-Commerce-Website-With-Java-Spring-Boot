package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisher_id;

    @Column(nullable = false, length = 255)
    private String publisher_name;

    private String country;
    private String publisher_address;
    private String publisher_email;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
