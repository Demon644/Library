package ua.logos.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SigninRequest {

    private String login;
    private String password;

}
