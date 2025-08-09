package com.tnsif.placement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tnsif.placement.entity.Placement;
import com.tnsif.placement.repository.PlacementRepository;

import jakarta.persistence.NoResultException;

public class PlacementService {
	@Autowired
    private PlacementRepository placementRepository;

    // Get all placement records
    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    // Save a new placement record
    public void savePlacement(Placement placement) {
        placementRepository.save(placement);
    }

    // Get a placement by ID
    public Placement getPlacementById(Integer id) {
        Optional<Placement> placement = placementRepository.findById(id);
        return placement.orElseThrow(() -> new NoResultException("Placement not found with ID: " + id));
    }

    // Delete a placement by ID
    public void deletePlacement(Integer id) {
        placementRepository.deleteById(id);
    }

    // Update an existing placement
    //


}
