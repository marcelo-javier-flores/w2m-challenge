package com.w2m.challenge.service;

import com.w2m.challenge.dto.NewSpaceshipDto;
import com.w2m.challenge.dto.SpaceshipDto;
import com.w2m.challenge.model.Spaceship;
import com.w2m.challenge.repository.SpaceshipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.w2m.challenge.util.MockConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SpaceshipServiceTest {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;

    private NewSpaceshipDto newSpaceshipDto;
    private SpaceshipDto spaceshipDto;
    private Spaceship spaceship;
    private List<Spaceship> spaceships;

    @BeforeEach
    void init(){
        newSpaceshipDto = new NewSpaceshipDto("nave-1");
        spaceshipDto = new SpaceshipDto(SPACESHIP_ID, "nave-1");
        spaceship = new Spaceship(SPACESHIP_ID, "nave-1");
        spaceships = List.of(spaceship);
    }

    @Test
    void saveSpaceship(){
        when(spaceshipRepository.save(any()))
                .thenReturn(spaceship);

        Spaceship result  = spaceshipService.save(newSpaceshipDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void UpdateSpaceship(){
        when(spaceshipRepository.save(any()))
                .thenReturn(spaceship);

        Spaceship result  = spaceshipService.update(spaceshipDto);
        Assertions.assertNotNull(result);
    }

    @Test
    void getAllSpaceships(){

        Pageable pageable = PageRequest.of(OFFSET, LIMIT_TEN);
        Page<Spaceship> spaceshipPage = new PageImpl<>(spaceships);

        when(spaceshipRepository.findAll(pageable))
                .thenReturn(spaceshipPage);

        List<SpaceshipDto> result = spaceshipService.getAll( OFFSET, LIMIT_TEN);

        Assertions.assertFalse(result.isEmpty());

    }

    @Test
    void getAllSpaceshipsByNameNull(){
        Specification<Spaceship> filters = Specification.where( null);
        when(spaceshipRepository.findAll(filters))
                .thenReturn(spaceships);
        List<SpaceshipDto> result = spaceshipService.getAllByName(null);

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void getSpaceshipById(){

        Optional<Spaceship> spaceshipOptional = Optional.of(spaceship);

        when(spaceshipRepository.findById(SPACESHIP_ID))
                .thenReturn(spaceshipOptional);

        SpaceshipDto result = spaceshipService.getById(SPACESHIP_ID);

        Assertions.assertEquals(SPACESHIP_ID, result.id());
    }


}


