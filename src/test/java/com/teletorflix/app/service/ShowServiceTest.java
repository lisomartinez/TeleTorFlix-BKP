package com.teletorflix.app.service;

import com.teletorflix.app.model.Show;
import com.teletorflix.app.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    private ShowService showService;

    @Mock
    private ShowRepository showRepository;

    @BeforeEach
    void setUp() {
        showService = new ShowServiceImpl(showRepository);
    }

    @Test
    void getShow_ShowId_ReturnsShow() {
        int id = 1;
        Show show = Mockito.mock(Show.class);

        when(showRepository.findById(anyInt())).thenReturn(Optional.of(show));
        when(show.getId()).thenReturn(1);

        Show returnedShow = showService.getShow(id);
        assertThat(returnedShow.getId()).isEqualTo(id);
    }


    @Test
    void getShow_ShowId_ShouldCallShowRepositoryGetById() {
        int id = 1;
        Show show = Mockito.mock(Show.class);
        when(showRepository.findById(anyInt())).thenReturn(Optional.of(show));

        showService.getShow(id);
        verify(showRepository).findById(anyInt());
    }


}