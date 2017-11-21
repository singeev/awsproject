package com.awsproject.web.domain.frontend;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by singeev on 21/11/2017.
 */
@Data
public class FeedbackPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String firstName;
    private String lastName;
    private String feedback;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeedbackPojo{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", feedback='").append(feedback).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
