package com.Chahin.GameSnag.Utils;

import com.microsoft.playwright.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpicGamesScraper {

    public static void scrapeEpicGames() {
        try (Playwright playwright = Playwright.create()) {

            // Set a headless chromium browser
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

            // Game Lists
            List<Map<String, String>> discountedGames = new ArrayList<>();

            for (ElementHandle item : gameItems) {

                // Get Game Title
                ElementHandle titleEl = item.querySelector("div.css-rgqwpc");
                String title = (titleEl != null) ? titleEl.innerText().trim() : "N/A";

                // Get Game Discount
                ElementHandle discountEl = item.querySelector("span.eds_1xxntt819");
                String discount = (discountEl != null) ? discountEl.innerText().trim() : "0%";

                // Get Game Original Price
                ElementHandle originalPriceEl = item.querySelector("span.css-4jky3p");
                String originalPrice = (originalPriceEl != null) ? originalPriceEl.innerText().trim() : "Free";

                // Get Game Discounted Price
                ElementHandle discountPriceEl = item.querySelector("span.eds_1ypbntdc");
                String discountPrice = (discountPriceEl != null) ? discountPriceEl.innerText().trim() : originalPrice;

                Map<String, String> game = new HashMap<>();
                game.put("title", title);
                game.put("discount", discount);
                game.put("original_price", originalPrice);
                game.put("discount_price", discountPrice);

                discountedGames.add(game);
            }

            for (Map<String, String> game : discountedGames) {
                System.out.println(game);
            }
        }
    }
}
