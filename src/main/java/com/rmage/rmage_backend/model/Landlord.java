package com.rmage.rmage_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@DiscriminatorValue("LANDLORD")
public class Landlord extends User{
}
