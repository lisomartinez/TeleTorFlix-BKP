package com.teletorflix.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@DirtiesContext
@ActiveProfiles("test")
class EpisodeRepositoryTest {

    @Autowired
    private EpisodeRepository episodeRepository;


}