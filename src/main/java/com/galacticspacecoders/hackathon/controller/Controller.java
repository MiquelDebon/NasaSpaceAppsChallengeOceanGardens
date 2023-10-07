package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.dto.Responds.PhytoplanktonRespond;
import com.galacticspacecoders.hackathon.model.dto.Responds.UserRespond;
import com.galacticspacecoders.hackathon.model.services.PhytoplanktonService;
import com.galacticspacecoders.hackathon.model.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value= "api/v1/user")
@AllArgsConstructor
@Tag(name = "PHYTOPLANKTON MANAGEMENT SYSTEM", description = "CRUD OPERATIONS FROM PHYTOPLANKTON MONGODB")
public class Controller {

    private final UserService userService;

    private final PhytoplanktonService phytoplanktonService;

    @PostMapping("/createUser")
    @ResponseStatus
    public ResponseEntity<UserRespond> createUser(@RequestBody RegisterDto registerDto) {
        //todo create save for USER in service
        return userService.save(registerDto);
    }

    @PostMapping("/createPhytoplankton")
    @ResponseStatus
    public ResponseEntity<PhytoplanktonRespond> createPhytoplankton(@RequestBody PhytoplanktonDto phytoplanktonDto) {
        //todo create save for PHYTOPLANKTON in service
        return phytoplanktonService.save(phytoplanktonDto);
    }

    @PostMapping("/createGame")
    @ResponseStatus
    public void fromFrontendGame(@RequestBody GameDto gameDto) {
        //todo create game for Game in service
        phytoplanktonService.fromFrontend(gameDto);
    }
}
