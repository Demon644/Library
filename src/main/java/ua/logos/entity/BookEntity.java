package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "book")
public class BookEntity extends BaseEntity{

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "author")
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @Column(nullable = false)
    private String image;

    @Column (nullable = false)
    private int quantity;

    @Column (nullable = false)
    private double price;

    @Column
    private String short_info;

    @Column
    private String language;

}
