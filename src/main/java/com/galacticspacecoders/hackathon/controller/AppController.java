package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.services.PhytoplanktonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PHYTOPLANKTON Application", description = "CRUD OPERATIONS FROM PHYTOPLANKTON")
@AllArgsConstructor
@RestController
@RequestMapping(value= "api/v1")
public class AppController {

    @Autowired
    private final PhytoplanktonService phytoplanktonService;


    @Operation(
            summary = "Feed check endpoint",
            description = "Description: This method check if the player is feeding the phytoplankton",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Phytoplankton well fed",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_ERROR",
                            content = @Content)
            }
    )
    @GetMapping("/missingPhotosynthesis") //método aleatorio
    @ResponseStatus
    public ResponseEntity<?> lackOfPhotosynthesis(@RequestBody PhytoplanktonDto phytoplanktonDto) {
        return new ResponseEntity<>(phytoplanktonService.tooMuchTimeWithoutPhotosynthesis(), HttpStatus.OK);
    }

    @Operation(
            summary = "Game action endpoint",
            description = "Description: This method allow the user interact with the their phytoplankton",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Well played",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL_ERROR",
                            content = @Content)
            }
    )
    @PostMapping("/action")
    @ResponseStatus
    public ResponseEntity<?> performAction(@RequestBody GameDto gameDto) {
        return new ResponseEntity<>(phytoplanktonService.fromFrontend(gameDto), HttpStatus.OK);
    }

}
