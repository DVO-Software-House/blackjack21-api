package com.dvosoftwarehouse.blackjack21.api.services;

import com.dvosoftwarehouse.blackjack21.api.entities.Card;
import com.dvosoftwarehouse.blackjack21.api.entities.Game;
import com.dvosoftwarehouse.blackjack21.api.entities.GameDeckCard;
import com.dvosoftwarehouse.blackjack21.api.repositories.CardRepository;
import com.dvosoftwarehouse.blackjack21.api.repositories.GameDeckCardRepository;
import com.dvosoftwarehouse.blackjack21.api.repositories.GameRepository;
import com.dvosoftwarehouse.blackjack21.api.utils.shuffle.Shuffler;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {
  private final GameRepository gameRepository;
  private final GameDeckCardRepository gameDeckCardRepository;
  private final CardRepository cardRepository;

  @Qualifier("modernFisherYatesShortShuffler")
  private final Shuffler<Short> shuffler;

  // No need for a property since this has been the standard since always
  // https://en.wikipedia.org/wiki/Standard_52-card_deck
  private static final int cardsPerDeck = 52;

  public boolean gameHasShuffledDeck(@NotNull final Long gameId) {
    return gameDeckCardRepository.findFirstByGameId(gameId).isPresent();
  }

  @Transactional
  public Boolean shuffleGameDeck(@NotNull final Long gameId) {
    final Optional<Game> gameOptional = gameRepository.findFirstById(gameId);
    if (gameOptional.isEmpty()) return false;

    final Game gameFound = gameOptional.get();

    // TODO(DavidGrunheidt): Eclipse Collections might get better performance
    final int numOfCards = cardsPerDeck * gameFound.getNumOfDecks();
    final List<Short> positions =
        IntStream.range(0, numOfCards)
            .boxed()
            .map(Integer::shortValue)
            .collect(Collectors.toList());

    shuffler.shuffle(positions);

    final List<Card> oneDeckCards = cardRepository.findAll();
    final List<Integer> positionsIndexes = IntStream.range(0, numOfCards).boxed().toList();

    final List<GameDeckCard> gameDeckCards =
        positionsIndexes.parallelStream()
            .map(
                index ->
                    GameDeckCard.builder()
                        .game(gameFound)
                        .card(oneDeckCards.get(index % cardsPerDeck))
                        .position(positions.get(index))
                        .build())
            .toList();

    gameDeckCardRepository.saveAll(gameDeckCards);
    return true;
  }
}
