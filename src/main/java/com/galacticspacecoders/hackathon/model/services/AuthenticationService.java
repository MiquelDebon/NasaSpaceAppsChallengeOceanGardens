package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.dto.LoginDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.entity.User;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.UserNotFoundException;
import com.galacticspacecoders.hackathon.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(RegisterDto registerDto) {
        return new User(
            registerDto.getName(),
            registerDto.getEmail(),
            registerDto.getPassword(),
            registerDto.getPhytoplanktonName()
        );
    }


    public User authenticate (LoginDto loginDto){
        Optional<User> user = userRepository.findByEmailAndPassword(
                loginDto.getEmail(),
                loginDto.getPassword());

        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException();
        }
    }



}
