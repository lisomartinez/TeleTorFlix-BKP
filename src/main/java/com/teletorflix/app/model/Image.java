package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class Image {

    private final String medium;
    private final String original;

    private Image( String medium, String original) {
        this.medium = medium;
        this.original = original;
    }

    @JsonCreator
    public static Image getInstance(@JsonProperty(value = "medium", required = true) String medium,
                                    @JsonProperty(value = "original", required = true) String original) {
        return new Image(medium, original);
    }


}
