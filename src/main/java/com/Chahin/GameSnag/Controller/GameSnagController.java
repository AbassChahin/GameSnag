package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Service.GameSnagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Allow CORS for testing purposes locally
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/game")
public class GameSnagController {
    private final GameSnagService gameSnagService;

    @Autowired
    public GameSnagController(GameSnagService gameSnagService) {
        this.gameSnagService = gameSnagService;
    }

    // Create
    @PostMapping("/addgame")
    public Game addGame(@RequestBody Game game) {
        System.out.println("[POST REQUEST] - Adding Game:\n" + game);
        return gameSnagService.addGame(game);
    }

    // Read
    @GetMapping
    public Game getGame(@RequestParam String title) {
        if (title != null) {
            Game game = gameSnagService.getGameByTitle(title);
            System.out.println("[GET REQUEST] - Retrieving Game:\n" + game);
            return game;
        } else {
            return null;
        }
    }

    @GetMapping("/allgames")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameSnagService.getAllGames();
        System.out.println("[GET REQUEST] - Retrieving All Games:\n");
        for (Game game : games) {
            System.out.println(game + "\n\n");
        }
        return new ResponseEntity<>(gameSnagService.getAllGames(), HttpStatus.OK);
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestParam String title, @RequestBody Game updatedGame) {
        // Get current game
        Game prevGame = gameSnagService.getGameByTitle(title);
        Game game = gameSnagService.getGameByTitle(title);

        // Update game
        game.setTitle(updatedGame.getTitle());
        game.setOriginalPrice(updatedGame.getOriginalPrice());
        game.setSalePrice(updatedGame.getSalePrice());
        game.setImagePath(updatedGame.getImagePath());

        // Save game
        Game newGame = gameSnagService.updateGameByTitle(game);

        System.out.println("[PUT REQUEST] - Game Successfully Updated\n\nFrom:\n" + prevGame + "\n\nTo:\n" + newGame);

        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Game> deleteGame(@RequestParam String title) {
        Game game = gameSnagService.getGameByTitle(title);
        gameSnagService.deleteGameByTitle(title);
        System.out.println("[DELETE REQUEST] - Game Successfully Deleted:\n" + game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
