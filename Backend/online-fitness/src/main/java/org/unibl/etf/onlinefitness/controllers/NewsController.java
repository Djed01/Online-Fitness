package org.unibl.etf.onlinefitness.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.onlinefitness.models.dto.NewsDTO;
import org.unibl.etf.onlinefitness.services.NewsService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/news")
public class NewsController {
    private NewsService newsService;

    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsDTO> getNews(){
        return newsService.consumeFeed();
    }
}
