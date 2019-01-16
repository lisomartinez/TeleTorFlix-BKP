package com.teletorflix.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "genre", schema = "PUBLIC")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Genre(String genre) {
        this.name = genre;
    }

    public static Genre of(String genre) {
        return new Genre(genre);
    }
}
