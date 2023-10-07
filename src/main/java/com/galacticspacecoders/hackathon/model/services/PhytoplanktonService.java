package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.dto.GameDto;
import com.galacticspacecoders.hackathon.model.dto.PhytoplanktonDto;
import com.galacticspacecoders.hackathon.model.entity.Phytoplankton;
import com.galacticspacecoders.hackathon.model.entity.User;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.UserNotFoundException;
import com.galacticspacecoders.hackathon.model.repository.PhytoplanktonRepository;
import com.galacticspacecoders.hackathon.model.repository.UserRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PhytoplanktonService {

    @Autowired
    private PhytoplanktonRepository phytoplanktonRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public void fromFrontend(GameDto gameDto) {

        switch (gameDto.getActionId()) {
            case 1 -> photosynthesis(gameDto.getUserId(), gameDto.getAnswer());
            case 2 -> reproduce(gameDto.getUserId(), gameDto.getAnswer());
            case 3 -> migrate(gameDto.getUserId(), gameDto.getAnswer());
            case 4 -> group(gameDto.getUserId(), gameDto.getAnswer());
        }
    }

    public PhytoplanktonDto photosynthesis(String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            phytoplanktonDto.setCo2Consumed(phytoplanktonDto.getCo2Consumed() + 10);
            phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() + 5);
            registerAction(phytoplankton);
        } else {
            phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() - 10);
        }
        return phytoplanktonDto;
    }


    public PhytoplanktonDto tooMuchTimeWithoutPhotosynthesis() {
        // Utiliza el repositorio de Phytoplankton para obtener el único fitoplancton
        Phytoplankton phytoplankton = phytoplanktonRepo.findAll().stream().findFirst().orElse(null);

        if (phytoplankton != null) {
            // Realiza la lógica para reducir el CO2 consumido si han pasado 3 horas
            LocalDateTime lastAction = phytoplankton.getLastAction();
            LocalDateTime now = LocalDateTime.now();

            if (lastAction != null && Duration.between(lastAction, now).toHours() >= 3) {
                int currentCo2Consumed = phytoplankton.getCo2Consumed();
                phytoplankton.setCo2Consumed(currentCo2Consumed - 15);

                phytoplanktonRepo.save(phytoplankton);
            }
        }

        return phytoplanktonToDto(phytoplankton);
    }


    public PhytoplanktonDto reproduce (String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            phytoplanktonDto.setCo2Consumed(phytoplanktonDto.getCo2Consumed() - 10);
        } else {
            phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() - 5);
        }
        return phytoplanktonDto;
    }

    public PhytoplanktonDto migrate (String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            phytoplanktonDto.setCo2Consumed(phytoplanktonDto.getCo2Consumed() + 10);
            phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() + 5);
        } else {
            phytoplanktonDto.setCo2Consumed(phytoplanktonDto.getCo2Consumed() - 5);
        }
        return phytoplanktonDto;
    }

    public PhytoplanktonDto group(String userId, boolean answer) {
        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            phytoplanktonDto.setInSymbiosis(true);
            phytoplanktonDto.setCo2Consumed(phytoplanktonDto.getCo2Consumed() + 20);

            // Aumentar el contador de puntos de reproducción
            int currentReproductionPoints = phytoplankton.getReproductionPossibility();

            if (currentReproductionPoints < 3) {
                // Aumentar posibilidad de reproducción
                phytoplankton.setReproductionPossibility(currentReproductionPoints + 1);
                // Reiniciar el contador
                phytoplankton.setReproductionPossibility(0);
            }
        } else {
            phytoplanktonDto.setHealth(phytoplanktonDto.getHealth() - 10);
        }
        return phytoplanktonDto;
    }


    private void registerAction(Phytoplankton phytoplankton) {

        phytoplankton.setLastAction(LocalDateTime.now());
    }

    private PhytoplanktonDto phytoplanktonToDto (Phytoplankton phytoplankton) {
        return modelMapper.map(phytoplankton, PhytoplanktonDto.class);
    }

    private User checkUserExist(String userId) {
        ObjectId objectId = new ObjectId(userId);
        return userRepo.findById(objectId)
                .orElseThrow(UserNotFoundException::new);
    }
}
