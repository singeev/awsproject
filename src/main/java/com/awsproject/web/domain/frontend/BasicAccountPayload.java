package com.awsproject.web.domain.frontend;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BasicAccountPayload implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Email
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String description;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicAccountPayload)) return false;
        if (!super.equals(o)) return false;

        BasicAccountPayload that = (BasicAccountPayload) o;

        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
