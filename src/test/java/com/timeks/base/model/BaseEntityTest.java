package com.timeks.base.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityTest {

    @Test
    void shouldSetUUID() {
        var entityWithConstructor = new BaseEntity();
        System.out.println(entityWithConstructor.getKey());
        assertNotNull(entityWithConstructor.getKey());

        var baseAuditiableEntity = new AuditableBaseEntity();
        System.out.println(baseAuditiableEntity.getKey());
        assertNotNull(baseAuditiableEntity.getKey());


        var entityWithBuilder = BaseEntity.builder().build();
        System.out.println(entityWithBuilder.getKey());
        assertNotNull(entityWithBuilder.getKey());

        var baseAuiditableEntityWithBuilder = AuditableBaseEntity.builder().build();
        assertNotNull(baseAuiditableEntityWithBuilder.getKey());
    }

}
