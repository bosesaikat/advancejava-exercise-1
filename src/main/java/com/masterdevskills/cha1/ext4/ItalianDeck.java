package com.masterdevskills.cha1.ext4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 08 August 2020
 */
public class ItalianDeck implements Deck{

  private final List<Card> entireDeck;
  private Deck deck;

  public ItalianDeck() {
    entireDeck = new ArrayList<>();
  }

  @Override
  public List<Card> getCards() {
    return entireDeck;
  }

  @Override
  public Deck deckFactory() {
    return new ItalianDeck();
  }

  @Override
  public int size() {
    return getCards().size();
  }

  @Override
  public void addCard(Card card) {
    getCards().add(card);
  }

  @Override
  public void addCards(List<Card> cards) {
    getCards().addAll(cards);
  }

  @Override
  public void addDeck(Deck deck) {
    this.deck = deck;
  }

  @Override
  public void shuffle() {
    Collections.shuffle(getCards());
  }

  @Override
  public void sort() {
    Collections.sort(getCards());
  }

  @Override
  public void sort(Comparator<Card> c) {
    getCards().sort(c);
  }

  @Override
  public String deckToString() {
    return defaultToString();
  }

  @Override
  public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
    if (validDeal(players, numberOfCards)) {
      throw new IllegalArgumentException(
          "Illegal Arguments. players = +" + players + " , numberOfCards = +" + numberOfCards);
    }
    return prepareDeal(players,numberOfCards);
  }
}
