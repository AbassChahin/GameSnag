package com.Chahin.GameSnag.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageDownloader {
    public static String downloadImage(String imageUrl, String fileName) {

        try (InputStream in = new URL(imageUrl).openStream()) {
            Path folder = Paths.get("./images/games");
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            Path filePath = folder.resolve(fileName);
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Downloaded to: " + filePath.toAbsolutePath());

            return filePath.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
