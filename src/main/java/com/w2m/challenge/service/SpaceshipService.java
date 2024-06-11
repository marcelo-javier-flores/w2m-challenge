package com.w2m.challenge.service;

import com.w2m.challenge.model.Spaceship;

import java.util.List;

public interface SpaceshipService {

    void save(Spaceship spaceship);

    List<Spaceship> getAll(String nameFilter, Integer offset, Integer limit);

    Spaceship getById(Long id);

    void update(Spaceship spaceship);

    void delete(Long id);

}
