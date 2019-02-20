package com.teletorflix.app.service;

import com.teletorflix.app.dtos.TvMazeShowDto;
import com.teletorflix.app.model.Show;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvMazeServiceImpl implements TvMazeService {

    private TvMazeClient client;

    private ModelMapper modelMapper;

    @Autowired
    public TvMazeServiceImpl(TvMazeClient client, ModelMapper modelMapper) {
        this.client = client;
        this.modelMapper = modelMapper;
    }

    @Override
    public Show getShowById(int id)  {
        TvMazeShowDto show = client.getById(id);
        return modelMapper.map(show, Show.class);
    }

}
