package com.galacticspacecoders.hackathon.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPhytoDto {

    private Integer IdPhyto;
    private String Name;
    private String Energy;
    private String Breed;
    private Integer userId;
    private Integer symbiosis;
    private LocalDateTime timeToFeed;

}