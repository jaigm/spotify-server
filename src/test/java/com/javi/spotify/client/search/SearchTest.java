package com.javi.spotify.client.search;

import com.javi.spotify.client.auth.AccessToken;
import com.javi.spotify.client.model.Album;
import com.javi.spotify.client.model.Albums;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
public class SearchTest {
    @Mock
    private RestTemplate httpClient;

    private Search search;

    @Before
    public void setUp() {
        this.search = new Search(this.httpClient);
    }

    @Test
    public void query() {
        Albums body = new Albums();
        ResponseEntity<Albums> responseEntity = new ResponseEntity<>(body, HttpStatus.OK);

        Mockito.when(httpClient.exchange(anyString(), any(), any(), any(Class.class))).thenReturn(responseEntity);

        Query query = new Query();
        query.setPageAndSize(1, 10);

        AccessToken accessToken = new AccessToken();

        Albums albums = this.search.query(query, accessToken, Albums.class);
        Assert.assertNotNull(albums);
    }
}
