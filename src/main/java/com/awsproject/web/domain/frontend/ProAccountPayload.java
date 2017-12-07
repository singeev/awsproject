package com.awsproject.web.domain.frontend;

import lombok.Data;

@Data
public class ProAccountPayload extends BasicAccountPayload {
    private String cardNumber;
    private String cardCode;
    private String cardMonth;
    private String cardYear;
}
