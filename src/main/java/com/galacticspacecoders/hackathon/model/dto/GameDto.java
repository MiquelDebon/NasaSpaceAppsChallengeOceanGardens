package com.galacticspacecoders.hackathon.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private ObjectId actionId;
    private ObjectId userId;
    private boolean answer;

    public ObjectId getActionId() {
        return actionId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public boolean getAnswer() {
        return answer;
    }
}
