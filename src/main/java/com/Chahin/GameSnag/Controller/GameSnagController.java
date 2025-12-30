package com.Chahin.GameSnag.Controller;

import com.Chahin.GameSnag.Entities.GameSnag;
import com.Chahin.GameSnag.Service.GameSnagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/game")
public class GameSnagController {
    private final GameSnagService gameSnagService;

    @Autowired
    public GameSnagController(GameSnagService gameSnagService) {
        this.gameSnagService = gameSnagService;
    }

    @GetMapping
    public GameSnag getGameSnag(@RequestParam String name) {
        System.out.println(name);
        if (name != null) {
            return gameSnagService.getGame(name);
        } else {
            return null;
        }
    }
}
