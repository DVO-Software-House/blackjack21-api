package com.dvosoftwarehouse.blackjack21.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "game_deck_card")
public class GameDeckCard {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_deck_card_id_seq")
  @GenericGenerator(
      name = "game_deck_card_id_seq",
      strategy = "com.vladmihalcea.hibernate.id.BatchSequenceGenerator",
      parameters = {
          @Parameter(name = "sequence", value = "game_deck_card_id_seq"),
          @Parameter(name = "fetch_size", value = "5")
      })
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @NonNull
  private Game game;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false, updatable = false)
  @NonNull
  private Card card;

  @Column(name = "position", columnDefinition = "smallint", nullable = false, updatable = false)
  private Short position;
}


