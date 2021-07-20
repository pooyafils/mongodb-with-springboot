package com.example.mongo.controller;

import com.example.mongo.model.Player;
import com.example.mongo.repository.PlayerRepository;
import com.example.mongo.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    TeamRepository teamRepository;

    PlayerRepository playerRepository;

    public PlayerController(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player createPlayerDto) {
        Player playerCreated = playerRepository.save(createPlayerDto);

        return new ResponseEntity<>(playerCreated, HttpStatus.CREATED);
    }
}
