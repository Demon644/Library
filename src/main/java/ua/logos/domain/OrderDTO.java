package ua.logos.domain;

import lombok.Data;
import ua.logos.entity.UserEntity;

import javax.validation.constraints.NotNull;

@Data
public class OrderDTO {

    private Long id;

    @NotNull(message = "Field 'BookId' can't be NULL")
    private BookDTO book;

    @NotNull(message = "Field 'BookId' can't be NULL")
    private UserEntity user;

    @NotNull(message = "Field 'Order_Token' can't be NULL")
    private String orderToken;
}
