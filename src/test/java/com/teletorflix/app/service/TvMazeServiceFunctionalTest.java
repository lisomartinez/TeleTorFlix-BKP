package com.teletorflix.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletorflix.app.dtos.JsonTestFiles;
import com.teletorflix.app.dtos.TvMazeShowDto;
import com.teletorflix.app.model.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class TvMazeServiceFunctionalTest {


    @Autowired
    private TvMazeService tvMazeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void FetchById_ValidId_ReturnShowJson() throws IOException {
        File showDeserialize = JsonTestFiles.getShow();
        TvMazeShowDto expectedDto = objectMapper.readValue(showDeserialize, TvMazeShowDto.class);
        Show expected = modelMapper.map(expectedDto, Show.class);

        Show show = tvMazeService.getShowById(1);
        assertThat(show).isEqualTo(expected);
    }

    @Test
    void FetchById_InvalidID_ThrowsShowNotFoundException() {
        assertThrows(IllegalArgumentException.class, () -> tvMazeService.getShowById(0));
    }
}