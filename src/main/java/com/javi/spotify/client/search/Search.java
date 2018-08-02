package com.javi.spotify.client.search;

// https://developer.spotify.com/documentation/web-api/reference/search/search/

import com.javi.spotify.client.auth.AccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * For realize search in Spotify API.
 */
public class Search {
    private RestTemplate httpClient;
    private static String URL = "https://api.spotify.com/v1/search";

    /**
     * Constructor.
     *
     * @param httpClient {@link RestTemplate}
     */
    public Search(RestTemplate httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @param query       {@link Query}
     * @param accessToken {@link AccessToken}
     * @return {@link String}
     */
    public <ObjectType> ObjectType query(Query query, AccessToken accessToken, Class<ObjectType> type) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken.getValue());
        HttpEntity httpEntity = new HttpEntity<>(new ArrayList<>(), httpHeaders);

        String endpoint = Search.URL + "?" + this.queryToQueryParams(query);
        ResponseEntity<ObjectType> response = this.httpClient.exchange(endpoint, HttpMethod.GET, httpEntity, type);
        return response.getBody();
    }

    /**
     * Transform a {@link Query} to a query params string.
     *
     * @param query {@link Query}
     * @return {@link String}
     */
    private String queryToQueryParams(Query query) {
        return
            "q=" + query.getText() +
            "&type=" + query.getType() +
            "&limit=" + query.getLimit() +
            "&offset=" + query.getOffset();
    }
}
