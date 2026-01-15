package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Utils.EpicGamesScraper;
import com.Chahin.GameSnag.Utils.SaleManager;
import com.Chahin.GameSnag.Utils.ScrapeScheduler;
import com.Chahin.GameSnag.Utils.SteamGamesScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {

    @Autowired
    private final ScrapeScheduler scrapeScheduler;

    public ScrapeController(ScrapeScheduler scrapeScheduler) {
        this.scrapeScheduler = scrapeScheduler;
    }

    @GetMapping
    public void scrape() throws IOException, InterruptedException {
        scrapeScheduler.scrapeDaily();
    }
}
