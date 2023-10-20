package com.dvosoftwarehouse.blackjack21.api.controllers;

import com.dvosoftwarehouse.blackjack21.api.entities.Card;
import com.dvosoftwarehouse.blackjack21.api.services.CardService;
import java.util.Optional;
import javax.validation.constraints.Size;
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
public class CardController {

  public final CardService cardService;

  @GetMapping("/card/code/{card-code}")
  public ResponseEntity<Card> getCardByCode(
      @PathVariable("card-code") @Size final String cardCode) {
    final Optional<Card> cardOptional = this.cardService.findCardByCode(cardCode);
    if (cardOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    return ResponseEntity.ok().body(cardOptional.get());
  }
}
