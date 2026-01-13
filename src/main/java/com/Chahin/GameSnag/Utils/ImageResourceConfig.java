package com.Chahin.GameSnag.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ImageResourceConfig implements WebMvcConfigurer {

    public ImageResourceConfig() {
        String imageFolderPath = "./images/games/";
        Path imageDirPath = Paths.get(imageFolderPath).toAbsolutePath();

        try {
            Files.createDirectories(imageDirPath);
        } catch (IOException e){
            throw new RuntimeException("[IMAGE RESOURCE] - Failed - Unable to create Image Directory: " + e);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/games/**")
                .addResourceLocations("file:./images/games/")
                .setCacheControl(CacheControl.noCache())
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
