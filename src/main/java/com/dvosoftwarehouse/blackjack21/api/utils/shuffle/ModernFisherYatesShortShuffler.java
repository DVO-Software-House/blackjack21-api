package com.dvosoftwarehouse.blackjack21.api.utils.shuffle;

import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
// https://www.blogcyberini.com/2015/01/algoritmo-de-embaralhamento-em-java.html
@Component
public class ModernFisherYatesShortShuffler implements Shuffler<Short> {

  @Override
  public void shuffle(List<Short> list) {
    final Random random = new Random();

    for (int index = list.size() - 1; index > 0; index --) {
      final int randomNumber = random.nextInt(index + 1);
      swap(list, index, randomNumber);
    }
  }

  public void swap(List<Short> list, int i, int j) {
    final Short temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }
}
