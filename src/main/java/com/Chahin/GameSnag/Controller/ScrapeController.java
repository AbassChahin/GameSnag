package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Utils.EpicGamesScraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {

    private final EpicGamesScraper epicGamesScraper;

    public ScrapeController(EpicGamesScraper epicGamesScraper) {
        this.epicGamesScraper = epicGamesScraper;
    }

    @GetMapping
    public void scrape() {
        epicGamesScraper.scrapeEpicGames();
    }
}
