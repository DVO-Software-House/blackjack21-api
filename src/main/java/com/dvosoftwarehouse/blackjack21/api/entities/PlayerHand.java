package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.PlayerHandStatusEnum;
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

@Entity(name = "player_hand")
public class PlayerHand {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_hand_id_seq")
  @GenericGenerator(
      name = "player_hand_id_seq",
      type = BatchSequenceGenerator.class,
      parameters = {
          @Parameter(name = "sequence", value = "player_hand_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  @NonNull
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id", nullable = false, updatable = false)
  @NonNull
  private Player player;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @NonNull
  private Game game;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 10, nullable = false)
  @NonNull
  private PlayerHandStatusEnum status;

  @Column(name = "position", columnDefinition = "smallint", nullable = false, updatable = false)
  @NonNull
  private short position;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "player_hand")
  @Transient
  private List<PlayerHandEvent> playerHandEvents;

  @Column(name = "created_at", nullable = false, updatable = false)
  @NonNull
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
