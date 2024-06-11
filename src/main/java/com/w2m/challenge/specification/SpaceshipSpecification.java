package com.w2m.challenge.specification;

import com.w2m.challenge.model.Spaceship;
import org.springframework.data.jpa.domain.Specification;

public class SpaceshipSpecification {

    private SpaceshipSpecification(){}
    public static Specification<Spaceship> nameLike(String nameLike) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + nameLike + "%");
    }

}
