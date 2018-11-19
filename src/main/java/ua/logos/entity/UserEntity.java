package ua.logos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name = "users")
public class UserEntity extends BaseEntity{

    @Column (nullable = false, unique = true)
    private String login;

    @Column (nullable = false)
    private String password;

    @Enumerated (EnumType.STRING)
    private UserType userType;

    @Column
    private String image;

}
