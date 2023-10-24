package com.dvosoftwarehouse.blackjack21.api.repositories;

import com.dvosoftwarehouse.blackjack21.api.entities.Game;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface GameRepository extends CustomRepository<Game, Long> {
  Optional<Game> findFirstById(@NotNull final Long gameId);
}
