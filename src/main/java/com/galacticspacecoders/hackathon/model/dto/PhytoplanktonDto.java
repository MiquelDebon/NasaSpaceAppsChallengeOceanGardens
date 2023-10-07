package com.galacticspacecoders.hackathon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhytoplanktonDto {

    private String name;
    private int energy;
    private int health;
    private int reproductions;
    private boolean inSymbiosis;

}