package com.rmage.rmage_backend.landlord;

import com.rmage.rmage_backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("LANDLORD")
public class Landlord extends User {
}
