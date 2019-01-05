package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;
import org.springframework.stereotype.Service;

@Service
public interface ShowService {
    Show getShow(int id);
}
