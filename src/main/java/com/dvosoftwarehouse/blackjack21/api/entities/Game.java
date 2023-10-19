package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.GameStatusEnum;
import io.hypersistence.utils.hibernate.id.BatchSequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "game")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
  @GenericGenerator(
      name = "game_id_seq",
      type = BatchSequenceGenerator.class,
      parameters = {
          @Parameter(name = "sequence", value = "game_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  @NonNull
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_table_id", nullable = false, updatable = false)
  @NonNull
  private GameTable gameTable;

  @Column(name = "num_of_decks", columnDefinition = "smallint", nullable = false, updatable = false)
  private short numOfDecks;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 20, nullable = false)
  @NonNull
  private GameStatusEnum status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "started_by_player_id", nullable = false, updatable = false)
  @NonNull
  private Player startedByPlayer;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
  @Transient
  private List<GameDeckCard> gameDeckCards;

  @Column(name = "created_at", nullable = false, updatable = false)
  @NonNull
  private LocalDateTime createdAt;
}
