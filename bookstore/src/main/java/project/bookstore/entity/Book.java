package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    public Book(int book_id) {
        this.book_id = book_id;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float salePrice;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String publication_date;

    @Column(nullable = false)
    private int num_pages;

    private String book_image;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

}
