package com.w2m.challenge.mapper;

import com.w2m.challenge.dto.NewSpaceshipDto;
import com.w2m.challenge.dto.SpaceshipDto;
import com.w2m.challenge.model.Spaceship;

public class SpaceshipMapper {

    private SpaceshipMapper() {
    }

    public static SpaceshipDto mapToSpaceshipDto(Spaceship spaceship){
        return new SpaceshipDto(spaceship.getId(), spaceship.getName());
    }

    public static Spaceship mapToSpaceship(SpaceshipDto spaceshipDto){
        return new Spaceship(spaceshipDto.id(), spaceshipDto.name());
    }

    public static Spaceship mapToSpaceship(NewSpaceshipDto newSpaceshipDto){
        return new Spaceship(null, newSpaceshipDto.name());
    }
}
