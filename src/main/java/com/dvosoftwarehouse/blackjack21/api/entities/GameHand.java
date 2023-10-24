package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.PlayerHandStatusEnum;
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
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "game_hand")
public class GameHand {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_hand_id_seq")
  @GenericGenerator(
      name = "game_hand_id_seq",
      strategy = "com.vladmihalcea.hibernate.id.BatchSequenceGenerator",
      parameters = {
          @Parameter(name = "sequence", value = "game_hand_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @NonNull
  private Game game;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player_id", updatable = false)
  private Player player;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", length = 10, nullable = false)
  @NonNull
  private PlayerHandStatusEnum status;

  @Column(name = "position", columnDefinition = "smallint", nullable = false, updatable = false)
  private short position;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "game_hand")
  @Transient
  private List<GameHandEvent> gameHandEvents;

  @Column(name = "is_dealer", nullable = false, updatable = false)
  private Boolean isDealer;

  @Column(name = "created_at", nullable = false, updatable = false)
  @NonNull
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
