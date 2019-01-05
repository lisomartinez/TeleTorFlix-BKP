package com.teletorflix.app.Config;

import org.springframework.stereotype.Component;

@Component
public class TvMazeConfigImpl implements TvMazeConfig {

    private final static String BASE_URL = "http://api.tvmaze.com/";
    private final static String SHOWS = "shows/";
    private final static String SEARCH = "search/shows?q=";
    private final static String SINGLE_SEARCH = "singlesearch/shows?q=";

    @Override
    public String getBaseURL() {
        return BASE_URL;
    }

    @Override
    public String getShowByIdURL(int id) {
        return BASE_URL + SHOWS + id;
    }

    @Override
    public String getSearchShowByName(String name) {
        return BASE_URL + SEARCH + name;
    }

    @Override
    public String getSingleSearchByName(String name) {
        return BASE_URL + SINGLE_SEARCH + name;
    }
}
