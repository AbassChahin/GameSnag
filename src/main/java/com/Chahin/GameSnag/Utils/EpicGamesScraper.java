package com.Chahin.GameSnag.Utils;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Service.GameSnagService;
import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EpicGamesScraper {

    // To Save Games to Database
    @Autowired
    private final GameSnagService gameSnagService;

    public EpicGamesScraper(GameSnagService gameSnagService) {
        this.gameSnagService = gameSnagService;
    }

    public void scrapeEpicGames() {
        try (Playwright playwright = Playwright.create()) {

            // Set a headless Chromium browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Epic Games discounted games URL
            String url = "https://store.epicgames.com/en-US/browse?sortBy=releaseDate&sortDir=DESC&priceTier=tierDiscouted&category=Game&count=40&start=0";
            page.navigate(url);

            // Let JS load on store page
            page.waitForTimeout(5000);

            // Select Ul Elements
            ElementHandle gameList = page.querySelector("ul.css-wvo9v4");
            List<ElementHandle> gameItems = gameList.querySelectorAll("li.css-14qz3g4");

            // Add all games scraped to list
            List<Game> discountedGames = new ArrayList<>();

            // Loop through all games
            for (ElementHandle item : gameItems) {

                // Get Game Image URL
                ElementHandle imageDiv = item.querySelector("div.css-uwwqev");
                ElementHandle imageEL = imageDiv.querySelector("div.css-uwwqev");
                ElementHandle image = imageEL.querySelector("img");

                // Get Game Title
                ElementHandle titleEl = item.querySelector("div.css-rgqwpc");
                String title = (titleEl != null) ? titleEl.innerText().trim() : "N/A";

                // Check if title is in different div
                if (title.equals("N/A")) {
                    titleEl = item.querySelector("div.css-8uhtka");
                    title = (titleEl != null) ? titleEl.innerText().trim() : "N/A";
                }

                String cleanImageSrc = "";
                String fileExtension = "";

                // Get image src url
                if (image != null) {
                    String src = image.getAttribute("data-image");

                    // Remove anything after file extension and save file extension
                    int index = src.lastIndexOf(".");
                    cleanImageSrc = "";
                    fileExtension = "";
                    if (index != -1) {
                        int endIndex = src.indexOf("?", index);
                        fileExtension = (endIndex != -1) ? src.substring(index, endIndex) : "N/A";
                        cleanImageSrc = (endIndex != -1) ? src.substring(0, endIndex) : "N/A";
                    }
                }

                // full file name & remove special characters from title
                String cleanTitle = title.replaceAll("[^a-zA-Z0-9._-]", "_");

                String fileName = cleanTitle + fileExtension;

                // download image locally and get specific path
                String localPath = ImageDownloader.downloadImage(cleanImageSrc, fileName);
                String cleanLocalPath = "";

                if (localPath != null) {
                    int index = localPath.indexOf("images");
                    if (index != -1) {
                        cleanLocalPath = localPath.substring(index);
                    }
                }

                // Turn backslashes forward & remove special characters
                cleanLocalPath = cleanLocalPath.replace("\\", "/");


                // Get Game Discount
                ElementHandle discountEl = item.querySelector("span.eds_1xxntt819");
                String discount = (discountEl != null) ? discountEl.innerText().trim() : "0%";

                // Get Game Original Price
                ElementHandle originalPriceEl = item.querySelector("span.css-4jky3p");
                String originalPrice = (originalPriceEl != null) ? originalPriceEl.innerText().trim() : "Free";
                double cleanOriginalPrice = 0.00;
                if (!originalPrice.equals("Free")) {
                    cleanOriginalPrice = cleanDouble(originalPrice);
                }

                // Get Game Discounted Price
                ElementHandle discountPriceEl = item.querySelector("span.eds_1ypbntdc");
                String discountPrice = (discountPriceEl != null) ? discountPriceEl.innerText().trim() : "Free";
                double cleanDiscountPrice = 0.00;
                if (!discountPrice.equals("Free")) {
                    cleanDiscountPrice = cleanDouble(discountPrice);
                }

                Game game = new Game(
                      title,
                      cleanOriginalPrice,
                      cleanDiscountPrice,
                      "Random",
                      cleanLocalPath
                );

                Game returnedGame = gameSnagService.addGame(game);
                System.out.println(returnedGame);

                discountedGames.add(game);
            }

            for (Game game : discountedGames) {
                System.out.println(game);
            }
        }
    }

    public double cleanDouble(String input) {
        input = input.replaceAll("[^\\d.]", "");
        return Double.parseDouble(input);
    }
}
