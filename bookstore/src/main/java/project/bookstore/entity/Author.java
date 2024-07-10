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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String country;

    private String description;

    @Column(name = "profile_image_url")
    private String profileImageURL;

    @OneToMany(mappedBy = "author")
    private Set<Book> book;

}
