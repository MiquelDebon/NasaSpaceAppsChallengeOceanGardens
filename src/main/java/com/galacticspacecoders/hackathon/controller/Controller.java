package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.services.PhytoplanktonService;
import com.galacticspacecoders.hackathon.model.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "api/v1/user")
@AllArgsConstructor
@Tag(name = "PHYTOPLANKTON MANAGEMENT SYSTEM", description = "CRUD OPERATIONS FROM PHYTOPLANKTON MONGODB")
public class Controller {

    private final UserService userService;

    @Autowired
    private final PhytoplanktonService phytoplanktonService;

    @PostMapping("/createUser")
    @ResponseStatus
    public ResponseEntity<String> createUser(@RequestBody RegisterDto registerDto) {
        //todo create save for USER in service
        //userService.save(registerDto);
        return new ResponseEntity(registerDto, null, HttpStatus.CREATED);
    }

    @GetMapping("/missingPhotosynthesis") //método aleatorio
    @ResponseStatus
    public ResponseEntity<PhytoplanktonDto> lackOfPhotosynthesis(@RequestBody PhytoplanktonDto phytoplanktonDto) {
        return new ResponseEntity<>(phytoplanktonService.tooMuchTimeWithoutPhotosynthesis(), HttpStatus.OK);
    }

    @PostMapping("/action")
    @ResponseStatus
    public ResponseEntity<PhytoplanktonDto> performAction(@RequestBody GameDto gameDto) {
        return new ResponseEntity<>(phytoplanktonService.fromFrontend(gameDto), HttpStatus.OK);
    }

}
