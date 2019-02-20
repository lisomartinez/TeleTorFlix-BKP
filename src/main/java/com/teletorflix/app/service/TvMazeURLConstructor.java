package com.teletorflix.app.service;

public interface TvMazeURLConstructor {
    String getBaseURL();
    String getShowByIdURL(int id);
    String getShowsPageURL(int page);
    String getSearchShowByName(String name);
    String getSingleSearchByName(String name);
}
