package com.teletorflix.app.repository;

public interface TvMazeURLConstructor {
    String getBaseURL();
    String getShowByIdURL(int id);
    String getShowsPageURL(int page);
    String getSearchShowByName(String name);
    String getSingleSearchByName(String name);
}
