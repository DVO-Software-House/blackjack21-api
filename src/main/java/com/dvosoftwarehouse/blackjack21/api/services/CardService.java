package com.dvosoftwarehouse.blackjack21.api.services;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.querydsl.BlazeJPAQuery;
import com.dvosoftwarehouse.blackjack21.api.entities.Card;
import com.dvosoftwarehouse.blackjack21.api.entities.QCard;
import javax.persistence.EntityManager;
import java.util.Optional;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

  @PersistenceContext private EntityManager entityManager;

  private final CriteriaBuilderFactory criteriaBuilderFactory;

  public Optional<Card> findCardByCode(@Length(min = 2, max = 2) final String cardCode) {
    final QCard card = QCard.card;
    final BlazeJPAQuery<Card> query =
        new BlazeJPAQuery<>(this.entityManager, this.criteriaBuilderFactory);

    return Optional.ofNullable(
        query
            .select(card)
            .from(card)
            .where(card.code.toLowerCase().eq(cardCode.toLowerCase()))
            .fetchOne());
  }
}
