package com.w2m.challenge.service;

import com.w2m.challenge.exception.NotFoundException;
import com.w2m.challenge.model.Spaceship;
import com.w2m.challenge.repository.SpaceshipRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.w2m.challenge.config.CaffeineCacheConfig.SPACESHIP_CACHE;
import static com.w2m.challenge.specification.SpaceshipSpecification.nameLike;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    @Override
    public void save(Spaceship spaceship) {
        spaceshipRepository.save(spaceship);
    }

    @Override
    @Cacheable(cacheNames = SPACESHIP_CACHE)
    public List<Spaceship> getAll(String nameFilter, Integer offset, Integer limit) {

        Specification<Spaceship> filters = Specification.where(StringUtils.isBlank(nameFilter) ? null : nameLike(nameFilter));

        Pageable pageable = PageRequest.of(offset, limit);
        Page<Spaceship> spaceshipPage = spaceshipRepository.findAll(filters, pageable);

        return spaceshipPage.getContent();

    }

    @Override
    public Spaceship getById(Long id) {

        Optional<Spaceship> foundSpaceship = spaceshipRepository.findById(id);

        if(foundSpaceship.isEmpty())
            throw new NotFoundException("Spaceship not found");

        return foundSpaceship.get();
    }

    @Override
    public void update(Spaceship spaceship) {
        spaceshipRepository.save(spaceship);
    }

    @Override
    public void delete(Long id) {
        spaceshipRepository.deleteById(id);
    }
}