package com.teletorflix.app.Config;

import org.springframework.stereotype.Component;

@Component
public interface TvMazeConfig {
    String getBaseURL();
    String getShowByIdURL(int id);
    String getSearchShowByName(String name);
    String getSingleSearchByName(String name);
}
