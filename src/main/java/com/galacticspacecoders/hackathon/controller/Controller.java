package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.services.PhytoplanktonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PHYTOPLANKTON Application", description = "CRUD OPERATIONS FROM PHYTOPLANKTON")
@RestController
@RequestMapping(value= "api/v1")
@AllArgsConstructor
public class Controller {

    @Autowired
    private final PhytoplanktonService phytoplanktonService;


    @Operation(
            summary = "Game endpoint",
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
    @PostMapping("/createGame")
    @ResponseStatus
    public ResponseEntity<?> performAction(@RequestBody GameDto gameDto) {
        return new ResponseEntity<>(phytoplanktonService.fromFrontend(gameDto), HttpStatus.OK);
    }

}
