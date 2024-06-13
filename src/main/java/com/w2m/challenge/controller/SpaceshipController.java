package com.w2m.challenge.controller;

import com.w2m.challenge.dto.NewSpaceshipDto;
import com.w2m.challenge.dto.SpaceshipDto;
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
@Tag(name = "Spaceships")
@RequestMapping("")
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }

    @Operation(summary = "Create a spaceship")
    @PostMapping(value = "spaceships")
    public ResponseEntity<String> save(@RequestBody NewSpaceshipDto newSpaceshipDto){
        spaceshipService.save(newSpaceshipDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Spaceship created");
    }

    @Operation(summary = "Get all spaceships with pagination")
    @GetMapping(value = "spaceships")
    public List<SpaceshipDto> getAll(
            @Parameter(name = "offset", description = "If paging is required, collection offset position", in = ParameterIn.QUERY) @RequestParam(defaultValue = "0", required = false) Integer offset,
            @Parameter(name = "limit", description = "Maximum number of items to receive", in = ParameterIn.QUERY) @RequestParam(defaultValue = "10" , required = false) Integer limit
    ){
        return spaceshipService.getAll(offset, limit);
    }
    @Operation(summary = "Get all spaceships by name")
    @GetMapping(value = "spaceships-by-name")
    public List<SpaceshipDto> getAllByName(
            @Parameter(name = "name_filter", description = "Retrieve all spaceships that contain, in their name, the value of this parameter", in = ParameterIn.QUERY) @RequestParam(name="name_filter", required = false) String nameFilter
     ){
        return spaceshipService.getAllByName(nameFilter);
    }


    @Operation(summary = "Get spaceship by id")
    @GetMapping(value = "spaceships/{spaceship_id}")
    public SpaceshipDto getById(
            @Parameter(name = "spaceship_id", description = "Unique id of the spaceship to retrieve", required = true, in = ParameterIn.PATH) @PathVariable("spaceship_id") Long id
    ){
        return spaceshipService.getById(id);
    }

    @Operation(summary = "Update spaceship")
    @PutMapping(value = "spaceships")
    public ResponseEntity<String> update(@RequestBody SpaceshipDto spaceship){
        spaceshipService.update(spaceship);
        return ResponseEntity.status(HttpStatus.OK).body("Spaceship updated");
    }

    @Operation(summary = "Delete spaceship")
    @DeleteMapping(value = "spaceships/{spaceship_id}")
    public ResponseEntity<String> delete(
            @Parameter(name = "spaceship_id", description = "Unique id of the spaceship to delete", required = true, in = ParameterIn.PATH) @PathVariable("spaceship_id") Long id
    ){
        spaceshipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Spaceship deleted");
    }

}
