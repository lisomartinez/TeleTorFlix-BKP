package com.teletorflix.app.service;

import org.springframework.stereotype.Component;

@Component
public class TvMazeRestURLConstructor implements TvMazeURLConstructor {

    private static final String BASE_URL = "http://api.tvmaze.com/";
    private static final String SHOWS = "shows/";
    private static final String SHOWS_PAGE = "shows?page=";
    private static final String SEARCH = "search/shows?q=";
    private static final String SINGLE_SEARCH = "singlesearch/shows?q=";

    @Override
    public String getBaseURL() {
        return BASE_URL;
    }

    @Override
    public String getShowByIdURL(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("id should be greater than 0");
        }
        return BASE_URL + SHOWS + id;
    }

    @Override
    public String getShowsPageURL(int page) {
        if ( page < 0) {
            throw new IllegalArgumentException("page should be equals to or greater than 0");
        }
        return BASE_URL + SHOWS_PAGE + page;
    }

    @Override
    public String getSearchShowByName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty or blank");
        }
        return BASE_URL + SEARCH + name;
    }

    @Override
    public String getSingleSearchByName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty or blank");
        }
        return BASE_URL + SINGLE_SEARCH + name;
    }
}
