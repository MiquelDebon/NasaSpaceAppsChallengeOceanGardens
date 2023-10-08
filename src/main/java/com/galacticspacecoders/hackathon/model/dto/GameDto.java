package com.galacticspacecoders.hackathon.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    @Schema(description = "Identification of the user playing", example = "643d909f15da8348ee4805c1")
    private String id;

    @Schema(description = "Identification of the functionality to play", example = 1)
    private int action;

    @Schema(description = "Response of the operation", example = true)
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
