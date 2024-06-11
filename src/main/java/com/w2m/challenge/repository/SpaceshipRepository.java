package com.w2m.challenge.repository;

import com.w2m.challenge.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> , JpaSpecificationExecutor<Spaceship> {


}
