package com.Chahin.GameSnag.Utils;

import com.Chahin.GameSnag.Service.S3Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScrapeScheduler {
    private final EpicGamesScraper epicGamesScraper;
    private final SteamGamesScraper steamGamesScraper;
    private final SaleManager saleManager;
    private final S3Service s3Service;

    public ScrapeScheduler(EpicGamesScraper epicGamesScraper, SteamGamesScraper steamGamesScraper, SaleManager saleManager, S3Service s3Service) {
        this.epicGamesScraper = epicGamesScraper;
        this.steamGamesScraper = steamGamesScraper;
        this.saleManager = saleManager;
        this.s3Service = s3Service;
    }

    @Scheduled(cron="0 30 0 * * ?", zone="America/New_York")
    public void scrapeDaily() throws IOException, InterruptedException {
        // Delete all current sales
        saleManager.deleteAllFiles();
        saleManager.deleteAllDocuments();
        s3Service.deleteAllS3Images();
        System.out.println("[SALE WIPE] - Successful!");

        // Update Sale Data
        epicGamesScraper.scrapeEpicGames();
        System.out.println("[EPIC GAMES SCRAPE] - Successful!");
        steamGamesScraper.scrape();
        System.out.println("[STEAM GAMES SCRAPE] - Successful!");
    }
}
