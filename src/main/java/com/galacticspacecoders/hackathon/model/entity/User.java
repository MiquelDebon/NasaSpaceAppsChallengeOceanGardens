package com.galacticspacecoders.hackathon.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class User implements UserDetails {

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

    @Field(name= "phytoplankton")
    @Schema(description = "Name of the phytoplankton", example= "phyto")
    private Phytoplankton phytoplankton;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String nickname, String email, String password, String phytoplanktonName) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        role = Role.USER;
        phytoplankton = new Phytoplankton(phytoplanktonName);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
