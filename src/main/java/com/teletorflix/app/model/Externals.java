package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;


@ToString
@EqualsAndHashCode
@Getter
public class Externals {

    private final int tvrage;

    private final int thetvdb;

    private final String imdb;

    private Externals( int tvrage, int thetvdb, String imdb) {

        if(tvrage <= 0) {
            throw new IllegalArgumentException("TVRage should be greater than or equal to 1");
        }

        if(thetvdb <= 0) {
            throw new IllegalArgumentException("TheTVDb should be greater than or equal to 1");
        }

        this.tvrage = tvrage;
        this.thetvdb = thetvdb;
        this.imdb = Objects.requireNonNull(imdb, "IMDB cannot be null");
    }

    @JsonCreator
    public static Externals of(@JsonProperty(value = "tvrage", required = true) int tvrage,
                               @JsonProperty(value = "thetvdb", required = true) int thetvdb,
                               @JsonProperty(value = "imdb", required = true) String imdb) {
        return new Externals(tvrage, thetvdb, imdb);
    }


}
