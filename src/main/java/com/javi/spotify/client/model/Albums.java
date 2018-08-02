package com.javi.spotify.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Albums {
    @JsonProperty("items")
    private ArrayList<Album> items;
}
