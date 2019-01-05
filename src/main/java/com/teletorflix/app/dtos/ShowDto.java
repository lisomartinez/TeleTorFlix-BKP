package com.teletorflix.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {

    private int id;
    private String url;
    private String name;
    private String type;
    private String language;
    private List<String> genres;
    private String status;
    private int runtime;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiered;

    private String officialSite;
    private ScheduleDto schedule;
    private ExternalsDto externals;
    private ImageDto image;
    private String summary;

    public static ShowDtoBuilder builder() {
        return new ShowDtoBuilder();
    }

    @JsonSetter(value = "summary")
    private void setSummaryJson(String summaryJson) {
        Pattern pattern = Pattern.compile("(<.+?>)");
        Matcher matcher = pattern.matcher(summaryJson);

        summary = matcher.replaceAll("");
    }

    public static class ShowDtoBuilder {
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
        private ScheduleDto schedule;
        private ImageDto image;
        private String summary;
        private ExternalsDto externals;

        public ShowDtoBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ShowDtoBuilder url(String url) {
            this.url = url;
            return this;
        }

        public ShowDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ShowDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ShowDtoBuilder language(String language) {
            this.language = language;
            return this;
        }

        public ShowDtoBuilder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public ShowDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ShowDtoBuilder runtime(int runtime) {
            if (runtime <= 0) {
                throw new IllegalArgumentException("Runtime should be greater than or equal to 1");
            }
            this.runtime = runtime;
            return this;
        }

        public ShowDtoBuilder premiered(LocalDate premiered) {
            this.premiered = premiered;
            return this;
        }

        public ShowDtoBuilder officialSite(String officialSite) {
            this.officialSite = officialSite;
            return this;
        }

        public ShowDtoBuilder schedule(ScheduleDto schedule) {
            this.schedule = schedule;
            return this;
        }

        public ShowDtoBuilder image(ImageDto image) {
            this.image = image;
            return this;
        }

        public ShowDtoBuilder externals(ExternalsDto externals) {
            this.externals = externals;
            return this;
        }

        public ShowDtoBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public ShowDto build() {
            return new ShowDto(id, url, name, type, language, genres, status, runtime, premiered, officialSite,
                    schedule, externals, image, summary);
        }
    }
}
