package com.javi.spotify.client.auth;

import org.junit.Assert;
import org.junit.Test;

public class AccessTokenTest {
    @Test
    public void isExpired_isAlreadyExpired() {
        AccessToken accessToken = new AccessToken();
        accessToken.setExpiresIn(-1);
        Assert.assertTrue(accessToken.isExpired());
    }

    @Test
    public void isExpired_isNonExpired() {
        AccessToken accessToken = new AccessToken();
        accessToken.setExpiresIn(1);
        Assert.assertFalse(accessToken.isExpired());
    }
}
