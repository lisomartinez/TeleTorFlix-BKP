package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;
import com.teletorflix.app.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show getShow(int id) {
        Optional<Show> show = showRepository.getById(id);
        if (show.isPresent()) return show.get();

        Show showFromTvMaze = fetchShowTvMazeBy(id).orElseThrow(RuntimeException::new);
        return showRepository.save(showFromTvMaze);
    }

    private Optional<Show> fetchShowTvMazeBy(int id) {
        return Optional.empty();
    }


}
