package com.galacticspacecoders.hackathon.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private int actionId;
    private String userId;
    private boolean answer;

    public int getActionId() {
        return actionId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean getAnswer() {
        return answer;
    }
}
