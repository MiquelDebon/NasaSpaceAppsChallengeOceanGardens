package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.services.PhytoplanktonService;
import com.galacticspacecoders.hackathon.model.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "api/v1/user")
@AllArgsConstructor
@Tag(name = "PHYTOPLANKTON MANAGEMENT SYSTEM", description = "CRUD OPERATIONS FROM PHYTOPLANKTON MONGODB")
public class Controller {

    private final UserService userService;

    private final PhytoplanktonService phytoplanktonService;

    @PostMapping("/createUser")
    @ResponseStatus
    public ResponseEntity<String> createUser(@RequestBody RegisterDto registerDto) {
        //todo create save for USER in service
        userService.save(registerDto);
        return new ResponseEntity(registerDto, null, HttpStatus.CREATED);
    }

    @PostMapping("/createPhytoplankton")
    @ResponseStatus
    public ResponseEntity<String> createPhytoplankton(@RequestBody PhytoplanktonDto phytoplanktonDto) {
        //todo create save for PHYTOPLANKTON in service
        phytoplanktonService.save(phytoplanktonDto);
        return new ResponseEntity(phytoplanktonDto, null, HttpStatus.CREATED);
    }

    @PostMapping("/createGame")
    @ResponseStatus
    public void fromFrontendGame(@RequestBody GameDto gameDto) {
        //todo create method FROMFRONTEND in service
        phytoplanktonService.fromFrontend(gameDto);
    }

}
