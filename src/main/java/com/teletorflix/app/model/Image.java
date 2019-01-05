package com.teletorflix.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@EqualsAndHashCode
@ToString
@Getter
public class Image {

    private final String medium;
    private final String original;

    private Image( String medium, String original) {
        this.medium = Objects.requireNonNull(medium, "Medium Image cannot be null");
        this.original = Objects.requireNonNull(original, "Original size Image cannot be null");
    }

    @JsonCreator
    public static Image of(@JsonProperty(value = "medium", required = true) String medium,
                           @JsonProperty(value = "original", required = true) String original) {
         return new Image(medium, original);
    }


    static Image getEmptyInstance() {
        return new Image("", "");
    }
}
