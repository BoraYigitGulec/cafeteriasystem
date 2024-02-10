package com.example.YemekhaneB.service;

import com.example.YemekhaneB.model.Title;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    private final com.example.YemekhaneB.repository.TitleRepository TitleRepository;

    public TitleService(com.example.YemekhaneB.repository.TitleRepository TitleRepository) {
        this.TitleRepository = TitleRepository;
    }

    public List<Title> getTitles() {
        return TitleRepository.findAll();
    }

    /*public Title createTitle(Long id) {
        Title title = new Title();
        title.setTitleId(id);
        return TitleRepository.save(title);
    }*/
    public Title getTitleByTitleId(Long id) {
        Title Title =TitleRepository.findBytitleid(id);
        return Title;
    }

    public Title saveTitle(Title title) {
        Title existingTitle = getTitleByTitleId(title.gettitleid());

        if (existingTitle == null) {
            title.setTitleId(title.gettitleid());
            TitleRepository.save(title);
            return title;
        }

        return existingTitle;
    }
}
