package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {

    private final int id;
    private final String url;
    private final String name;
    private final String type;
    private final String language;
    private final List<String> genres;
    private final String status;
    private final int runtime;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate premiered;

    private final String officialSite;
    private final Schedule schedule;
    private Externals externals;
    private final Image image;
    private final String summary;


    private Show(@JsonProperty(value = "id", required = true) int id,
                @JsonProperty(value = "url", required = true) String url,
                @JsonProperty(value = "name", required = true) String name,
                @JsonProperty(value = "type", required = true) String type,
                @JsonProperty(value = "language", required = true) String language,
                @JsonProperty(value = "genres", required = true) List<String> genres,
                @JsonProperty(value = "status", required = true) String status,
                @JsonProperty(value = "runtime", required = true) int runtime,
                @JsonProperty(value = "premiered", required = true) LocalDate premiered,
                @JsonProperty(value = "officialSite", required = true) String officialSite,
                @JsonProperty(value = "schedule", required = true) Schedule schedule,
                @JsonProperty(value = "externals", required = true) Externals externals,
                @JsonProperty(value = "image", required = true) Image image,
                @JsonProperty(value = "summary", required = true) String summary
                ) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id should be greater than or equal to 1");
        }

        if (runtime <= 0) {
            throw new IllegalArgumentException("Runtime should be greater than or equal to 1");
        }
        this.id = id;
        this.url = Objects.requireNonNull(url, "URL cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.language = Objects.requireNonNull(language, "Language cannot be null");
        this.genres = Objects.requireNonNull(genres, "Genres cannot be null");
        this.status = Objects.requireNonNull(status, "Status cannot be null");
        this.runtime = runtime;
        this.premiered = Objects.requireNonNull(premiered, "Premiered cannot be null");
        this.officialSite = Objects.requireNonNull(officialSite, "OfficialSite cannot be null");
        this.schedule = Objects.requireNonNull(schedule, "Schedule cannot be null");
        this.externals = Objects.requireNonNull(externals, "Externals cannot be null");
        this.image = Objects.requireNonNull(image, "Image cannot be null");
        this.summary = Objects.requireNonNull(summary, "Summary cannot be null");
    }

    public static ShowBuilder builder() {
        return new ShowBuilder();
    }

    public static class ShowBuilder {
        private int id;
        private String url;
        private String name;
        private String type;
        private String language;
        private List<String> genres;
        private String status;
        private int runtime;
        private LocalDate premiered;
        private String officialSite;
        private Schedule schedule;
        private Image image;
        private String summary;
        private Externals externals;

        public ShowBuilder id(int id) {
            if (id <= 0) {
                throw new IllegalArgumentException("Id should be greater than or equal to 1");
            }
            this.id = id;
            return this;
        }

        public ShowBuilder url(String url) {
            this.url = Objects.requireNonNull(url, "URL cannot be null");
            return this;
        }

        public ShowBuilder name(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            return this;
        }

        public ShowBuilder type(String type) {
            this.type = Objects.requireNonNull(type, "Type cannot be null");
            return this;
        }

        public ShowBuilder language(String language) {
            this.language = Objects.requireNonNull(language, "Language cannot be null");
            return this;
        }

        public ShowBuilder genres(List<String> genres) {
            this.genres = Objects.requireNonNull(genres, "Genres cannot be null");
            return this;
        }

        public ShowBuilder status(String status) {
            this.status = Objects.requireNonNull(status, "Status cannot be null");
            return this;
        }

        public ShowBuilder runtime(int runtime) {
            if (runtime <= 0) {
                throw new IllegalArgumentException("Runtime should be greater than or equal to 1");
            }
            this.runtime = runtime;
            return this;
        }

        public ShowBuilder premiered(LocalDate premiered) {
            this.premiered = Objects.requireNonNull(premiered, "Premiered cannot be null");
            return this;
        }

        public ShowBuilder officialSite(String officialSite) {
            this.officialSite = Objects.requireNonNull(officialSite, "OfficialSite cannot be null");
            return this;
        }

        public ShowBuilder schedule(Schedule schedule) {
            this.schedule = Objects.requireNonNull(schedule, "Schedule cannot be null");
            return this;
        }

        public ShowBuilder image(Image image) {
            this.image = Objects.requireNonNull(image, "Image cannot be null");
            return this;
        }

        public ShowBuilder externals(Externals externals) {
            this.externals = Objects.requireNonNull(externals, "Externals cannot be null");
            return this;
        }

        public ShowBuilder summary(String summary) {
            this.summary = Objects.requireNonNull(summary, "Summary cannot be null");
            return this;
        }

        public Show build() {
            return new Show(id, url, name, type, language, genres, status, runtime, premiered, officialSite, schedule, externals, image, summary);
        }
    }

}
