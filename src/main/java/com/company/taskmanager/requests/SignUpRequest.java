package com.company.taskmanager.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for user sign-up requests.
 * <p>
 * This class encapsulates the data required for a new user to
 * sign up for the application. It includes validation constraints
 * to ensure that the provided username, email, and password meet
 * the required format and length.
 * </p>
 */
@Data
@Schema(description = "Sign up request")
public class SignUpRequest {

    @JsonProperty("username")
    @Schema(description = "username", example = "Nikolay")
    @Size(min = 5, max = 30,
            message = "Minimum username length is 5 letters, maximum is 30")
    @NotBlank(message = "Username can not be blank ")
    private String username;

    @JsonProperty("email")
    @Schema(description = "email", example = "nikolay@gmail.com")
    @Size(min = 5, max = 255,
            message = "Minimum email length is 5 letters, maximum is 255")
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email address should be in the format user@example.com")
    private String email;

    @JsonProperty("password")
    @Schema(description = "password")
    @Size(max = 255, message = "Password maximum length is 255 letters")
    private String password;
}

