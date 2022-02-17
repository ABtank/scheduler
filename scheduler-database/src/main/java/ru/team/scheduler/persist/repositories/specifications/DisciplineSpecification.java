package ru.team.scheduler.persist.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.team.scheduler.persist.entities.Discipline;

public final class DisciplineSpecification {

    public static Specification<Discipline> trueLiteral() {
        return (root, quary, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Discipline> findByName(String name) {
        return (root, quary, builder) -> builder.like(root.get("name"), name);
    }

    public static Specification<Discipline> nameContains(String name) {
        return (root, quary, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

}
