package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.PlayerActionEnum;
import io.hypersistence.utils.hibernate.id.BatchSequenceGenerator;
import jakarta.annotation.Nullable;
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
import java.time.LocalDateTime;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "player_hand_event")
public class PlayerHandEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_hand_card_id_seq")
  @GenericGenerator(
      name = "player_hand_card_id_seq",
      type = BatchSequenceGenerator.class,
      parameters = {
          @Parameter(name = "sequence", value = "player_hand_card_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  @NonNull
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_hand_id", nullable = false, updatable = false)
  @NonNull
  private PlayerHand playerHand;

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
