package com.w2m.challenge.service;

import com.w2m.challenge.dto.NewSpaceshipDto;
import com.w2m.challenge.dto.SpaceshipDto;
import com.w2m.challenge.model.Spaceship;

import java.util.List;

public interface SpaceshipService {

    Spaceship save(NewSpaceshipDto newSpaceshipDto);

    List<SpaceshipDto> getAll(Integer offset, Integer limit);

    List<SpaceshipDto> getAllByName(String nameFilter);

    SpaceshipDto getById(Long id);

    Spaceship update(SpaceshipDto spaceshipDto);

    void delete(Long id);

}
