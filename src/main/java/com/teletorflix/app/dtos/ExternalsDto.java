package com.teletorflix.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalsDto {

    @Min(1)
    private int tvrage;

    @Min(1)
    private int thetvdb;

    @NotBlank
    private String imdb;

    public static ExternalsDto of(int tvrage, int thetvdb, String imdb) {
        return new ExternalsDto(tvrage, thetvdb, imdb);
    }

}
