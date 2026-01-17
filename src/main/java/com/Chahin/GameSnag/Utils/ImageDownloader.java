package com.Chahin.GameSnag.Utils;

import com.Chahin.GameSnag.Service.S3Service;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageDownloader {

    private final S3Service s3Service;

    public ImageDownloader(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public String downloadImageAndUploadToS3(String imageUrl, String fileName) {
        try (InputStream in = new URI(imageUrl).toURL().openStream()) {
            Path folder = Paths.get("./images/games");
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }

            fileName = sanitizeFileName(fileName);

            URLConnection connection = new URI(imageUrl).toURL().openConnection();
            long contentLength = connection.getContentLength();
            String contentType = connection.getContentType();

            String s3Url = s3Service.uploadImage(in, fileName, contentType, contentLength);

            System.out.println("[IMAGE DOWNLOAD] - Successful - Uploaded to: " + s3Url);
            return s3Url;
        } catch (Exception e) {
            System.out.println("[IMAGE DOWNLOAD] - Failed - File: " + fileName + "\n" + e);
        }
        return null;
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
