package com.javi.spotify.controller;

import com.javi.spotify.client.model.SearchResult;
import com.javi.spotify.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;
    private final String DEFAULT_PAGE = "1";
    private final String DEFAULT_PAGE_SIZE = "20";

    /**
     * Constructor
     *
     * @param searchService {@link SearchService}
     */
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Return albums by a query params
     *
     * @param query {@link String}
     * @return {@link String}
     */
    @GetMapping(path = "/albums")
    public SearchResult getAlbums(
        @RequestParam String query,
        @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize
    ) {
        return this.searchService.getAlbums(query, page, pageSize);
    }
}
