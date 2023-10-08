package com.galacticspacecoders.hackathon.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(description = "Unique identifier for the user", example = "643d909f15da8348ee4805c1")
    private String id;

    @Schema(description = "Name of the user", example = "Galactic")
    private String name;

    @Schema(description = "Email of the user", example = "galactic@mail.com")
    private String email;

    @Schema(description = "Phytoplankton associated with the user")
    private PhytoplanktonDto phytoplankton;
}

