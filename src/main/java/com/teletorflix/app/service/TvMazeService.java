package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;

public interface TvMazeService {

    Show fetchById(int id);
}
