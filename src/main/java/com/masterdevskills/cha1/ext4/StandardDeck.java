package com.masterdevskills.cha1.ext4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class StandardDeck implements Deck {

  private final List<Card> entireDeck;
  private Deck deck;

  private StandardDeck() {
    entireDeck = new ArrayList<>();
  }

  public void sort() {
    Collections.sort(entireDeck);
  }

  @Override
  public void sort(final Comparator<Card> c) {
    getCards().sort(c);
  }

  @Override
  public String deckToString() {
    return defaultToString();
  }

  @Override
  public Map<Integer, Deck> deal(final int players, final int numberOfCards)
      throws IllegalArgumentException {
    if (validDeal(players, numberOfCards)) {
      throw new IllegalArgumentException(
          "Illegal Arguments. players = +" + players + " , numberOfCards = +" + numberOfCards);
    }
    return prepareDeal(players,numberOfCards);
  }

  @Override
  public List<Card> getCards() {
    return entireDeck;
  }

  @Override
  public Deck deckFactory() {
    return new StandardDeck();
  }

  @Override
  public int size() {
    return getCards().size();
  }

  @Override
  public void addCard(final Card card) {
    getCards().add(card);
  }

  @Override
  public void addCards(final List<Card> cards) {
    getCards().addAll(cards);
  }

  @Override
  public void addDeck(final Deck deck) {
    this.deck = deck;
  }

  public void shuffle() {
    Collections.shuffle(entireDeck);
  }
}
