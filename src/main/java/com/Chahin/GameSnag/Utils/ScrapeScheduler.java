package com.Chahin.GameSnag.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScrapeScheduler {
    private final EpicGamesScraper epicGamesScraper;
    private final SteamGamesScraper steamGamesScraper;
    private final SaleManager saleManager;

    public ScrapeScheduler(EpicGamesScraper epicGamesScraper, SteamGamesScraper steamGamesScraper, SaleManager saleManager) {
        this.epicGamesScraper = epicGamesScraper;
        this.steamGamesScraper = steamGamesScraper;
        this.saleManager = saleManager;
    }

    @Scheduled(cron="0 30 0 * * ?", zone="America/New_York")
    public void scrapeDaily() throws IOException, InterruptedException {
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
