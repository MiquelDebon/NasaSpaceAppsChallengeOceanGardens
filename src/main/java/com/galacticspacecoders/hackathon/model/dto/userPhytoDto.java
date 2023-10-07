package com.galacticspacecoders.hackathon.model.dto;


import java.time.LocalDateTime;

public record userPhytoDto(
    Integer IdPhyto,
    String Name,
    String Energy,
    String Breed,
    Integer userId,
    Integer symbiosis,
    LocalDateTime timeToFeed
) {

}

