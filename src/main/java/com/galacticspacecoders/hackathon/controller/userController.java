package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.LoginDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Authentication Controller")
@RequestMapping("auth")
public class userController {

    @Autowired
    private AuthenticationService service;

    @Operation(
            summary = "Register endpoint",
            description = "Description: This method is to register a new player",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User registered successfully",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_ERROR",
                            content = @Content)
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterDto registerDto
    ){
        service.registerUser(registerDto);
        return ResponseEntity.ok().body("User registered successfully");
    }


    @Operation(
            summary = "Authentication endpoint",
            description = "Description: This method is to authenticate a new player",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User authenticated successfully",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_ERROR",
                            content = @Content)
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginDto request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok().body("Log out Successfully");
    }


}
