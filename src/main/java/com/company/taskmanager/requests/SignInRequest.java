package com.company.taskmanager.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sign in request")
public class SignInRequest {

    @Schema(description = "email", example = "nikolay@gmail.com")
    @Size(min = 5, max = 255,
            message = "Minimum email length is 5 letters, maximum is 255")
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email address should be in the format user@example.com")
    private String email;

    @Schema(description = "password")
    @Size(max = 255, message = "Password maximum length is 255 letters")
    private String password;
}

