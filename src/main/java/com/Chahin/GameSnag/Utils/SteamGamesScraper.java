package com.Chahin.GameSnag.Utils;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Service.GameSnagService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class SteamGamesScraper {

    @Autowired
    private final GameSnagService gameSnagService;

    public SteamGamesScraper(GameSnagService gameSnagService) {
        this.gameSnagService = gameSnagService;
    }

    public void scrape() throws IOException, InterruptedException {

        // Add all games scraped to list
        List<Game> discountedGames = new ArrayList<>();

        int page = 1;
        while (true) {
            // link to scrape
            String url = "https://store.steampowered.com/search/results/" + "?specials=1&cc=us&l=en&infinite=1&category1=998&page=" + page;

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            // headers for html call
            String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", USER_AGENT)
                    .header("Referer", "https://store.steampowered.com/")
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Request failed: " + response.statusCode());
                break;
            }

            // Steam returns JSON with embedded HTML
            String body = response.body();
            String html = body.split("\"results_html\":\"", 2)[1]
                    .split("\",\"pagination\"", 2)[0]
                    .replace("\\n", "")
                    .replace("\\\"", "\"")
                    .replace("\\/", "/");

            if (html.trim().isEmpty()) {
                break;
            }

            Document doc = Jsoup.parse(html);
            // get li games
            Elements games = doc.select(".search_result_row");

            for (Element game : games) {
                // appID
                String appId = game.attr("data-ds-appid");

                // game title
                String title = game.selectFirst(".title").text();

                // game discount percent
                String discount = game.selectFirst(".discount_pct") != null
                        ? game.selectFirst(".discount_pct").text()
                        : "0%";

                // attempt edge case where div is different
                if (discount.equals("0%")) {
                    discount = game.selectFirst(".bundle_base_discount") != null
                            ? game.selectFirst(".bundle_base_discount").text()
                            : "0%";
                }

                // get discount final price
                String price = game.selectFirst(".discount_final_price") != null
                        ? game.selectFirst(".discount_final_price").text()
                        : "N/A";

                double cleanDiscountPrice = 0.00;
                if (!price.equals("N/A")) {
                    cleanDiscountPrice = cleanDouble(price);
                }

                // get original price
                String original = game.selectFirst(".discount_original_price") != null
                        ? game.selectFirst(".discount_original_price").text()
                        : "0.00";

                double cleanOriginalPrice = 0.00;
                if (!original.equals("0.00")) {
                    cleanOriginalPrice = cleanDouble(original);
                }

                // edge case if no original price, calculate original price by given discount price and percentage
                if (original.equals("0%")) {
                    double discount_pct = Double.parseDouble(discount.replace("%", "")) * -1;
                    double discount_price = Double.parseDouble(price.replace("$", ""));
                    double per_remaining = (100.00 - discount_pct) / 100.00;
                    double initial = discount_price / per_remaining;

                    original = String.format("$%.2f", initial * (discount_pct / 100.0));
                }


                // Get image src url
                Element imageEl = game.selectFirst(".search_capsule img");
                String src = imageEl.attr("src");

                // Remove anything after file extension and save file extension
                int index = src.lastIndexOf(".");
                String cleanImageSrc = "";
                String fileExtension = "";
                if (index != -1) {
                    int endIndex = src.indexOf("?", index);
                    fileExtension = (endIndex != -1) ? src.substring(index, endIndex) : "N/A";
                    cleanImageSrc = (endIndex != -1) ? src.substring(0, endIndex) : "N/A";
                }

                // full file name & remove special characters from title
                String cleanTitle = title.replaceAll("[^a-zA-Z0-9._-]", "_");

                String fileName = cleanTitle + fileExtension;

                // download image locally and get specific path
                String localPath = ImageDownloader.downloadImage(cleanImageSrc, fileName);
                String cleanLocalPath = "";

                if (localPath != null) {
                    index = localPath.indexOf("images");
                    if (index != -1) {
                        cleanLocalPath = localPath.substring(index);
                    }
                }

                // Turn backslashes forward & remove special characters
                cleanLocalPath = cleanLocalPath.replace("\\", "/");

                // test
                System.out.printf(
                        "AppID: %s | %s | %s | %s%n | %s%n",
                        appId, title, discount, price, original
                );

                System.out.println("Fetched page " + page);
                page++;

                Game newGame = new Game(
                        title,
                        cleanOriginalPrice,
                        cleanDiscountPrice,
                        "Random",
                        cleanLocalPath
                );

                Game returnedGame = gameSnagService.addGame(newGame);
                System.out.println(returnedGame);

                // avoid site blocking IP
                Thread.sleep(1000); // VERY important
            }
        }

        for (Game game : discountedGames) {
            System.out.println(game);
        }
    }

    public double cleanDouble(String input) {
        input = input.replaceAll("[^\\d.]", "");
        return Double.parseDouble(input);
    }
}
