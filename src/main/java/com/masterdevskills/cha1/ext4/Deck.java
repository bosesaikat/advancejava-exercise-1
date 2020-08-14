package com.masterdevskills.cha1.ext4;

import com.masterdevskills.cha1.ext4.Card.Rank;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public interface Deck {

  List<Card> getCards();

  Deck deckFactory();

  int size();

  void addCard(Card card);

  void addCards(List<Card> cards);

  void addDeck(Deck deck);

  void shuffle();

  void sort();

  void sort(Comparator<Card> c);

  String deckToString();

  Map<Integer, Deck> deal(int players, int numberOfCards)
      throws IllegalArgumentException;

  default boolean validDeal(int players, int cards) {
    return players <= 0 || cards <= 0 || cards % players != 0;
  }

  default Map<Integer, Deck> prepareDeal(final int players, final int numberOfCards) {
    var chunkSize = numberOfCards / players;
    Map<Integer, Deck> dealMap = new HashMap<>(players);
    for (int player = 0; player < players; player++) {
      dealMap.put(player, getDeck(player,chunkSize));
    }
    return dealMap;
  }

  private Deck getDeck(int index,int chunkSize){
    var start = index * chunkSize;
    var end = Math.min(start + chunkSize, size());
    var playerCards = getCards().subList(start, end);
    var deck = deckFactory();
    deck.addCards(playerCards);
    return deck;
  }

  default void sortBySuit() {
    sort(Comparator.comparing(Card::getSuit));
  }

  default void sortByRank() {
    sort(Comparator.comparing(Card::getRank));
  }

  default void sortByKing() {
    sort(Comparator.comparing(card -> {
      var diff = Rank.KING.value() - card.getRank().value();
      return diff < 0 ? Rank.KING.value() + diff : diff;
    }));
  }

  default String defaultToString(){
    StringJoiner stringJoiner = new StringJoiner(",");
    getCards().forEach(card -> stringJoiner.add(card.toString()));
    return stringJoiner.toString();
  }
}
