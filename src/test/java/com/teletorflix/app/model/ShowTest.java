package com.teletorflix.app.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShowTest {

    @Test
    void staticFactoryMethod_ValidFields_ReturnsInstance() {
        Schedule schedule = Mockito.mock(Schedule.class);
        Externals externals = Mockito.mock(Externals.class);
        Image image = Image.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg",
                "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg");

        Show show = Show.builder()
                .id(1)
                .name("Under the Dome")
                .url("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .genres(List.of("Drama", "Science-Fiction", "Thriller"))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .schedule(schedule)
                .externals(externals)
                .image(image)
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off " +
                        "from the rest of the world by an enormous transparent dome. The town's inhabitants must " +
                        "deal with surviving the post-apocalyptic conditions while searching for answers about the " +
                        "dome, where it came from and if and when it will go away.")
                .build();

        assertThat(show.getId()).isEqualTo(1);
        assertThat(show.getName()).isEqualTo("Under the Dome");
        assertThat(show.getUrl()).isEqualTo("http://www.tvmaze.com/shows/1/under-the-dome");
        assertThat(show.getType()).isEqualTo("Scripted");
        assertThat(show.getLanguage()).isEqualTo("English");
        assertThat(show.getGenres()).isEqualTo(List.of("Drama", "Science-Fiction", "Thriller"));
        assertThat(show.getStatus()).isEqualTo("Ended");
        assertThat(show.getRuntime()).isEqualTo(60);
        assertThat(show.getPremiered()).isEqualTo(LocalDate.of(2013, 6, 24));
        assertThat(show.getOfficialSite()).isEqualTo("http://www.cbs.com/shows/under-the-dome/");
        assertThat(show.getSchedule()).isEqualTo(schedule);
        assertThat(show.getExternals()).isEqualTo(externals);
        assertThat(show.getImage()).isEqualTo(image);
        assertThat(show.getSummary()).isEqualTo("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off " +
                "from the rest of the world by an enormous transparent dome. The town's inhabitants must " +
                "deal with surviving the post-apocalyptic conditions while searching for answers about the " +
                "dome, where it came from and if and when it will go away.");

    }
}