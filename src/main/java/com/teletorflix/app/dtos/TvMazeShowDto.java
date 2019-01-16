package com.teletorflix.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvMazeShowDto {

    private int id;
    private String name;
    private String type = "N/A";
    private String language = "N/A";
    private List<String> genres = List.of("N/A");
    private String status = "N/A";
    private int runtime = 0;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiered = LocalDate.of(1900, 1, 1);

    private String officialSite = "N/A";
    private TvMazeScheduleDto schedule;

    private String tvMazeUrl = "N/A";
    private String imdbUrl = "N/A";
    private String imageUrl = "N/A";
    private String summary = "N/A";

    public static ShowDtoBuilder builder() {
        return new ShowDtoBuilder();
    }


    @JsonSetter("url")
    private void setTvMazeUrl(String url) {
        this.tvMazeUrl = url;
    }

    @JsonProperty("image")
    private void originalImage(Map<String, String> image) {
        this.imageUrl = image.getOrDefault("original", "N/A");
    }

    @JsonProperty("externals")
    private void imdbUrl(Map<String, String> externals) {
        this.imdbUrl = externals.getOrDefault("imdb", "N/A");
    }


    @JsonSetter(value = "summary")
    private void setSummaryJson(String summaryJson) {
        if(summaryJson == null || summaryJson.isBlank()) {
            this.summary = "N/A";
        } else {
            Pattern pattern = Pattern.compile("(<.+?>)");
            Matcher matcher = pattern.matcher(summaryJson);

            this.summary = matcher.replaceAll("");
        }
    }

    public static class ShowDtoBuilder {
        private int id;
        private String name;
        private String type = "N/A";
        private String language = "N/A";
        private List<String> genres = List.of("N/A");
        private String status = "N/A";
        private int runtime = 0;

        private LocalDate premiered = LocalDate.of(1900, 1, 1);

        private String officialSite = "N/A";
        private TvMazeScheduleDto schedule = new TvMazeScheduleDto();

        private String tvMazeUrl = "N/A";
        private String imdbUrl = "N/A";
        private String imageUrl = "N/A";
        private String summary = "N/A";


        public ShowDtoBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ShowDtoBuilder tvMazeUrl(String url) {
            this.tvMazeUrl = url;
            return this;
        }

        public ShowDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ShowDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ShowDtoBuilder language(String language) {
            this.language = language;
            return this;
        }

        public ShowDtoBuilder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public ShowDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ShowDtoBuilder runtime(int runtime) {
            this.runtime = runtime;
            return this;
        }

        public ShowDtoBuilder premiered(LocalDate premiered) {
            this.premiered = premiered;
            return this;
        }

        public ShowDtoBuilder officialSite(String officialSite) {
            this.officialSite = officialSite;
            return this;
        }

        public ShowDtoBuilder schedule(TvMazeScheduleDto schedule) {
            this.schedule = schedule;
            return this;
        }

        public ShowDtoBuilder imageUrl(String image) {
            this.imageUrl = image;
            return this;
        }

        public ShowDtoBuilder imdbUrl(String url) {
            this.imdbUrl = url;
            return this;
        }

        public ShowDtoBuilder summary(String summary) {


            this.summary = summary;
            return this;
        }

        public TvMazeShowDto build() {
            return new TvMazeShowDto(id, name,type, language, genres, status, runtime, premiered, officialSite,
                    schedule, tvMazeUrl, imdbUrl, imageUrl, summary);
        }
    }

}
