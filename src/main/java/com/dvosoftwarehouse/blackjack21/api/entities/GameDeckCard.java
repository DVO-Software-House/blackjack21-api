package com.dvosoftwarehouse.blackjack21.api.entities;

import io.hypersistence.utils.hibernate.id.BatchSequenceGenerator;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "game_deck_card")
public class GameDeckCard {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @NonNull
  private Game game;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false, updatable = false)
  @NonNull
  private Card card;

  @Column(name = "position", columnDefinition = "smallint", nullable = false, updatable = false)
  @NonNull
  private short position;
}
