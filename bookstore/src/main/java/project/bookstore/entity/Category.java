package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(nullable = false, length = 255, unique = true)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books;

    @ManyToMany(mappedBy = "categories")
    private Set<News> news;
}
