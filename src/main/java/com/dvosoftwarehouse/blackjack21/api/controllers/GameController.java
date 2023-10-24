package com.dvosoftwarehouse.blackjack21.api.controllers;

import com.dvosoftwarehouse.blackjack21.api.services.GameService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class GameController {
  public final GameService gameService;

  // TODO(DavidGrunheidt): Change to PostMapping
  @GetMapping("/game/{game-id}/shuffle")
  public ResponseEntity<List<Integer>> shuffleGameDeck(@PathVariable("game-id") final Long gameId) {
    if (this.gameService.gameHasShuffledDeck(gameId)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    final Boolean hasShuffled = this.gameService.shuffleGameDeck(gameId);
    if (!hasShuffled) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    return ResponseEntity.ok().build();
  }
}
