package com.javi.spotify.client.auth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
public class ClientCredentialsFlowTest {
    @Mock
    private RestTemplate httpClient;

    private ClientCredentialsFlow clientCredentialsFlow;

    @Before
    public void setUp() {
        this.clientCredentialsFlow = new ClientCredentialsFlow(this.httpClient, "", "");
    }

    @Test
    public void getAccessToken() {
        Mockito.when(httpClient.postForObject(anyString(), any(), any())).thenReturn(new AccessToken());
        AccessToken accessToken = clientCredentialsFlow.getAccessToken();
        Assert.assertNotNull(accessToken);
    }
}
