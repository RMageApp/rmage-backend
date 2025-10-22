package com.rmage.rmage_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TENANT")
public class Tenant extends User {
}
