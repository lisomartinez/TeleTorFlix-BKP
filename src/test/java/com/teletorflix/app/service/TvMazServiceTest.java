package com.teletorflix.app.service;

import com.teletorflix.app.Config.TvMazeConfig;
import com.teletorflix.app.exceptions.ShowNotFoundException;
import com.teletorflix.app.model.Externals;
import com.teletorflix.app.model.Image;
import com.teletorflix.app.model.Show;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TvMazServiceTest {

    private TvMazeService tvMazeService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TvMazeConfig tvMazeConfig;

    @BeforeEach
    void setUp() {
        this.tvMazeService = new TvMazeServiceImpl(restTemplate, tvMazeConfig);
    }

    @Test
    @DisplayName("Fetch Show by Id should return Show instance with same Id")
    void fetchShow_ById_shouldReturnShow() {
        int id = 1;
        Show show = Mockito.mock(Show.class);
        when(tvMazeConfig.getShowByIdURL(anyInt())).thenReturn("http://api.tvmaze.com/shows/1");
        when(restTemplate.getForObject("http://api.tvmaze.com/shows/1", Show.class)).thenReturn(show);
        Optional<Show> returnedShow = tvMazeService.fetchById(id);
        assertThat(returnedShow.orElseThrow(ShowNotFoundException::new)).isEqualTo(show);
    }

    @Test
    @DisplayName("Fetch Non-Existent Show Throws ShowNotFoundException")
    void fetchShow_NonExistentShow_shouldThrowShowNotFoundException() {
        int id = 1;
        Show show = Mockito.mock(Show.class);
        when(tvMazeConfig.getShowByIdURL(anyInt())).thenReturn("http://api.tvmaze.com/shows/1");
        when(restTemplate.getForObject("http://api.tvmaze.com/shows/1", Show.class)).thenReturn(null);
        Optional<Show> returnedShow = tvMazeService.fetchById(id);
        assertThrows(ShowNotFoundException.class, () -> returnedShow.orElseThrow(ShowNotFoundException::new));
    }

    @Test
    @DisplayName("Fetch Show should call TvMazeConfig's GetShowByIdURL method")
    void fetchShow_ById_shouldCallTvMazeConfigGetShowByIdUrl() {
        int id = 1;
        when(tvMazeConfig.getShowByIdURL(anyInt())).thenReturn("http://api.tvmaze.com/shows/1");
        tvMazeService.fetchById(id);
        verify(tvMazeConfig).getShowByIdURL(id);
    }

    @Test
    @DisplayName("Fetch Show should call RestTemplate's getForObject method")
    void fetchShow_ById_shouldCallRestTemplateGetForObject() {
        int id = 1;
        Show show = Mockito.mock(Show.class);
        when(tvMazeConfig.getShowByIdURL(anyInt())).thenReturn("http://api.tvmaze.com/shows/1");
        when(restTemplate.getForObject("http://api.tvmaze.com/shows/1", Show.class)).thenReturn(show);
        tvMazeService.fetchById(id);
        verify(restTemplate).getForObject("http://api.tvmaze.com/shows/1", Show.class);
    }


}
