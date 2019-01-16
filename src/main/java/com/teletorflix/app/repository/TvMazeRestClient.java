package com.teletorflix.app.repository;

import com.teletorflix.app.dtos.TvMazeShowDto;
import com.teletorflix.app.service.TvMazeRestRespondeErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class TvMazeRestClient implements TvMazeClient {

    private RestTemplate restTemplate;
    private TvMazeURLConstructor tvMazeURLConstructor;

    @Autowired
    public TvMazeRestClient(RestTemplateBuilder restTemplateBuilder,
                            TvMazeURLConstructor config,
                            TvMazeRestRespondeErrorHandler errorHandler) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(errorHandler)
                .build();
        this.tvMazeURLConstructor = config;
    }

    @Override
    public TvMazeShowDto getById(int id) {
        String url = tvMazeURLConstructor.getShowByIdURL(id);
        return restTemplate.getForObject(url, TvMazeShowDto.class);
    }

    @Override
    public List<TvMazeShowDto> findPage(int page) {
        String url = tvMazeURLConstructor.getShowsPageURL(page);
        ResponseEntity<List<TvMazeShowDto>> response = restTemplate.exchange(
                url,
                GET,
                null,
                new ParameterizedTypeReference<List<TvMazeShowDto>>(){});
        return response.getBody();
    }
}
