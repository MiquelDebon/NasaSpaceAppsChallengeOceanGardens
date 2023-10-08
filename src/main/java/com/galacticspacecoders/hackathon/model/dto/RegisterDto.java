package com.galacticspacecoders.hackathon.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    @Schema(description = "Email of the user to register", example = "galactic@mail.com")
    private String email;

    @Schema(description = "Name of the user to register", example = "galactic")
    private String name;

    @Schema(description = "Password of the user to register", example = "password123")
    private String password;

    @Schema(description = "Name of the phytoplankton", example = "Phyto")
    private String phytoplanktonName;
}
