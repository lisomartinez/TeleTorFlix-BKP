package com.teletorflix.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    @NotBlank
    private String medium;

    @NotBlank
    private String original;

    public static ImageDto of(String medium, String original) {
        return new ImageDto(medium, original);
    }

}
