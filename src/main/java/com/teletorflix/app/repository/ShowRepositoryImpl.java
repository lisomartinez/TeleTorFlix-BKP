package com.teletorflix.app.repository;

import com.teletorflix.app.model.Show;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShowRepositoryImpl implements ShowRepository {
    @Override
    public Optional<Show> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Show save(Show show) {
        return null;
    }
}
