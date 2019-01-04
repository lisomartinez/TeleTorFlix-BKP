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
import java.util.List;

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
                @JsonProperty(value = "externals", required = true) Externals externals,
                @JsonProperty(value = "image", required = true) Image image,
                @JsonProperty(value = "summary", required = true) String summary
                ) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.type = type;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.runtime = runtime;
        this.premiered = premiered;
        this.officialSite = officialSite;
        this.externals = externals;
        this.image = image;
        this.summary = summary;
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
        private Image image;
        private String summary;
        private Externals externals;

        public ShowBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ShowBuilder url(String url) {
            this.url = url;
            return this;
        }

        public ShowBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ShowBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ShowBuilder language(String language) {
            this.language = language;
            return this;
        }

        public ShowBuilder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public ShowBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ShowBuilder runtime(int runtime) {
            this.runtime = runtime;
            return this;
        }

        public ShowBuilder premiered(LocalDate premiered) {
            this.premiered = premiered;
            return this;
        }

        public ShowBuilder officialSite(String officialSite) {
            this.officialSite = officialSite;
            return this;
        }

        public ShowBuilder image(Image images) {
            this.image = images;
            return this;
        }

        public ShowBuilder externals(Externals externals) {
            this.externals = externals;
            return this;
        }

        public ShowBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Show build() {
            return new Show(id, url, name, type, language, genres, status, runtime, premiered, officialSite, externals, image, summary);
        }
    }

}
