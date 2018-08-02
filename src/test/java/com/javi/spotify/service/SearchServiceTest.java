package com.javi.spotify.service;

import com.javi.spotify.client.auth.AccessToken;
import com.javi.spotify.client.auth.ClientCredentialsFlow;
import com.javi.spotify.client.model.SearchResult;
import com.javi.spotify.client.search.Search;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class SearchServiceTest {
    private SearchService searchService;

    @Mock
    private SaveAlbumService saveAlbumService;

    @Mock
    private Search search;

    @Mock
    private ClientCredentialsFlow clientCredentialsFlow;

    @Before
    public void setUp() {
        this.searchService = new SearchService(this.saveAlbumService,"", "");
        this.searchService.setClientCredentialsFlow(clientCredentialsFlow);
        this.searchService.setSearch(search);
    }

    @Test
    public void getAlbums() {
        Mockito.doNothing().when(saveAlbumService).saveAlbumsAsync(any());
        Mockito.when(clientCredentialsFlow.getAccessToken()).thenReturn(new AccessToken());
        Mockito.when(search.query(any(), any(), any(Class.class))).thenReturn(new SearchResult());
        SearchResult searchResult = this.searchService.getAlbums("", 1,1);
        Assert.assertNotNull(searchResult);
    }
}
