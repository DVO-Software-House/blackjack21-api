package com.dvosoftwarehouse.blackjack21.api.repositories;

import com.dvosoftwarehouse.blackjack21.api.entities.GameDeckCard;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface GameDeckCardRepository extends CustomRepository<GameDeckCard, Long> {
  Optional<GameDeckCard> findFirstByGameId(@NotNull final Long gameId);
}
