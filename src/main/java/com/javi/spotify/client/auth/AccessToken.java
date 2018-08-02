package com.javi.spotify.client.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Represent an Access Token.
 *
 * See https://developer.spotify.com/documentation/general/guides/authorization-guide/
 */
@Data
public class AccessToken {
    @JsonProperty("access_token")
    private String value;

    @JsonProperty("token_type")
    private String type;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("scope")
    private String scope;

    private Date createdAt = new Date();

    /**
     * @return Return if the access token is expired or not.
     */
    public boolean isExpired() {
        Date expiresDate = Date.from(createdAt.toInstant().plusSeconds(expiresIn));

        return expiresDate.before(new Date());
    }
}
