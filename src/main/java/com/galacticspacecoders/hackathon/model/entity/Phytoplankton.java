package com.galacticspacecoders.hackathon.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Document(collection = "phytoplankton")
public class Phytoplankton {

    @Id
    private ObjectId id;
    private String name;
    private int co2Consumed;
    private int health;
    private int reproductions;
    private boolean inSymbiosis;
    @CreationTimestamp
    private LocalDateTime lastAction;

    public Phytoplankton(String name) {
        this.name = name;
        this.co2Consumed = 0;
        this.health = 100;
        this.reproductions = 0;
        this.inSymbiosis = false;
        this.lastAction = LocalDateTime.now();
    }
}
