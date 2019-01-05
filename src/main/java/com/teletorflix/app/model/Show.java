package com.teletorflix.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
public class Show {

    private final int id;
    private final String url;
    private final String name;
    private final String type;
    private final String language;
    private final List<String> genres;
    private final String status;
    private final int runtime;
    private final LocalDate premiered;
    private final String officialSite;
    private final Schedule schedule;
    private final Externals externals;
    private final Image image;
    private final String summary;

    public Show(int id, String url, String name, String type, String language, List<String> genres, String status,
                int runtime, LocalDate premiered, String officialSite, Schedule schedule, Externals externals,
                Image image, String summary) {

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
        this.schedule = schedule;
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
        private Schedule schedule;
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
            if (runtime <= 0) {
                throw new IllegalArgumentException("Runtime should be greater than or equal to 1");
            }
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

        public ShowBuilder schedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public ShowBuilder image(Image image) {
            this.image = image;
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
            return new Show(id, url, name, type, language, genres, status, runtime, premiered, officialSite,
                    schedule, externals, image, summary);
        }
    }

}
