package com.javi.spotify.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

// todo: Separate persistence layer with the spotify model layer.
@Data
public class Album {
    @JsonProperty("id")
    private String spotifyId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("artists")
    private List<Artist> artists;

    @JsonProperty("images")
    private List<Image> images;
}
