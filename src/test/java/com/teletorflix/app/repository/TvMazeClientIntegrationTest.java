package com.teletorflix.app.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teletorflix.app.dtos.JsonTestFiles;
import com.teletorflix.app.dtos.TvMazeScheduleDto;
import com.teletorflix.app.dtos.TvMazeShowDto;
import com.teletorflix.app.exceptions.TvMazeShowNotFoundException;
import com.teletorflix.app.service.TvMazeRestRespondeErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest({ TvMazeClient.class,
                  TvMazeURLConstructor.class,
                  TvMazeRestRespondeErrorHandler.class })
class TvMazeClientIntegrationTest {

    private final static String BASE_URL = "http://api.tvmaze.com/";
    private final static String SHOWS = "shows/";
    private final static String SHOWs_PAGE = "shows?page=";
    private final static String SEARCH = "search/shows?q=";
    private final static String SINGLE_SEARCH = "singlesearch/shows?q=";



    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    @Qualifier("tvMazeRestClient")
    private TvMazeClient tvMazeRestClient;

    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();

    }

    @Test
    void getById_ValidId_ReturnsShowDto() throws IOException {

        setUpGetById();

        TvMazeShowDto expected = TvMazeShowDto.builder()
                .id(1)
                .name("Under the Dome")
                .type("Scripted")
                .language("English")
                .genres(List.of("Drama", "Science-Fiction", "Thriller"))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .schedule(TvMazeScheduleDto.of(List.of(DayOfWeek.THURSDAY), LocalTime.of(22, 0)))
                .tvMazeUrl("http://www.tvmaze.com/shows/1/under-the-dome")
                .imdbUrl("tt1553656")
                .imageUrl("http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg")
                .summary("Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.")
                .build();


        TvMazeShowDto response = tvMazeRestClient.getById(expected.getId());

        assertThat(response).isEqualTo(expected);
    }

    private void setUpGetById() throws IOException {
        String expected = Files.readString(Paths.get(JsonTestFiles.getShow().getAbsolutePath()));
        server.expect(requestTo(BASE_URL + SHOWS + "1")).andRespond(withSuccess(expected, MediaType.APPLICATION_JSON));
    }

    @Test
    void getById_InvalidId_ThrowsShowNotFoundException() {
        assertThrows(IllegalArgumentException.class, () -> tvMazeRestClient.getById(0));
    }

    @Test
    void findPage_ValidPageNumber_ReturnsListOfShowDto() throws IOException {

        File showPage0 = JsonTestFiles.getShowPage0();

        setUpShowPage(showPage0);

        List<TvMazeShowDto> expectedList = objectMapper.readValue(showPage0, new TypeReference<List<TvMazeShowDto>>(){});

        List<TvMazeShowDto> response = tvMazeRestClient.findPage(0);

        assertThat(response.size()).isEqualTo(expectedList.size());
    }

    private void setUpShowPage(File showPage0) throws IOException {
        String expected = Files.readString(Paths.get(showPage0.getAbsolutePath()));
        server.expect(requestTo(BASE_URL + SHOWs_PAGE + "0")).andRespond(withSuccess(expected, MediaType.APPLICATION_JSON));
    }

    @Test
    @Disabled
    void findPage_InvalidPageNumber_ThrowsShowNotFoundException() {
        assertThrows(TvMazeShowNotFoundException.class, () -> tvMazeRestClient.findPage(1000000000));
    }

    @Test
    @Disabled
    void MoreThan20CallsEvery10_ThrowsTvMazeTooManyRequestException() {
         List<TvMazeShowDto> tvMazeShowDtos = new ArrayList<>();
         for (int i = 1; i <= 400; i++) {
              tvMazeShowDtos.add(tvMazeRestClient.getById(1));
          }
        System.out.println(tvMazeShowDtos);
         assertThat(tvMazeShowDtos.size()).isEqualTo(400);
    }
}