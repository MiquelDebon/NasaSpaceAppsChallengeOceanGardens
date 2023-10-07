package com.galacticspacecoders.hackathon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhytoplanktonDto {

    private String name;
    private int co2Consumed;
    private int health;
    private int reproductionPossibility;
    private boolean inSymbiosis;

}