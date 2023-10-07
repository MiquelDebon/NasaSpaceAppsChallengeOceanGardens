package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.dto.LoginDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.dto.UserDto;
import com.galacticspacecoders.hackathon.model.entity.User;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.UserNotFoundException;
import com.galacticspacecoders.hackathon.model.helper.UserDocumentToDtoConverter;
import com.galacticspacecoders.hackathon.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDocumentToDtoConverter converter;

    public UserDto registerUser(RegisterDto registerDto) {
        User user = new User(
            registerDto.getName(),
            registerDto.getEmail(),
            registerDto.getPassword(),
            registerDto.getPhytoplanktonName()
        );

        return converter.convertDocumentToDto(user);
    }


    public UserDto authenticate (LoginDto loginDto){
        Optional<User> user = userRepository.findByEmailAndPassword(
                loginDto.getEmail(),
                loginDto.getPassword());
        if(user.isPresent()){
            return converter.convertDocumentToDto(user.get());
        }else{
            throw new UserNotFoundException();
        }
    }



}
