package com.dvosoftwarehouse.blackjack21.api.entities;

import com.dvosoftwarehouse.blackjack21.api.enums.CardSuitEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NonNull;

@Entity(name = "card")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "smallserial")
  private short id;

  @Column(name = "value", nullable = false, updatable = false, columnDefinition = "smallint")
  private short value;

  @Enumerated(EnumType.STRING)
  @Column(name = "suit", length = 10, nullable = false, updatable = false)
  @NonNull
  private CardSuitEnum suit;

  @Column(name = "code", length = 2, unique = true, nullable = false, updatable = false)
  @NonNull
  private String code;
}
