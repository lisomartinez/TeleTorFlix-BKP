package com.teletorflix.app.repository;

import com.teletorflix.app.model.Show;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository {
    Optional<Show> getById(int id);

    Show save(Show show);
}
