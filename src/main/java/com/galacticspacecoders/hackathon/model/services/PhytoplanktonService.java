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

    public PhytoplanktonDto fromFrontend(GameDto gameDto) {
        return switch (gameDto.getActionId()) {
            case 1 -> photosynthesis(gameDto.getUserId(), gameDto.getAnswer());
            case 2 -> reproduce(gameDto.getUserId(), gameDto.getAnswer());
            case 3 -> migrate(gameDto.getUserId(), gameDto.getAnswer());
            case 4 -> group(gameDto.getUserId(), gameDto.getAnswer());
            default -> null;
        };
    }

    /**
     *  Simulates photosynthesis process for the given user's phytoplankton and updates its properties.
     *
     *  @param userId The unique identifier of the user.
     *  @param answer A boolean indicating whether the user confirms the photosynthesis process.
     *                If true, the phytoplankton's CO2 consumption and health will increase.
     *                If false, the phytoplankton's health will decrease.
     *  @return A {@link PhytoplanktonDto} object representing the updated properties of the phytoplankton
     *          after the photosynthesis process.
     *  @throws UserNotFoundException If the specified user is not found
     */
    public PhytoplanktonDto photosynthesis(String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            int totalCo2Consumed = phytoplanktonDto.getCo2Consumed() + 10;
            int totalHealth = phytoplanktonDto.getHealth() + 5;
            phytoplanktonDto.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplanktonDto.setHealth(Math.min(totalHealth, 100));
            registerAction(phytoplankton);
        } else {
            int totalHealth = phytoplanktonDto.getHealth() - 10;
            phytoplanktonDto.setHealth(Math.max(0, totalHealth));
        }
        return phytoplanktonDto;
    }
    
    /**
     * Checks if the phytoplankton has been without photosynthesis for more than 3 hours
     * and reduces the consumed CO2 accordingly if the condition is met.
     *
     * @return A {@link PhytoplanktonDto} object representing the updated properties of the phytoplankton
     *         after adjusting the CO2 consumption.
     */
    public PhytoplanktonDto tooMuchTimeWithoutPhotosynthesis() {

        Phytoplankton phytoplankton = phytoplanktonRepo.findAll().stream().findFirst().orElse(null);

        if (phytoplankton != null) {

            LocalDateTime lastAction = phytoplankton.getLastAction();
            LocalDateTime now = LocalDateTime.now();

            if (lastAction != null && Duration.between(lastAction, now).toHours() >= 2) {
                int currentCo2Consumed = phytoplankton.getCo2Consumed();
                phytoplankton.setCo2Consumed(currentCo2Consumed - 15);

                phytoplanktonRepo.save(phytoplankton);
            }
        }

        return phytoplanktonToDto(phytoplankton);
    }

    /**
     * Simulates the reproduction process of phytoplankton based on user input and updates its properties.
     *
     * @param userId The unique identifier of the user.
     * @param answer A boolean indicating whether the user confirms the reproduction process.
     *               If true, the phytoplankton's CO2 consumption will decrease.
     *               If false, the phytoplankton's health will decrease.
     * @return A {@link PhytoplanktonDto} object representing the updated properties of the phytoplankton
     *         after the reproduction process.
     * @throws UserNotFoundException If the specified user is not found.
     */
    public PhytoplanktonDto reproduce (String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if(phytoplanktonDto.getReproductionPossibility() == 3) {
            if (answer) {
                int totalCo2Consumed = phytoplanktonDto.getCo2Consumed() + 10;
                phytoplanktonDto.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            } else {
                int totalHealth = phytoplanktonDto.getHealth() - 5;
                phytoplanktonDto.setHealth(Math.max(0, totalHealth));
            }
        }
        return phytoplanktonDto;
    }

    /**
     * Simulates the migration process of phytoplankton based on user input and updates its properties.
     *
     * @param userId The unique identifier of the user.
     * @param answer A boolean indicating whether the user confirms the migration process.
     *               If true, the phytoplankton's CO2 consumption and health will increase.
     *               If false, the phytoplankton's CO2 consumption will decrease.
     * @return A {@link PhytoplanktonDto} object representing the updated properties of the phytoplankton
     *         after the migration process.
     * @throws UserNotFoundException If the specified user is not found.
     */
    public PhytoplanktonDto migrate (String userId, boolean answer) {

        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            int totalCo2Consumed = phytoplanktonDto.getCo2Consumed() + 10;
            int totalHealth = phytoplanktonDto.getHealth() + 5;
            phytoplanktonDto.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplanktonDto.setHealth(Math.min(totalHealth, 100));
        } else {
            int totalCo2Consumed = phytoplanktonDto.getCo2Consumed() - 10;
            phytoplanktonDto.setCo2Consumed(Math.max(0, totalCo2Consumed));
        }
        return phytoplanktonDto;
    }

    /**
     * Simulates the grouping process of phytoplankton based on user input and updates its properties.
     * If the user confirms the grouping, the phytoplankton enters symbiosis, increases CO2 consumption,
     * and earns reproduction points. If not, the phytoplankton's health decreases.
     *
     * @param userId The unique identifier of the user.
     * @param answer A boolean indicating whether the user confirms the grouping process.
     *               If true, the phytoplankton enters symbiosis, increases CO2 consumption, and earns
     *               reproduction points. If false, the phytoplankton's health decreases.
     * @return A {@link PhytoplanktonDto} object representing the updated properties of the phytoplankton
     *         after the grouping process.
     * @throws UserNotFoundException If the specified user is not found.
     */
    public PhytoplanktonDto group(String userId, boolean answer) {
        Phytoplankton phytoplankton = checkUserExist(userId).getPhytoplankton();
        PhytoplanktonDto phytoplanktonDto = phytoplanktonToDto(phytoplankton);

        if (answer) {
            int totalCo2Consumed = phytoplanktonDto.getCo2Consumed() + 20;
            phytoplanktonDto.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplanktonDto.setInSymbiosis(true);

            int currentReproductionPoints = phytoplankton.getReproductionPossibility();

            if (currentReproductionPoints < 3) {
                phytoplankton.setReproductionPossibility(currentReproductionPoints + 1);
            } else {
                phytoplankton.setReproductionPossibility(0);
            }
        } else {
            int totalHealth = phytoplanktonDto.getHealth() - 10;
            phytoplanktonDto.setHealth(Math.max(0, totalHealth));
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
