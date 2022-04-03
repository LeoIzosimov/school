package com.jopov.schoolportal.secutity.models;

import com.jopov.schoolportal.secutity.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public Role(ERole roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return String.valueOf(roleName);
    }

}
