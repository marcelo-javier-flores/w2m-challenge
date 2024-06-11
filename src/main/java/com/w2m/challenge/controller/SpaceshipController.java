package com.w2m.challenge.controller;

import com.w2m.challenge.model.Spaceship;
import com.w2m.challenge.service.SpaceshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Spaceship")
@RequestMapping("spaceship")
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @Operation(summary = "Create a spaceship")
    @PostMapping(value = "")
    public ResponseEntity<Spaceship> save(@RequestBody Spaceship spaceship){
        spaceshipService.save(spaceship);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all spaceships")
    @GetMapping(value = "")
    public List<Spaceship> getAll(
            @Parameter(name = "name_filter", description = "Retrieve all spaceships that contain, in their name, the value of this parameter", in = ParameterIn.QUERY) @RequestParam(name="name_filter", required = false) String nameFilter,
            @Parameter(name = "offset", description = "If paging is required, collection offset position", in = ParameterIn.QUERY) @RequestParam(defaultValue = "0", required = false) Integer offset,
            @Parameter(name = "limit", description = "Maximum number of items to receive", in = ParameterIn.QUERY) @RequestParam(defaultValue = "10" , required = false) Integer limit
    ){
        return spaceshipService.getAll(nameFilter, offset, limit);
    }

    @Operation(summary = "Get spaceship by id")
    @GetMapping(value = "{spaceship_id}")
    public Spaceship getById(
            @Parameter(name = "spaceship_id", description = "Unique id of the spaceship to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("spaceship_id") Long id
    ){
        return spaceshipService.getById(id);
    }

    @Operation(summary = "Update spaceship")
    @PutMapping(value = "")
    public ResponseEntity<Spaceship> update(@RequestBody Spaceship spaceship){
        spaceshipService.update(spaceship);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete spaceship")
    @DeleteMapping(value = "{spaceship_id}")
    public ResponseEntity<Spaceship> delete(
            @Parameter(name = "spaceship_id", description = "Unique id of the spaceship to delete", required = true, in = ParameterIn.PATH) @PathVariable("spaceship_id") Long id
    ){
        spaceshipService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
