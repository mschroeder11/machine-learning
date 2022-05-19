package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *Represents a model for a pyramid solitaire game with 3 peaks.
 * The TriPeaks variant uses the same rules as the original game, 
 * but uses a larger board and a larger deck of cards.
 */
public class TriPeaksSolitaire extends BasicPyramidSolitaire {
  
  private static final int DECK_SIZE = 104;

  /**
   * Constructs a tri peaks pyramid solitaire model in an unstarted state.
   */
  public TriPeaksSolitaire() {
    super();
  }
  
  public TriPeaksSolitaire(List<Card> deck, int numRows, int numDraw) {
    super(deck, numRows, numDraw);
  }
  
  @Override
  public List<Card> getDeck() {
    List<Card> myDeck = new ArrayList<Card>();
    // Make a standard deck
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card current = new Card(suit, rank);
        myDeck.add(current);
      }
    }
    // Make copy of it
    List<Card> result = new ArrayList<>(myDeck);
    // Add in another standard deck.
    result.addAll(myDeck);
    return result;
  }
  
  @Override
  protected boolean deckHasDuplicates(List<Card> deck) {
    HashMap<Card, Integer> counts = new HashMap<>();
    for (int i = 0; i < deck.size(); i++) {
      Card current = deck.get(i);
      // null cards are supported on input for testing
      if (current != null) {
        if (!counts.containsKey(current)) {
          counts.put(current, 1); 
        } else {
          counts.put(current, counts.get(current) + 1);
        }
        if (counts.get(current) > 2) {
          return true;
        }
      }
    }
    return false;
  }
  
  @Override
  protected int getValidDeckSize() {
    return DECK_SIZE;
  }
  
  @Override
  protected int calculateNumCards(int row) {
    int numOverlap = (row + 1) / 2;
    int numNonOverlap = row - numOverlap;
    int cardsNonOverlap = ((numNonOverlap * (numNonOverlap + 1)) / 2) * 3;
    int cardsOverlap = ((numOverlap * (numOverlap + 1)) / 2) + (numOverlap * numNonOverlap * 3);
    return cardsOverlap + cardsNonOverlap;
  }
  
  @Override
  protected ArrayList<ArrayList<Card>> buildPyramid(int numRows, Iterator<Card> it) {
    ArrayList<ArrayList<Card>> pyramid = new ArrayList<ArrayList<Card>>(numRows);
    int numOverlap = (numRows + 1) / 2;
    int numNonOverlap = numRows - numOverlap;
    // create the non overlapping rows
    for (int i = 0; i < numNonOverlap; i++) {
      ArrayList<Card> current = new ArrayList<>();
      pyramid.add(current);
      // add cards to each of the three peaks
      for (int j = 0; j < 3; j++) {
        int currentNumCards = i + 1;
        addCardsToRow(currentNumCards, it, current);
        // add blank cards if this is not the last card
        int numBlanks = (numRows / 2) - (i + 1);
        if (j < 2) {
          for (int k = 0; k < numBlanks; k++) {
            current.add(null);
          }
        }
      }
    }
    // now add the overlapping cards
    for (int k = 0; k < numOverlap; k++) {
      ArrayList<Card> current = new ArrayList<>();
      pyramid.add(current);
      // number of cards per row.
      int numCards = (numNonOverlap * 3) + (k + 1);
      addCardsToRow(numCards, it, current);
    }
    
    return pyramid;
  }
  
  /**
   * Adds the given number of cards from the deck iterator to the given pyramid row.
   *
   * @param numCards number of cards to add
   * @param it       iterator for the deck
   * @param row      ArrayList holding cards for a row in the pyramid.
   */
  void addCardsToRow(int numCards, Iterator<Card> it, ArrayList<Card> row) {
    for (int i = 0; i < numCards; i++) {
      row.add(it.next());
    }
  }
}
