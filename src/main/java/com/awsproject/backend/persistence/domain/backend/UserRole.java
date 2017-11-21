package com.awsproject.backend.persistence.domain.backend;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by singeev on 21/11/2017.
 */
@Entity
@Table(name = "user_role")
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
