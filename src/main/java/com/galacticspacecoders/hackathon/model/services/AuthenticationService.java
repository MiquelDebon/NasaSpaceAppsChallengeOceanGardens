package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.dto.LoginDto;
import com.galacticspacecoders.hackathon.model.dto.RegisterDto;
import com.galacticspacecoders.hackathon.model.dto.UserDto;
import com.galacticspacecoders.hackathon.model.entity.User;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.UserNotFoundException;
import com.galacticspacecoders.hackathon.model.helper.UserDocumentToDtoConverter;
import com.galacticspacecoders.hackathon.model.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDocumentToDtoConverter converter;

    /**
     * Registers a new user based on the provided registration data.
     *
     * @param registerDto The data for user registration, including name, email, password, and phytoplankton name.
     * @return A UserDto object representing the registered user.
     */
    public UserDto registerUser(RegisterDto registerDto) {
        User user = new User(
            registerDto.getName(),
            registerDto.getEmail(),
            registerDto.getPassword(),
            registerDto.getPhytoplanktonName()
        );
        userRepository.save(user);

        return converter.convertDocumentToDto(user);
    }

    /**
     * Authenticates a user based on the provided login data.
     *
     * This method attempts to find a user in the repository using the provided email
     * and password. If a matching user is found, it converts the user document to a UserDto
     * object and returns it. If no user is found, a UserNotFoundException is thrown.
     *
     * @param loginDto The login data, including email and password, for user authentication.
     * @return A UserDto object representing the authenticated user.
     * @throws UserNotFoundException If the provided email and password do not match any user in the repository.
     */
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

    /**
     * Logs out a user based on the provided user ID.
     *
     * This method retrieves a user from the repository using the provided user ID.
     * If a matching user is found, it converts the user document to a UserDto object
     * and returns it. If no user is found for the given ID, a UserNotFoundException is thrown.
     *
     * @param id The unique identifier of the user to be logged out.
     * @return A UserDto object representing the logged-out user.
     * @throws UserNotFoundException If no user is found for the provided ID in the repository.
     */
    public UserDto logout(String id){
        User user = userRepository.findById(new ObjectId(id)).orElseThrow(UserNotFoundException::new);
        return converter.convertDocumentToDto(user);
    }

}
