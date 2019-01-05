package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ShowServiceTest {

    private ShowService showService;

    @BeforeEach
    void setUp() {
        showService = new ShowServiceImpl();
    }

    @Test
    void getShow_ShowId_ReturnsShow() {
        int id = 2;
        Show show = showService.getShow(id);
        assertThat(show.getId()).isEqualTo(id);
    }
}