package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TvMazeService {

    Optional<Show> fetchById(int id);
}
