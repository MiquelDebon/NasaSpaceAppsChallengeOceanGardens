package com.galacticspacecoders.hackathon.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhytoplanktonDto {

    @Schema(description = "Name of the phytoplankton", example = "Phyto")
    private String name;

    @Schema(description = "Amount of CO2 consumed by the phytoplankton", example = "80")
    private int co2Consumed;

    @Schema(description = "Health status of the phytoplankton", example = "100")
    private int health;

    @Schema(description = "Reproduction possibility of the phytoplankton", example = "2")
    private int reproductionPossibility;

    @Schema(description = "Boolean indicating whether the phytoplankton is in symbiosis with other organisms", example = "true")
    private boolean inSymbiosis;

}