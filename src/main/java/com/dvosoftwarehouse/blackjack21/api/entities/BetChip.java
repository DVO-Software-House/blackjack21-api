package com.dvosoftwarehouse.blackjack21.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NonNull;

@Entity(name = "bet_chip")
public class BetChip {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", columnDefinition = "smallserial")
  private Short id;

  @Column(name = "value", nullable = false)
  private Integer value;

  @Column(name = "label", length = 8, nullable = false)
  @NonNull
  private String label;
}
