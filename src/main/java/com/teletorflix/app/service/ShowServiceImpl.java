package com.teletorflix.app.service;

import com.teletorflix.app.exceptions.ShowNotFoundException;
import com.teletorflix.app.model.Show;
import com.teletorflix.app.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show getShow(int id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException("Show with id=" + id + " not found"));
    }
}
