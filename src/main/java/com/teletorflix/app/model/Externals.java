package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@ToString
@EqualsAndHashCode
@Getter
public class Externals {

    private final int tvrage;

    private final int thetvdb;

    private final String imdb;

    private Externals( int tvrage, int thetvdb, String imdb) {
        this.tvrage = tvrage;
        this.thetvdb = thetvdb;
        this.imdb = imdb;
    }

    @JsonCreator
    public static Externals getInstance(@JsonProperty(value = "tvrage", required = true) int tvrage,
                                        @JsonProperty(value = "thetvdb", required = true) int thetvdb,
                                        @JsonProperty(value = "imdb", required = true) String imdb) {
        return new Externals(tvrage, thetvdb, imdb);
    }
}
