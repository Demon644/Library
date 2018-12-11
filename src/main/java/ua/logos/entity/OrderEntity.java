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
    private BookEntity book;

//    @Column (nullable = false, unique = true)
//    private String orderToken;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "delivery_address")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn (name = "user_login")
    private UserEntity users;

}
