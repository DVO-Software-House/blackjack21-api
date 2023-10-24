package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.CardSuitEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name = "card")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "smallserial")
  private Short id;

  @Column(name = "value", nullable = false, updatable = false, columnDefinition = "smallint")
  private Short value;

  @Enumerated(EnumType.STRING)
  @Column(name = "suit", length = 10, nullable = false, updatable = false)
  @NonNull
  private CardSuitEnum suit;

  @Column(name = "code", length = 2, unique = true, nullable = false, updatable = false)
  @NonNull
  private String code;
}
