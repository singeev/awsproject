package com.awsproject.backend.persistence.domain.backend;

import com.awsproject.enums.PlansEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by singeev on 21/11/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    private String name;

    public Plan(PlansEnum plansEnum) {
        this.id = plansEnum.getId();
        this.name = plansEnum.getPlanName();
    }
}
