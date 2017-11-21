package com.awsproject.enums;

import lombok.Getter;

/**
 * Created by singeev on 21/11/2017.
 */
@Getter
public enum PlansEnum {
    BASIC (1, "Basic"),
    PRO (2, "Pro");

    private final int id;
    private final String planName;

    PlansEnum(int id, String planName) {
        this.id = id;
        this.planName = planName;
    }
}
