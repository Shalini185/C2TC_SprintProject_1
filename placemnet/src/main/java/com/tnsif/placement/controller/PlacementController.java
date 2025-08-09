package com.tnsif.placement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tnsif.placement.entity.Placement;
import com.tnsif.placement.service.PlacementService;

import jakarta.persistence.NoResultException;


public class PlacementController {
	 @Autowired
	    private PlacementService service;

	    // GET all placements
	    @GetMapping("/PlacementService")
	    public List<Placement> listAll() {
	        return service.listAll();
	    }

	    // POST new placement
	    @PostMapping("/PlacementService")
	    public void add(@RequestBody Placement placement) {
	        service.save(placement);
	    }

	    // GET placement by ID
	    @GetMapping("/PlacementService/{id}")
	    public ResponseEntity<Placement> get(@PathVariable Integer id) {
	        try {
	            Placement placement = service.get(id);
	            return new ResponseEntity<>(placement, HttpStatus.OK);
	        } catch (NoResultException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // PUT update placement
	    @PutMapping("/PlacementService/{id}")
	    public ResponseEntity<Placement> update(@PathVariable Integer id, @RequestBody Placement updatedPlacement) {
	        try {
	            Placement existingPlacement = service.get(id);

	            existingPlacement.setStudentName(updatedPlacement.getStudentName());
	            existingPlacement.setStudentDepartment(updatedPlacement.getStudentDepartment());
	            existingPlacement.setCompanyName(updatedPlacement.getCompanyName());
	            existingPlacement.setJobRole(updatedPlacement.getJobRole());
	            existingPlacement.setPlacementStatus(updatedPlacement.getPlacementStatus());

	            service.update(existingPlacement);
	            return new ResponseEntity<>(existingPlacement, HttpStatus.OK);
	        } catch (NoResultException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // DELETE placement
	    @DeleteMapping("/PlacementService/{id}")
	    public void delete(@PathVariable Integer id) {
	        service.delete(id);
	    }

}
