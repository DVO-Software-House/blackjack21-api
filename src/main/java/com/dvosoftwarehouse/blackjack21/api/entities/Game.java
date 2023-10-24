package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.GameStatusEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "game")
@Getter
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
  @GenericGenerator(
      name = "game_id_seq",
      strategy = "com.vladmihalcea.hibernate.id.BatchSequenceGenerator",
      parameters = {
          @Parameter(name = "sequence", value = "game_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  private Long id;

  @Column(name = "label", unique = true, nullable = false)
  @NonNull
  private String label;

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
