package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Service.GameSnagService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Game getGameSnag(@RequestParam String name) {
        if (name != null) {
            Game game = gameSnagService.getGameByName(name);
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
    public ResponseEntity<Game> updateGame(@RequestParam String name, @RequestBody Game updatedGame) {
        // Get current game
        Game prevGame = gameSnagService.getGameByName(name);
        Game game = gameSnagService.getGameByName(name);

        // Update game
        game.setName(updatedGame.getName());
        game.setSaleDates(updatedGame.getSaleDates());
        game.setOriginalPrice(updatedGame.getOriginalPrice());
        game.setSalePrice(updatedGame.getSalePrice());

        // Save game
        Game newGame = gameSnagService.updateGameByName(game);

        System.out.println("[PUT REQUEST] - Game Successfully Updated\n\nFrom:\n" + prevGame + "\n\nTo:\n" + newGame);

        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Game> deleteGame(@RequestParam String name) {
        Game game = gameSnagService.getGameByName(name);
        gameSnagService.deleteGameByName(name);
        System.out.println("[DELETE REQUEST] - Game Successfully Deleted:\n" + game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
