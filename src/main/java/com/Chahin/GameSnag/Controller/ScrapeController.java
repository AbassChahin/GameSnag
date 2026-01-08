package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Utils.EpicGamesScraper;
import com.Chahin.GameSnag.Utils.SteamGamesScraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {

    private final EpicGamesScraper epicGamesScraper;
    private final SteamGamesScraper steamGamesScraper;

    public ScrapeController(EpicGamesScraper epicGamesScraper, SteamGamesScraper steamGamesScraper) {
        this.epicGamesScraper = epicGamesScraper;
        this.steamGamesScraper = steamGamesScraper;
    }

    @GetMapping
    public void scrape() throws IOException, InterruptedException {
        // testing
        epicGamesScraper.scrapeEpicGames();

        //steamGamesScraper.scrape();
    }
}
