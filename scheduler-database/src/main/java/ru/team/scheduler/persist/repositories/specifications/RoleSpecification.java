package ru.team.scheduler.persist.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.team.scheduler.persist.entities.Role;

public final class RoleSpecification {

    public static Specification<Role> trueLiteral() {
        return (root, quary, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Role> findByName(String name) {
        return (root, quary, builder) -> builder.like(root.get("name"), name);
    }

    public static Specification<Role> nameContains(String name) {
        return (root, quary, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Role> descriptionContains(String descr) {
        return (root, quary, builder) -> builder.like(root.get("name"), "%" + descr + "%");
    }

}
