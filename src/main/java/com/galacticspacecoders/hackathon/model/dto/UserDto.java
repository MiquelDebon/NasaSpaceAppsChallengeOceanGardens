package com.galacticspacecoders.hackathon.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String nickname;
    private String email;
    private String phytoplanktonName;
}

