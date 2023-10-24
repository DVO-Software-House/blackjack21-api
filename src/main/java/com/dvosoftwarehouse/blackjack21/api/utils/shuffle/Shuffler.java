package com.dvosoftwarehouse.blackjack21.api.utils.shuffle;

import java.util.List;

public interface Shuffler<T> {

  void shuffle(final List<T> list);
}
