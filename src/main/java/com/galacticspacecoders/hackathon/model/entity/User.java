package com.galacticspacecoders.hackathon.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class User {

    @Id
    @Schema(description = "Identifier of the user", example = "643d909f15da8348ee4805c1")
    private ObjectId id;

    @NotBlank
    @Field(name = "nickname")
    @Schema(description = "Nickname of the user", example = "galactic")
    private String nickname;

    @Email
    @NotBlank
    @Field(name = "email")
    @Schema(description = "Email of the user", example = "galactic@mail.com")
    private String email;

    @NotBlank
    @Field(name = "password")
    @Schema(description = "Password of the user", example = "password123")
    private String password;
}
