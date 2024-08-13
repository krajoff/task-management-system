package com.company.taskmanager.responces;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Response object for JWT authentication.
 * <p>
 * This class represents the response sent to the client after a successful authentication,
 * which includes the access token required for accessing protected resources.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Access token response")
public class JwtAuthenticationResponse {
    @Schema(description = "Token response")
    private String token;
}
