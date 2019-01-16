package com.teletorflix.app.repository;

import com.teletorflix.app.exceptions.ShowNotFoundException;
import com.teletorflix.app.model.Episode;
import com.teletorflix.app.model.Season;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext
class SeasonRepositoryIT {

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    @DisplayName("")
    void save_validSeasonAndEpisode_returnsSeasonInstance() {
        List<Episode> episodes = getEpisodes();

        Season season = getSeason(episodes);

        Season saved = seasonRepository.save(season);

        assertThat(saved.getId()).isEqualTo(season.getId());
        assertThat(saved.getNumber()).isEqualTo(season.getNumber());
        assertThat(saved.getEpisodeOrder()).isEqualTo(season.getEpisodeOrder());
        assertThat(saved.getPremiereDate()).isEqualTo(season.getPremiereDate());
        assertThat(saved.getEndDate()).isEqualTo(season.getEndDate());
        assertThat(saved.getImage()).isEqualTo(season.getImage());
        assertThat(saved.getTvMaze()).isEqualTo(season.getTvMaze());
        assertThat(saved.getSummary()).isEqualTo(season.getSummary());

    }

    private Season getSeason(List<Episode> episodes) {
        return Season.builder()
                    .id(1)
                    .number(1)
                    .episodeOrder(13)
                    .premiereDate(LocalDate.of(2013,6,24))
                    .endDate(LocalDate.of(2013, 9, 16))
                    .image("http://static.tvmaze.com/uploads/images/original_untouched/24/60941.jpg")
                    .tvMaze("http://www.tvmaze.com/seasons/1/under-the-dome-season-1")
                    .summary("N/A")
                    .episodes(episodes)
                    .build();
    }

    private List<Episode> getEpisodes() {
        Episode episode = Episode.builder()
                .id(1)
                .name("Pilot")
                .number(1)
                .airDate(LocalDate.of(2016,6,24))
                .airTime(LocalTime.of(22,0))
                .runtime(60)
                .image("http://static.tvmaze.com/uploads/images/original_untouched/1/4388.jpg")
                .tvMaze("http://www.tvmaze.com/episodes/1/under-the-dome-1x01-pilot")
                .summary("When the residents of Chester's Mill find themselves trapped under a massive " +
                        "transparent dome with no way out, they struggle to survive as resources rapidly " +
                        "dwindle and panic quickly escalates.")
                .build();

        return List.of(episode);
    }

    @Test
    void save_validSeasonAndEpisodes_returnsSeasonWithSameEpisodes() {
        List<Episode> episodes = getEpisodes();

        Season season = getSeason(episodes);

        Season saved = seasonRepository.save(season);

        assertThat(saved.getEpisodes()).containsExactlyInAnyOrderElementsOf(episodes);
    }

    @Test
    void getSeason_withValidId_ReturnsInstance() {
        List<Episode> episodes = getEpisodes();

        Season season = getSeason(episodes);

        Season saved = seasonRepository.save(season);

        Season fetched = seasonRepository.findById(1).orElseThrow(ShowNotFoundException::new);

        assertThat(fetched).isEqualTo(saved);
    }

    @Test
    void getSeason_withInvalidId_ThrowsShowNotFoundException() {
        List<Episode> episodes = getEpisodes();

        Season season = getSeason(episodes);

        seasonRepository.save(season);

        assertThrows(ShowNotFoundException.class, () -> seasonRepository.findById(0).orElseThrow(ShowNotFoundException::new));
    }
}