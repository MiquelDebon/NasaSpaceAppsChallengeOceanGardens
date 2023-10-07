package com.galacticspacecoders.hackathon.controller;

import com.galacticspacecoders.hackathon.model.dto.*;
import com.galacticspacecoders.hackathon.model.repository.PhytoplanktonRepository;
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

    @PostMapping("/createUser")
    public UserRespond createUser(@RequestBody UserDto userDto) {
        //todo create save for USER in service
        return userService.save(userDto);
    }

    @PostMapping("/createPhytoplankton")
    public PhytoplanktonRespond createPhytoplankton(@RequestBody UserPhytoDto userPhytoDto) {
        //todo create save for PHYTOPLANKTON in service
        return userService.save(userPhytoDto);
    }

    

}
