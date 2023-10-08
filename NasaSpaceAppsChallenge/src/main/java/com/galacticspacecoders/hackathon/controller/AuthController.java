package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.LoginDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.dto.UserDto;
import com.galacticspacecoders.hackathon.model.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "The Phytoplankton Adventure Application")
@RestController
@RequestMapping("auth")
public class AuthController {

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
    public ResponseEntity<UserDto> register(
            @RequestBody RegisterDto registerDto
    ){
        return ResponseEntity.ok().body(service.registerUser(registerDto));
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
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginDto request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }


    @Operation(
            summary = "Logout endpoint",
            description = "Description: This method is to logout a player",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User logout successfully",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_ERROR",
                            content = @Content)
            }
    )
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String id){
        return ResponseEntity.ok(service.logout(id));
    }


}
