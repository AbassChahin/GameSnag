package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Entities.Game;
import com.Chahin.GameSnag.Entities.Message;
import com.Chahin.GameSnag.Service.GameSnagService;
import com.Chahin.GameSnag.Service.MessageService;
import org.springframework.http.HttpHeaders;
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
    private final MessageService messageService;

    public GameSnagController(GameSnagService gameSnagService, MessageService messageService) {
        this.gameSnagService = gameSnagService;
        this.messageService = messageService;
    }

    // Create
    @PostMapping("/addgame")
    public Game addGame(@RequestBody Game game) {
        Game newGame = gameSnagService.addGame(game);
        if (newGame == null) {
            System.out.println("[POST REQUEST] - Incorrect Game:\n" + game);
        } else {
            System.out.println("[POST REQUEST] - Adding Game:\n" + game);
        }
        return newGame;
    }

    @PostMapping("/addmessage")
    public Message addMessage(@RequestBody Message message) {
        Message newMessage = messageService.addMessage(message);
        if (newMessage == null) {
            System.out.println("[POST REQUEST] - Incorrect Message:\n" + newMessage);
        } else {
            System.out.println("[POST REQUEST] - Adding Message:\n" + newMessage);
        }
        return newMessage;
    }

    // Read
    @GetMapping
    public Game getGame(@RequestParam String title) {
        Game game = gameSnagService.getGameByTitle(title);

        if (game == null) {
            return null;
        }

        System.out.println("[GET REQUEST] - Retrieving Game:\n" + game);
        return game;
    }

    @GetMapping(value="/message", params="name")
    public Message getMessageByName(@RequestParam String name) {
        Message message = messageService.getMessageByName(name);

        if (message == null) {
            return null;
        }

        System.out.println("[GET REQUEST] - Retrieving Message:\n" + message);
        return message;
    }

    @GetMapping(value="/message", params="email")
    public Message getMessageByEmail(@RequestParam String email) {
        Message message = messageService.getMessageByEmail(email);

        if (message == null) {
            return null;
        }

        System.out.println("[GET REQUEST] - Retrieving Message:\n" + message);
        return message;
    }

    @GetMapping("/allgames")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameSnagService.getAllGames();
        System.out.println("[GET REQUEST] - Retrieving All Games:\n");
        for (Game game : games) {
            System.out.println(game + "\n\n");
        }
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/allmessages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        System.out.println("[GET REQUEST] - Retrieving All Messages:\n");
        for (Message message : messages) {
            System.out.println(message + "\n\n");
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<Game> updateGame(@RequestParam String title, @RequestBody Game updatedGame) {

        if (updatedGame == null) {
            System.out.println("[PUT REQUEST] - Game Failed Updated\n\nBad Request Body Given:\n" + updatedGame);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }

        // Get current game
        Game prevGame = gameSnagService.getGameByTitle(title);

        // Save game
        Game newGame = gameSnagService.updateGameByTitle(updatedGame, title);

        if (newGame == null) {
            System.out.println("[PUT REQUEST] - Game Failed Updated\n\nBad Request Body Given:\n" + updatedGame);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }

        System.out.println("[PUT REQUEST] - Game Successfully Updated\n\nFrom:\n" + prevGame + "\n\nTo:\n" + newGame);
        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Game> deleteGame(@RequestParam String title) {
        Game game = gameSnagService.getGameByTitle(title);

        if (game == null) {
            System.out.println("[DELETE REQUEST] - Game Failed Deletion:\nBad Request Body: " + game);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }

        gameSnagService.deleteGameByTitle(title);
        System.out.println("[DELETE REQUEST] - Game Successfully Deleted:\n" + game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping(value="/deletemessage", params="name")
    public ResponseEntity<Message> deleteMessageByName(@RequestParam String name) {
        Message message = messageService.getMessageByName(name);

        if (message == null) {
            System.out.println("[DELETE REQUEST] - Message Failed Deletion:\nBad Request Body: " + message);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }

        messageService.deleteMessageByName(name);
        System.out.println("[DELETE REQUEST] - Message Successfully Deleted:\n" + message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping(value="/deletemessage", params="email")
    public ResponseEntity<Message> deleteMessageByEmail(@RequestParam String email) {
        Message message = messageService.getMessageByEmail(email);

        if (message == null) {
            System.out.println("[DELETE REQUEST] - Message Failed Deletion:\nBad Request Body: " + message);
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }

        messageService.deleteMessageByEmail(email);
        System.out.println("[DELETE REQUEST] - Message Successfully Deleted:\n" + message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
