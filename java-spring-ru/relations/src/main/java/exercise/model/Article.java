package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Lob
    private String body;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
// END
