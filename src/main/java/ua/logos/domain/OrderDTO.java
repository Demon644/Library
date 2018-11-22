package ua.logos.domain;

import lombok.Data;
import ua.logos.entity.UserEntity;

import javax.validation.constraints.NotNull;

@Data
public class OrderDTO {

    private Long id;

    @NotNull(message = "Field 'BookId' can't be NULL")
    private BookDTO book;

    private UserEntity user;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String deliveryAddress;

//    @NotNull(message = "Field 'Order_Token' can't be NULL")
//    private String orderToken;
}
