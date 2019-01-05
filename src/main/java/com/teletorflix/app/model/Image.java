package com.teletorflix.app.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode
@ToString
@Getter
public class Image {

    private final String medium;

    @NotBlank
    private final String original;

    private Image(String medium, String original) {
        this.medium = medium;
        this.original = original;

    }

    public static Image of(String medium, String original) {
         return new Image(medium, original);
    }


}
