package com.w2m.challenge.service;

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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SpaceshipServiceTest {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;

    private Spaceship spaceship;
    private List<Spaceship> spaceships;

    @BeforeEach
    void init(){
        spaceship = new Spaceship(SPACESHIP_ID, "nave-1");
        spaceships = List.of(spaceship);
    }

    @Test
    void getAllSpaceships(){

        Specification<Spaceship> filters = Specification.where(null);
        Pageable pageable = PageRequest.of(OFFSET, LIMIT_TEN);
        Page<Spaceship> spaceshipPage = new PageImpl<>(spaceships);

        when(spaceshipRepository.findAll(filters, pageable))
                .thenReturn(spaceshipPage);

        List<Spaceship> result = spaceshipService.getAll(null, OFFSET, LIMIT_TEN);

        Assertions.assertFalse(result.isEmpty());

    }

    @Test
    void getSpaceshipById(){

        Optional<Spaceship> spaceshipOptional = Optional.of(spaceship);

        when(spaceshipRepository.findById(SPACESHIP_ID))
                .thenReturn(spaceshipOptional);

        Spaceship spaceship = spaceshipService.getById(SPACESHIP_ID);

        Assertions.assertEquals(SPACESHIP_ID, spaceship.getId());
    }


}


