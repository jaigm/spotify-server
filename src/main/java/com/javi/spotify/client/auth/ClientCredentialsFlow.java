package com.javi.spotify.client.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Logic for get an access token bt a Client Credentials Flow.
 *
 * https://developer.spotify.com/documentation/general/guides/authorization-guide/
 * https://tools.ietf.org/html/rfc6749#section-4.4
 */
@SuppressWarnings("FieldCanBeLocal")
public class ClientCredentialsFlow {
    private RestTemplate httpClient;
    private static final String URL = "https://accounts.spotify.com/api/token";
    private String clientId;
    private String clientSecret;

    /**
     * Constructor.
     *
     * @param httpClient {@link RestTemplate}
     */
    public ClientCredentialsFlow(RestTemplate httpClient, String clientId, String clientSecret) {
        this.httpClient = httpClient;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Return an access token.
     *
     * @return {@link AccessToken}
     */
    public AccessToken getAccessToken() {
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(this.getRequestBody(), this.getHeaders());
        return httpClient.postForObject(ClientCredentialsFlow.URL, entity, AccessToken.class);
    }

    /**
     * @return {@link HttpHeaders}
     */
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credentials = this.getBase64AuthorizationEncoded();
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + credentials);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED.toString());
        return headers;
    }

    /**
     * @return {@link HashMap}
     */
    private MultiValueMap<String, String> getRequestBody() {
        return new LinkedMultiValueMap<String, String>() {{
            add("grant_type", GrantTypes.CLIENT_CREDENTIALS);
        }};
    }

    /**
     * @return {@link String}
     */
    private String getBase64AuthorizationEncoded() {
        return Base64Utils.encodeToString((this.clientId + ":" + this.clientSecret).getBytes());
    }
}
