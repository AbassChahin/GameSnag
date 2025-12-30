package com.Chahin.GameSnag.Service;

import com.Chahin.GameSnag.Entities.GameSnag;
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

    public GameSnag getGame(String gameName) {
        return gameSnagRepository.findByName(gameName);
    }

    public List<GameSnag> getSalePrice(double salePrice) {
        return gameSnagRepository.findAll().stream()
                .filter( gameSnag -> gameSnag.getSalePrice() == salePrice)
                .collect(Collectors.toList());
    }
}
