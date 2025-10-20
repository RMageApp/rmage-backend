package com.rmage.rmage_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User extends BaseEntity{

    /**
     * Base user class using inheritance with discriminator column.
     * User types are differentiated by the "role" column with values from Role enum.
     *
     * @see Tenant
     * @see Landlord
     */

    private String name;

    private String phone;

    @Column(unique = true, nullable = false)
    private String email;
}
