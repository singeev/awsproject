package com.awsproject.backend.persistence.domain.backend;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by singeev on 21/11/2017.
 */
@Entity
@Data
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;
}
