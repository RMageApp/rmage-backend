package com.rmage.rmage_backend.tenant;

import com.rmage.rmage_backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TENANT")
public class Tenant extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenantStatus status;
}
