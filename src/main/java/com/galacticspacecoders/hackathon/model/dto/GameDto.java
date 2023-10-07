package com.galacticspacecoders.hackathon.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private String id;
    private int action;
    private boolean answer;

    public int getAction() {
        return action;
    }

    public String getId() {
        return id;
    }

    public boolean getAnswer() {
        return answer;
    }
}
