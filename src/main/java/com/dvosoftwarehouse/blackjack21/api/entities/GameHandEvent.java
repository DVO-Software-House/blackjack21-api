package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.PlayerActionEnum;
import javax.annotation.Nullable;
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
import java.time.LocalDateTime;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "game_hand_event")
public class GameHandEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_hand_event_id_seq")
  @GenericGenerator(
      name = "game_hand_event_id_seq",
      strategy = "com.vladmihalcea.hibernate.id.BatchSequenceGenerator",
      parameters = {
          @Parameter(name = "sequence", value = "game_hand_event_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_hand_id", nullable = false, updatable = false)
  @NonNull
  private GameHand gameHand;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", updatable = false)
  @Nullable
  private Card card;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bet_chip_id", updatable = false)
  @Nullable
  private BetChip betChip;

  @Enumerated(EnumType.STRING)
  @Column(name = "action", length = 15, updatable = false)
  @Nullable
  private PlayerActionEnum action;

  @Column(name = "created_at", nullable = false, updatable = false)
  @NonNull
  private LocalDateTime createdAt;
}
