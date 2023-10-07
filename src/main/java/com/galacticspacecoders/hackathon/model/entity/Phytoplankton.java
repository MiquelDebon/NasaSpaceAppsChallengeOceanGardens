package com.galacticspacecoders.hackathon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Document(collection = "phytoplankton")
public class Phytoplankton {

    @Id
    private int id;
    private String name;
    private int energy;
    private int health;
    private int reproductions;
    private boolean inSymbiosis;
    @CreationTimestamp
    private LocalDateTime time;

    public Phytoplankton(String name) {
        this.name = name;
        this.energy = 100;
        this.health = 100;
        this.reproductions = 0;
        this.inSymbiosis = false;
        this.time = LocalDateTime.now();
    }
}
