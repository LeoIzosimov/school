package com.jopov.schoolportal.secutity.models;

import com.jopov.schoolportal.secutity.enums.EStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Enumerated(EnumType.STRING)
    private EStatus status = EStatus.ACTIVE;

}
