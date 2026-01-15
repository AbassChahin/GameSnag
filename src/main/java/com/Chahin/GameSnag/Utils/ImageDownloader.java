package com.Chahin.GameSnag.Utils;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageDownloader {
    public static String downloadImage(String imageUrl, String fileName) {
        try (InputStream in = new URI(imageUrl).toURL().openStream()) {
            Path folder = Paths.get("./images/games");
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            fileName = fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path filePath = folder.resolve(fileName);
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("[IMAGE DOWNLOAD] - Successful - Downloaded to: " + filePath.toAbsolutePath());
            return filePath.toString();
        } catch (Exception e) {
            System.out.println("[IMAGE DOWNLOAD] - Failed - File: " + fileName + "\n" + e);
        }
        return null;
    }
}
