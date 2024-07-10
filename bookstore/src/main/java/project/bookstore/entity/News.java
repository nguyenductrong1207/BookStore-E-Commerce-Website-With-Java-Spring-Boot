package project.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author_news;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publication;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String shortDescription;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description_news;

    @Column(name = "news_image")
    private String newsImage;

    @ManyToMany
    @JoinTable(
            name = "news_category",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
