package com.teletorflix.app.service;

import com.teletorflix.app.dtos.TvMazeShowDto;

import java.util.List;

public interface TvMazeClient {
    TvMazeShowDto getById(int id);
    List<TvMazeShowDto> findPage(int page);
}
