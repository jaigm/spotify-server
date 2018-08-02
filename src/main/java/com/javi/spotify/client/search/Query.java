package com.javi.spotify.client.search;

import lombok.Data;
import lombok.NonNull;

/**
 * Class for realize queries to the Spotify Search API.
 *
 * https://developer.spotify.com/documentation/web-api/reference/search/search/
 */
@Data
public class Query {
    /**
     * The text that you want to search.
     * */
    private String text;

    /**
     * Possible values in {@link QueryTypes}
     * */
    private String type;
    private Integer limit;
    private Integer offset;

    /**
     * Modify the limit and offset of the {@link Query} object.
     *
     * @param page {@link Integer} The page that you want. Example: 1 -> for the Page 1.
     * @param size {@link Integer} The page size. Example: 20 -> 20 elements by page.
     */
    public void setPageAndSize(@NonNull Integer page, @NonNull  Integer size) {
        this.limit = size;

        // Calculate the offset.
        this.offset = ((page - 1) * size);
    }
}
