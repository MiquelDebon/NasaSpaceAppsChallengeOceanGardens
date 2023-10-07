package com.galacticspacecoders.hackathon.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

    @Schema(description = "Email of the user to log in", example = "galactic@mail.com")
    private String email;

    @Schema(description = "Password of the user to log in", example = "password123")
    private String password;
}
