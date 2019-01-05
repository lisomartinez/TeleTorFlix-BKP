package com.teletorflix.app.service;

import com.teletorflix.app.Config.TvMazeConfig;
import com.teletorflix.app.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    private RestTemplate restTemplate;

    private TvMazeConfig tvMazeConfig;

    @Autowired
    public TvMazeServiceImpl(RestTemplate restTemplate, TvMazeConfig tvMazeConfig) {
        this.restTemplate = restTemplate;
        this.tvMazeConfig = tvMazeConfig;
    }

    @Override
    public Optional<Show> fetchById(int id) {
        return Optional.ofNullable(restTemplate.getForObject(tvMazeConfig.getShowByIdURL(id), Show.class));
    }
}
