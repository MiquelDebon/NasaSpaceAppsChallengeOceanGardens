package com.galacticspacecoders.hackathon.model.services;

import com.galacticspacecoders.hackathon.model.entity.Phytoplankton;
import com.galacticspacecoders.hackathon.model.exception.customExceptions.PhytoplanktonNotFoundException;
import com.galacticspacecoders.hackathon.model.repository.PhytoplanktonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhytoplanktonService {

    @Autowired
    private PhytoplanktonRepository phytoplanktonRepo;

    public boolean photosynthesis(int id, boolean answer) {

        Optional<Phytoplankton> phytoplankton = phytoplanktonRepo.findById(id);
        if(phytoplankton.isPresent()) {
            if (answer) {
                energy += 10;
                salud += 5;
                registrarAccion();
                return true;
            } else {
                // El fitoplancton no pudo realizar la fotosíntesis correctamente
                // Reduce su salud
                salud -= 10;
                registrarAccion();
                return false;
            }
        } else {
            throw new PhytoplanktonNotFoundException();
        }

    }
}
