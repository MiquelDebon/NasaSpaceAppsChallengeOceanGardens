package com.galacticspacecoders.hackathon.model.entity;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "phytoplankton")
public class Phytoplankton {

    @Id
    @Schema(description = "Identifier of the phytoplankton", example = "643d909f15da8348ee4805c1")
    private ObjectId id;

    @Schema(description = "Name of the phytoplankton", example = "Manolo")
    private String name;

    @Schema(description = "Energy of the phytoplankton", example = "100")
    private int co2Consumed;

    @Schema(description = "Health of the phytoplankton", example = "100")
    private int health;

    @Schema(description = "Reproductions of the phytoplankton", example = "0")
    private int reproductionPossibility;

    @Schema(description = "In symbiosis of the phytoplankton", example = "false")
    private boolean inSymbiosis;

    @Schema(description = "Last time the phytoplankton eaten", example = "2022-01-01 00:00:00")
    @CreationTimestamp
    private LocalDateTime lastAction;
    public Phytoplankton(String name) {
        this.name = name;
        this.co2Consumed = 0;
        this.health = 100;
        this.reproductionPossibility = 0;
        this.inSymbiosis = false;
        this.lastAction = LocalDateTime.now();
    }
}
