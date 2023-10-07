package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.entity.Phytoplankton;
import com.galacticspacecoders.hackathon.model.entity.User;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.PhytoplanktonNotFoundException;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.UserNotFoundException;
import com.galacticspacecoders.hackathon.model.repository.PhytoplanktonRepository;
import com.galacticspacecoders.hackathon.model.repository.UserRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PhytoplanktonService {

    @Autowired
    private PhytoplanktonRepository phytoplanktonRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public void fromFrontend(GameDto gameDto) {

        switch (gameDto.getActionId()){
            case 1 -> photosynthesis(gameDto.getUserId(), gameDto.getAnswer());
            case 2 -> MenuOptions.addTree();
            case 3 -> MenuOptions.addFlower();
            case 4 -> MenuOptions.addDecoration();
        }
    } while (option != 0);

}

    public PhytoplanktonDto photosynthesis(ObjectId userId, boolean answer) {

        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()) {
            Phytoplankton phytoplankton = user.get().getPhytoplankton();
            PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

            if (answer) {
                phytoplanktonDto.setEnergy(phytoplanktonDto.getEnergy() + 10);
                phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() + 5);
                registerAction(phytoplankton);
                return phytoplanktonDto;
            } else {
                phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() - 10);
                //registrarAccion();
                return phytoplanktonDto;
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    private void registerAction(Phytoplankton phytoplankton) {

        phytoplankton.setLastAction(LocalDateTime.now());
    }

    private PhytoplanktonDto phytoplanktonToDto (Phytoplankton phytoplankton) {
        return modelMapper.map(phytoplankton, PhytoplanktonDto.class);
    }


}
