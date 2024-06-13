package com.w2m.challenge.service;

import com.w2m.challenge.dto.NewSpaceshipDto;
import com.w2m.challenge.dto.SpaceshipDto;
import com.w2m.challenge.exception.NotFoundException;
import com.w2m.challenge.mapper.SpaceshipMapper;
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
import static com.w2m.challenge.mapper.SpaceshipMapper.mapToSpaceship;
import static com.w2m.challenge.mapper.SpaceshipMapper.mapToSpaceshipDto;
import static com.w2m.challenge.specification.SpaceshipSpecification.nameLike;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipServiceImpl(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    @Override
    public Spaceship save(NewSpaceshipDto newSpaceshipDto) {
        return spaceshipRepository.save(mapToSpaceship(newSpaceshipDto));
    }

    @Override
    @Cacheable(cacheNames = SPACESHIP_CACHE)
    public List<SpaceshipDto> getAll(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Spaceship> spaceshipPage = spaceshipRepository.findAll(pageable);

        return spaceshipPage.getContent().stream().map(SpaceshipMapper::mapToSpaceshipDto).toList();
    }

    @Override
    public List<SpaceshipDto> getAllByName(String nameFilter) {
        Specification<Spaceship> filters = Specification.where(StringUtils.isBlank(nameFilter) ? null : nameLike(nameFilter));
        return spaceshipRepository.findAll(filters).stream().map(SpaceshipMapper::mapToSpaceshipDto).toList();
    }

    @Override
    public SpaceshipDto getById(Long id) {

        Optional<Spaceship> foundSpaceship = spaceshipRepository.findById(id);

        if(foundSpaceship.isEmpty())
            throw new NotFoundException("Spaceship not found");

        return mapToSpaceshipDto(foundSpaceship.get());
    }

    @Override
    public Spaceship update(SpaceshipDto spaceshipDto) {
        return spaceshipRepository.save(mapToSpaceship(spaceshipDto));
    }

    @Override
    public void delete(Long id) {
        spaceshipRepository.deleteById(id);
    }
}
