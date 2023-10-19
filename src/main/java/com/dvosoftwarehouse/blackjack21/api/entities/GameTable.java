package com.dvosoftwarehouse.blackjack21.api.entities;

import io.hypersistence.utils.hibernate.id.BatchSequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity(name = "game_table")
public class GameTable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_table_id_seq")
  @GenericGenerator(
      name = "game_table_id_seq",
      type = BatchSequenceGenerator.class,
      parameters = {
          @Parameter(name = "sequence", value = "game_table_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  @NonNull
  private Long id;

  @Column(name = "display_name", length = 20, nullable = false)
  @NonNull
  private String display_name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by_player_id", nullable = false, updatable = false)
  @NonNull
  private Player createdByPlayer;

  @Column(name = "created_at", nullable = false, updatable = false)
  @NonNull
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}