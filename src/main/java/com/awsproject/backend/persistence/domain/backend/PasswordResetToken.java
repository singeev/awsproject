package com.awsproject.backend.persistence.domain.backend;

import com.awsproject.backend.persistence.converters.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by singeev on 22/11/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class PasswordResetToken implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_TOKEN_LIFETIME_IN_MINUTES = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiry_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime expirationDate;

    public PasswordResetToken(String token, User user, LocalDateTime creationDateTime, int expirationInMinutes) throws IllegalArgumentException {
        if (token == null || user == null || creationDateTime == null) {
            throw new IllegalArgumentException("Token, user and creation date time can't be null");
        }
        if (expirationInMinutes == 0) {
            LOGGER.warn("The token expiration time in minutes is zero. Use default value {} minutes",
                    DEFAULT_TOKEN_LIFETIME_IN_MINUTES);
            expirationInMinutes = DEFAULT_TOKEN_LIFETIME_IN_MINUTES;
        }
        this.token = token;
        this.user = user;
        this.expirationDate = creationDateTime.plusMinutes(expirationInMinutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordResetToken)) return false;
        PasswordResetToken that = (PasswordResetToken) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
