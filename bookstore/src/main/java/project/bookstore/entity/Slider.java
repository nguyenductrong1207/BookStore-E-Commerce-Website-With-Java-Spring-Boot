package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "slider")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    private String title;
    private String description;
    private Boolean isSelected;

}
