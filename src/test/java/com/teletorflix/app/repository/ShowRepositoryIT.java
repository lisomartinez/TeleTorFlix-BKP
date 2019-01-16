package com.teletorflix.app.repository;

import com.teletorflix.app.model.Genre;
import com.teletorflix.app.model.Schedule;
import com.teletorflix.app.model.ScheduleDay;
import com.teletorflix.app.model.Show;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ShowRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShowRepository showRepository;


    @Test
    @DirtiesContext
    void save_validShow_returnsSaveShow() {

        ScheduleDay thursday = entityManager.persistFlushFind(ScheduleDay.of("Thursday"));
        Schedule schedule = Schedule.of(Set.of(thursday), LocalTime.of(22, 0));
        schedule.setId(1);
        Genre drama = entityManager.persistFlushFind(Genre.of("Drama"));
        Genre scienceFiction = entityManager.persistFlushFind(Genre.of("Science_Fiction"));
        Genre thriller = entityManager.persistFlushFind(Genre.of("Thriller"));

        Show expected = Show.builder()
                .id(1)
                .name("Under the Dome")
                .tvMaze("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .genres(List.of(drama, scienceFiction, thriller))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .schedule(schedule)
                .imdb("https://www.imdb.com/title/tt1553656/")
                .image("http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg")
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off " +
                        "of the rest of the world by an enormous transparent dome. The town's inhabitants must " +
                        "deal with surviving the post-apocalyptic conditions while searching for answers about the " +
                        "dome, where it came of and if and when it will go away.")
                .build();

        Show savedShow = showRepository.save(expected);

        assertThat(savedShow).isEqualTo(expected);

    }


    @Test
    @Disabled
    void saveMultipleShows_SameGenresAlreadySaved() {
        List<Show> expectedList = new ArrayList<>();


        Show show1 = Show.builder()
                .id(1)
                .name("Under the Dome")
                .tvMaze("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .genres(List.of(Genre.of("Drama"), Genre.of("Science_fiction"), Genre.of("Thriller")))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .schedule(Schedule.of(Set.of(ScheduleDay.of("Thursday")), LocalTime.of(22, 0)))
                .imdb("https://www.imdb.com/title/tt1553656/")
                .image("http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg")
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off " +
                        "of the rest of the world by an enormous transparent dome. The town's inhabitants must " +
                        "deal with surviving the post-apocalyptic conditions while searching for answers about the " +
                        "dome, where it came of and if and when it will go away.")
                .build();

        expectedList.add(show1);


        List<Show> savedShowLists = new ArrayList<>();

        Show savedShow1 = showRepository.save(show1);
        savedShowLists.add(savedShow1);


        Show show2 = Show.builder()
                .id(2)
                .name("Person of Interest")
                .tvMaze("http://www.tvmaze.com/shows/2/person-of-interest")
                .type("Scripted")
                .language("English")
                .genres(List.of(Genre.of("Action"), Genre.of("Crime"), Genre.of("Science_fiction")))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2011, 9, 22))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .schedule(Schedule.of(Set.of(ScheduleDay.of("Thursday")), LocalTime.of(22, 0)))
                .imdb("https://www.imdb.com/title/tt1839578")
                .image("\"http://static.tvmaze.com/uploads/images/original_untouched/163/407679.jpg")
                .summary("<p>You are being watched. The government has a secret system, a machine that spies " +
                        "on you every hour of every day. I know because I built it. I designed the Machine to detect " +
                        "acts of terror but it sees everything. Violent crimes involving ordinary people. " +
                        "People like you. Crimes the government considered \\\"irrelevant\\\". " +
                        "They wouldn't act so I decided I would. But I needed a partner. Someone with the " +
                        "skills to intervene. Hunted by the authorities, we work in secret. You'll never find us." +
                        " But victim or perpetrator, if your number is up, we'll find you.</p>")
                .build();

        expectedList.add(show2);

        Show savedShow2 = showRepository.save(show2);
        savedShowLists.add(savedShow2);


        assertThat(savedShowLists.size()).isEqualTo(expectedList.size());
        for (int i = 0; i < savedShowLists.size(); i++) {
            assertShow(expectedList.get(i), savedShowLists.get(i));
        }

    }

    private void assertShow(Show expected, Show savedShow) {
        System.out.println("expected = " + expected);
        System.out.println("savedShow = " + savedShow);

        assertThat(savedShow.getId()).isEqualTo(expected.getId());
        assertThat(savedShow.getName()).isEqualTo(expected.getName());
        assertThat(savedShow.getTvMaze()).isEqualTo(expected.getTvMaze());
        assertThat(savedShow.getType()).isEqualTo(expected.getType());
        assertThat(savedShow.getLanguage()).isEqualTo(expected.getLanguage());

        assertGenre(expected, savedShow);

        assertThat(savedShow.getStatus()).isEqualTo(expected.getStatus());
        assertThat(savedShow.getRuntime()).isEqualTo(expected.getRuntime());
        assertThat(savedShow.getPremiered()).isEqualTo(expected.getPremiered());
        assertThat(savedShow.getOfficialSite()).isEqualTo(expected.getOfficialSite());

        assertThat(savedShow.getSchedule().getTime()).isEqualTo(expected.getSchedule().getTime());

        assertScheduleDays(expected, savedShow);

        assertThat(savedShow.getImdb()).isEqualTo(expected.getImdb());
        assertThat(savedShow.getImage()).isEqualTo(expected.getImage());
        assertThat(savedShow.getSummary()).isEqualTo(expected.getSummary());
    }

    private void assertScheduleDays(Show expected, Show savedShow) {
        assertThat(savedShow.getSchedule().getDays().size()).isEqualTo(expected.getSchedule().getDays().size());

        List<ScheduleDay> savedDays = List.copyOf(savedShow.getSchedule().getDays());
        List<ScheduleDay> expectedDays = List.copyOf(expected.getSchedule().getDays());

        for (int i = 0; i < savedShow.getSchedule().getDays().size(); i++) {
            assertThat(savedDays.get(i).getDay()).isEqualTo(expectedDays.get(i).getDay());
        }
    }

    private void assertGenre(Show expected, Show savedShow) {
        assertThat(savedShow.getGenres().size()).isEqualTo(expected.getGenres().size());

        for (int i = 0; i < savedShow.getGenres().size(); i++) {
            assertThat(savedShow.getGenres().get(i).getName()).isEqualTo(savedShow.getGenres().get(i).getName());
        }
    }
}