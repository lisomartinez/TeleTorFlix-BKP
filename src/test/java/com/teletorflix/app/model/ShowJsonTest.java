package com.teletorflix.app.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletorflix.app.dtos.ExternalsDto;
import com.teletorflix.app.dtos.ImageDto;
import com.teletorflix.app.dtos.ScheduleDto;
import com.teletorflix.app.dtos.ShowDto;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ShowJsonTest {

    private JacksonTester<ShowDto> json;
    private Logger logger;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Show JSON should return Show instance")
    void deserializeShowJson_returnShowInstance() throws IOException {
        ShowDto show = ShowDto.builder()
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
                .schedule(ScheduleDto.of(List.of(DayOfWeek.THURSDAY), LocalTime.of(22, 0)))
                .externals(ExternalsDto.of(25988, 264492, "tt1553656"))
                .image(ImageDto.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg"))
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.")
                .build();

        ShowDto showDto = json.readObject(JsonTestFiles.getShowDeserialize());

        assertThat(showDto).isEqualTo(show);
    }


    @Test
    @DisplayName("Serialize Show should return JSON String")
    void serializeShow_returnJson() throws IOException {
        ShowDto show = ShowDto.builder()
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
                .schedule(ScheduleDto.of(List.of(DayOfWeek.THURSDAY), LocalTime.of(22, 0)))
                .externals(ExternalsDto.of(25988, 264492, "tt1553656"))
                .image(ImageDto.of("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg"))
                .summary("<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>")
                .build();


        JsonContent<ShowDto> showDtoJsonContent = json.write(show);

        assertThat(showDtoJsonContent).isEqualToJson(JsonTestFiles.getShowSerielizeExpected());
    }


    @Test
    @DisplayName("Serialize Show NULL should throw IllegalArgumentException")
    void serialize_Null_Throws() throws IOException {
        ShowDto show = null;
        assertThrows(IllegalArgumentException.class, () -> json.write(show));
    }
}
