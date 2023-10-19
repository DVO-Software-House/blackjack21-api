package com.dvosoftwarehouse.blackjack21.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NonNull;

@Entity(name = "bet_chip")
public class BetChip {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "smallserial")
  private short id;

  @Column(name = "value", nullable = false)
  private int value;

  @Column(name = "label", length = 8, nullable = false)
  @NonNull
  private String label;
}
