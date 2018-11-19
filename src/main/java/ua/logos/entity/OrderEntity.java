package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name = "orders")
public class OrderEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn (name = "book_id")
    private BookEntity bookEntity;

    @Column (nullable = false, unique = true)
    private String orderToken;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private UserEntity users;

}
