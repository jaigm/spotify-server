package com.javi.spotify.service;

import com.javi.spotify.client.auth.AccessToken;
import com.javi.spotify.client.auth.ClientCredentialsFlow;
import com.javi.spotify.client.model.SearchResult;
import com.javi.spotify.client.search.Query;
import com.javi.spotify.client.search.QueryTypes;
import com.javi.spotify.client.search.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {
    private Search search;

    private ClientCredentialsFlow clientCredentialsFlow;

    private AccessToken currentAccessToken; // A stored access token for re-use in calls.

    /**
     * Constructor.
     */
    public SearchService(
        @Value("${spotify.client.client_id}") String clientId,
        @Value("${spotify.client.client_secret}") String clientSecret
    ) {
        RestTemplate httpClient = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        this.clientCredentialsFlow = new ClientCredentialsFlow(httpClient, clientId, clientSecret);
        this.search = new Search(httpClient);
    }

    public void setClientCredentialsFlow(ClientCredentialsFlow clientCredentialsFlow) {
        this.clientCredentialsFlow = clientCredentialsFlow;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    /**
     * @param text {@link String}
     * @return {@link String} The list of albums given by the spotify search.
     */
    public SearchResult getAlbums(String text, Integer page, Integer pageSize) {
        Query query = new Query(); // Spotify Query.
        query.setText(text);
        query.setType(QueryTypes.ALBUM);
        query.setPageAndSize(page, pageSize);

        return this.search.query(query, this.getCurrentAccessToken(), SearchResult.class);
    }

    /**
     * Use a valid non-expired access token, otherwise request a new one.
     *
     * @return {@link AccessToken}
     */
    private AccessToken getCurrentAccessToken() {
        if (this.currentAccessToken == null || this.currentAccessToken.isExpired()) {
            this.currentAccessToken = this.clientCredentialsFlow.getAccessToken();
        }

        return this.currentAccessToken;
    }
}
