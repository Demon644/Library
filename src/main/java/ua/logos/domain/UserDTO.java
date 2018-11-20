package ua.logos.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private Long id;

    //Make unique exception
    @NotNull(message = "Field 'Login' can't be NULL")
    private String login;

    @NotNull(message = "Field 'Password' can't be NULL")
    private String password;

    private String image;

}
