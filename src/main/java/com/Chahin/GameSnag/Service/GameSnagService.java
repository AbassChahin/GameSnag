package com.Chahin.GameSnag.Service;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Repository.GameSnagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameSnagService {

    private final GameSnagRepository gameSnagRepository;

    @Autowired
    public GameSnagService(GameSnagRepository gameSnagRepository) {
        this.gameSnagRepository =  gameSnagRepository;
    }

    // Create
    public Game addGame(Game game) {
        // Ensure not null
        if (game == null) {
            return null;
        }

        // Title check
        if (game.getTitle() == null || game.getTitle().isEmpty()) {
            return null;
        }

        // Image Path Check
        if (game.getImagePath() == null || game.getImagePath().isEmpty()) {
            return null;
        }

        // Platform check
        if (game.getPlatform() == null) {
            return null;
        }

        // ReferenceURL Check
        if (game.getReferenceURL() == null || game.getReferenceURL().isEmpty()) {
            return null;
        }

        return gameSnagRepository.save(game);
    }

    // Read
    public Game getGameByTitle(String gameName) {
        return gameSnagRepository.findByTitle(gameName);
    }

    public List<Game> getAllGames() {
        return gameSnagRepository.findAll();
    }

    public List<Game> getSalePrice(double salePrice) {
        return gameSnagRepository.findAll().stream()
                .filter(game -> game.getSalePrice() == salePrice)
                .collect(Collectors.toList());
    }

    // Update
    public Game updateGameByTitle(Game game) {
        return gameSnagRepository.save(game);
    }

    // Delete
    public Game deleteGameByTitle(String name) {
        return gameSnagRepository.deleteByTitle(name);
    }
}
