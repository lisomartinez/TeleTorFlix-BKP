package com.teletorflix.app.service;

import com.teletorflix.app.model.Externals;
import com.teletorflix.app.model.Image;
import com.teletorflix.app.model.Show;
import com.teletorflix.app.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    private ShowRepository showRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show getShow(int id) {
        return Show.builder()
                .id(1)
                .name("Under the Dome")
                .url("http://www.tvmaze.com/shows/1/under-the-dome")
                .type("Scripted")
                .language("English")
                .genres(List.of("Drama", "Science-Fiction", "Thriller"))
                .status("Ended")
                .runtime(60)
                .premiered(LocalDate.of(2013, 6, 24))
                .officialSite("http://www.cbs.com/shows/under-the-dome/")
                .externals(Externals.getInstance(25988,264492, "tt1553656" ))
                .image(Image.getInstance("http://static.tvmaze.com/uploads/images/medium_portrait/0/1.jpg", "http://static.tvmaze.com/uploads/images/original_untouched/0/1.jpg"))
                .summary("<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>")
                .build();
    }
}
