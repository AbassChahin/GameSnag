package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Utils.EpicGamesScraper;
import com.Chahin.GameSnag.Utils.SaleManager;
import com.Chahin.GameSnag.Utils.SteamGamesScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLOutput;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {

    @Autowired
    private final EpicGamesScraper epicGamesScraper;
    @Autowired
    private final SteamGamesScraper steamGamesScraper;
    @Autowired
    private final SaleManager saleManager;

    public ScrapeController(EpicGamesScraper epicGamesScraper, SteamGamesScraper steamGamesScraper, SaleManager saleManager) {
        this.epicGamesScraper = epicGamesScraper;
        this.steamGamesScraper = steamGamesScraper;
        this.saleManager = saleManager;
    }

    @GetMapping
    public void scrape() throws IOException, InterruptedException {
        // Delete all current sales
        saleManager.deleteAllFiles();
        saleManager.deleteAllDocuments();
        System.out.println("[SALE WIPE] - Successful!");

        // Update Sale Data
        epicGamesScraper.scrapeEpicGames();
        System.out.println("[EPIC GAMES SCRAPE] - Successful!");
        steamGamesScraper.scrape();
        System.out.println("[STEAM GAMES SCRAPE] - Successful!");
    }
}
