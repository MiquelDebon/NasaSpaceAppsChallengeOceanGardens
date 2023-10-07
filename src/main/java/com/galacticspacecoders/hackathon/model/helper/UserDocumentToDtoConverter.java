package com.galacticspacecoders.hackathon.model.helper;

import com.galacticspacecoders.hackathon.model.dto.UserDto;
import com.galacticspacecoders.hackathon.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDocumentToDtoConverter {

    public UserDto convertDocumentToDto(User user)  {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDto.class);
    }

}
