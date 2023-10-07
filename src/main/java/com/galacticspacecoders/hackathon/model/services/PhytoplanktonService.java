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
        return switch (gameDto.getAction()) {
            case 1 -> photosynthesis(gameDto.getId(), gameDto.getAnswer());
            case 2 -> reproduce(gameDto.getId(), gameDto.getAnswer());
            case 3 -> migrate(gameDto.getId(), gameDto.getAnswer());
            case 4 -> group(gameDto.getId(), gameDto.getAnswer());
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
        User user = checkUserExist(userId);

        if (answer) {
            int totalCo2Consumed = phytoplankton.getCo2Consumed() + 10;
            int totalHealth = phytoplankton.getHealth() + 10;
            phytoplankton.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplankton.setHealth(Math.min(totalHealth, 100));
            user.setPhytoplankton(phytoplankton);
            registerAction(phytoplankton);
        } else {
            int totalHealth = phytoplankton.getHealth() - 10;
            phytoplankton.setHealth(Math.max(0, totalHealth));
            user.setPhytoplankton(phytoplankton);
        }
        userRepo.save(user);
        return phytoplanktonToDto(phytoplankton);
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
        User user = userRepo.findByPhytoplanktonId(phytoplankton.getId()).orElseThrow(UserNotFoundException::new);

        if (phytoplankton != null) {

            LocalDateTime lastAction = phytoplankton.getLastAction();
            LocalDateTime now = LocalDateTime.now();

            if (lastAction != null && Duration.between(lastAction, now).toSeconds() >= 2) {
                int currentCo2Consumed = phytoplankton.getCo2Consumed();
                phytoplankton.setCo2Consumed(currentCo2Consumed - 15);

                user.setPhytoplankton(phytoplankton);
                userRepo.save(user);
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
        User user = checkUserExist(userId);

        if(phytoplankton.getReproductionPossibility() >= 3) {
            if (answer) {
                phytoplankton.setReproductionPossibility(0);
                user.setPhytoplankton(phytoplankton);
                userRepo.save(user);
                int totalCo2Consumed = phytoplankton.getCo2Consumed() + 10;
                phytoplankton.setCo2Consumed(Math.min(totalCo2Consumed, 100));

            } else {
                int totalHealth = phytoplankton.getHealth() - 5;
                phytoplankton.setHealth(Math.max(0, totalHealth));
            }
        }
        user.setPhytoplankton(phytoplankton);
        userRepo.save(user);
        return phytoplanktonToDto(phytoplankton);
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
        User user = checkUserExist(userId);

        if (answer) {
            int totalCo2Consumed = phytoplankton.getCo2Consumed() + 10;
            int totalHealth = phytoplankton.getHealth() + 5;
            phytoplankton.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplankton.setHealth(Math.min(totalHealth, 100));
        } else {
            int totalCo2Consumed = phytoplankton.getCo2Consumed() - 10;
            phytoplankton.setCo2Consumed(Math.max(0, totalCo2Consumed));
        }

        user.setPhytoplankton(phytoplankton);
        userRepo.save(user);
        return phytoplanktonToDto(phytoplankton);
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
        User user = checkUserExist(userId);

        if (answer) {
            int totalCo2Consumed = phytoplankton.getCo2Consumed() + 20;
            phytoplankton.setCo2Consumed(Math.min(totalCo2Consumed, 100));
            phytoplankton.setInSymbiosis(true);

            int currentReproductionPoints = phytoplankton.getReproductionPossibility();

            if (currentReproductionPoints < 3) {
                phytoplankton.setReproductionPossibility(currentReproductionPoints + 1);
                user.setPhytoplankton(phytoplankton);
                userRepo.save(user);
            }
        } else {
            int totalHealth = phytoplankton.getHealth() - 10;
            phytoplankton.setHealth(Math.max(0, totalHealth));
        }
        user.setPhytoplankton(phytoplankton);
        userRepo.save(user);
        return phytoplanktonToDto(phytoplankton);
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
