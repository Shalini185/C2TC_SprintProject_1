package com.tnsif.placement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tnsif.placement.entity.Placement;
import com.tnsif.placement.service.PlacementService;

@RestController
@RequestMapping("/PlacementService")
public class PlacementController {

    @Autowired
    private PlacementService s;

    @GetMapping
    public List<Placement> listAll() {
        return s.listAll();
    }

    @PostMapping
    public ResponseEntity<Placement> add(@RequestBody Placement s1) {
        Placement saved = s.save(s1);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placement> get(@PathVariable Integer id) {
        Optional<Placement> placementOpt = s.findById(id);
        return placementOpt
                .map(placement -> new ResponseEntity<>(placement, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> update(@PathVariable Integer id, @RequestBody Placement updatedPlacement) {
        Optional<Placement> placementOpt = s.findById(id);
        if (placementOpt.isPresent()) {
            Placement existingPlacement = placementOpt.get();
            existingPlacement.setStudentName(updatedPlacement.getStudentName());
            existingPlacement.setStudentDepartment(updatedPlacement.getStudentDepartment());
            existingPlacement.setCompanyName(updatedPlacement.getCompanyName());
            existingPlacement.setJobRole(updatedPlacement.getJobRole());
            existingPlacement.setPlacementStatus(updatedPlacement.getPlacementStatus());
            existingPlacement.setInterviewDate(updatedPlacement.getInterviewDate());

            Placement saved = s.save(existingPlacement);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (s.findById(id).isPresent()) {
            s.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
