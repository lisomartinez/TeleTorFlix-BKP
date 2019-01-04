package com.teletorflix.app.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ShowTest {

    private JacksonTester<Show> json;
    private Logger logger;

    @BeforeEach
    void setUp() {
        logger = LogManager.getLogger();
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    @DisplayName("Deserialize Show JSON should return Show instance")
    void DeserializeShowJson_returnShowInstance() throws IOException {
        Show show = getShow();
        assertThat(json.readObject(JsonFiles.getShowDeserialize())).isEqualTo(show);
    }


    @Test
    @DisplayName("Serialize JSON Show should return JSON String")
    void SerializeShow_returnJson() throws IOException {
        Show show = getShow();
        assertThat(json.write(show)).isEqualToJson(JsonFiles.getShowSerielizeExpected());
    }

    private Show getShow() {
        return Show.builder()
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
                .externals(Externals.getInstance(25988,264492, "tt1553656" ))
                .image(Image.getInstance("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg"))
                .summary("<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>")
                .build();
    }
}
