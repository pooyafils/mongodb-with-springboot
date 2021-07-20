package com.example.mongo.controller;

import com.example.mongo.model.Player;
import com.example.mongo.model.Team;
import com.example.mongo.repository.PlayerRepository;
import com.example.mongo.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SoccerController {
    TeamRepository teamRepository;

    PlayerRepository playerRepository;

    public SoccerController(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team createTeamDto) {
        Team teamCreated = teamRepository.save(createTeamDto);

        return new ResponseEntity<>(teamCreated, HttpStatus.CREATED);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player createPlayerDto) {
        Player playerCreated = playerRepository.save(createPlayerDto);

        return new ResponseEntity<>(playerCreated, HttpStatus.CREATED);
    }
    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody Team createTeamDto) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        Team teamToUpdate = optionalTeam.get()
                .setAddress(createTeamDto.getAddress())
                .setName(createTeamDto.getName())
                .setAcronym(createTeamDto.getAcronym());

        Team teamUpdated = teamRepository.save(teamToUpdate);

        return new ResponseEntity<>(teamUpdated, HttpStatus.OK);
    }
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id, @RequestBody Team createTeamDto) {
        teamRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @PostMapping("/players/bulk")
//    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<Player> createPlayerDtoList) {
//        List<Player> players = createPlayerDtoList
//                .stream()
//                .map(Player::toPlayer)
//                .collect(Collectors.toList());
//
//        List<Player> playersCreated = playerRepository.saveAll(players);
//
//        return new ResponseEntity<>(playersCreated, HttpStatus.CREATED);
//    }
@PostMapping("/teams/{id}/players")
public ResponseEntity<Team> addPlayersToTeam(@PathVariable String id, @RequestBody List<String> playerIds) {
    Optional<Team> optionalTeam = teamRepository.findById(id);

    if (optionalTeam.isEmpty()) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    Team teamToUpdate = optionalTeam.get();

    Set<Player> playersToAdd = playerIds.stream()
            .map(playerId -> playerRepository.findById(playerId))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());

    teamToUpdate.setPlayers(playersToAdd);

    Team teamUpdated =  teamRepository.save(teamToUpdate);

    return new ResponseEntity<>(teamUpdated, HttpStatus.OK);
}
}