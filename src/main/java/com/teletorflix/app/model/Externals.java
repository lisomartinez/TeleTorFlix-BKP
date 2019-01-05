package com.teletorflix.app.model;

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

    public static Externals of(int tvrage, int thetvdb, String imdb) {
        return new Externals(tvrage, thetvdb, imdb);
    }


}
