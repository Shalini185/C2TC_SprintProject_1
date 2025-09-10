package com.tnsif.placement.service;

import com.tnsif.placement.entity.Placement;
import com.tnsif.placement.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacementService {

    @Autowired
    private PlacementRepository placementRepository;

    // ✅ Get all placements
    public List<Placement> listAll() {
        return placementRepository.findAll();
    }

    // ✅ Get placement by ID
    public Optional<Placement> findById(Integer id) {
        return placementRepository.findById(id);
    }

    // ✅ Save or update placement
    public Placement save(Placement placement) {
        try {
            if (placement.getPlacementId() != 0 
                    && !placementRepository.existsById(placement.getPlacementId())) {
                throw new RuntimeException("Placement with ID " + placement.getPlacementId() + " does not exist.");
            }
            return placementRepository.save(placement);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Placement data was modified by another user. Please refresh and try again.");
        }
    }

    // ✅ Delete placement by ID
    public void delete(Integer id) {
        if (placementRepository.existsById(id)) {
            placementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Placement with ID " + id + " does not exist.");
        }
    }
}
