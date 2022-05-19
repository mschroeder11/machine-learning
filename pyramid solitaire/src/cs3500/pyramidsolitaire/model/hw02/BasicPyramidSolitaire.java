package cs3500.pyramidsolitaire.model.hw02;

import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents a basic pyramid solitaire game.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {

  private ArrayList<ArrayList<Card>> pyramid;
  private List<Card> draw;
  private LinkedList<Card> stock;
  private int numRows;
  private int numDraw;
  private boolean gameStarted = false;
  private int score;
  private static final int TARGET = 13;
  private static final int DECK_SIZE = 52;

  /**
   * Constructs a BasicPyramidSolitaire model in the unstarted state.
   */
  public BasicPyramidSolitaire() {
    this.numRows = -1;
    this.numDraw = -1;
    // default constructor that is ready for the user to call startGame
  }

  // constructor that presets the relevant fields. To be used for testing only
  /**
   * Constructs a BasicPyramidSoliare model in a started state. This is to be used only for testing.
   *
   * @param deck The cards in the pyramid, as a list.
   * @param numRows Number of rows in the pyramid.
   * @param numDraw Number of cards in the draw.
   */
  public BasicPyramidSolitaire(List<Card> deck, int numRows, int numDraw) {
    deck = padDeck(deck);

    startGame(deck, false, numRows, numDraw);
  }

  @Override
  public List<Card> getDeck() {
    List<Card> myDeck = new ArrayList<Card>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card current = new Card(suit, rank);
        myDeck.add(current);
      }
    }
    return myDeck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    this.numRows = -1;
    this.numDraw = -1;
    gameStarted = false;
    this.score = 0;
    // Test deck validity (whatever that means)
    if (deck == null || deck.size() != getValidDeckSize()) {
      throw new IllegalArgumentException("Deck size not valid");
    }
    if (deckHasDuplicates(deck)) {
      throw new IllegalArgumentException("Duplicate cards not valid");
    }

    if (calculateNumCards(numRows) > deck.size() || numRows < 1) {
      throw new IllegalArgumentException("Number of rows not valid.");
    }

    int remainder = deck.size() - calculateNumCards(numRows);
    if (numDraw > remainder || numDraw < 0) {
      throw new IllegalArgumentException("Invalid number for draw");
    }
    deck = new ArrayList<>(deck);
    if (shouldShuffle) {
      Collections.shuffle(deck);
    }
    // build the pyramid
    Iterator<Card> it = deck.iterator();
    pyramid = buildPyramid(numRows, it);

    // build the draw pile
    draw = new ArrayList<Card>();
    for (int i = 0; i < numDraw; i++) {
      Card c = it.next();
      draw.add(c);
    }

    // add the remaining cards in the deck to the stock pile
    stock = new LinkedList<Card>();
    while (it.hasNext()) {
      Card c = it.next();
      if (c != null) {
        stock.add(c);
      }
    }

    this.score = computeInitialScore(pyramid);
    this.numRows = numRows;
    this.numDraw = numDraw;
    this.gameStarted = true;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    testGameStarted();
    testRemovable(row1, card1, row2, card2);

    if (getCardAt(row1, card1).sum(getCardAt(row2, card2)) != TARGET) {
      throw new IllegalArgumentException("Cards do not sum up to " + TARGET);
    }
    pyramid.get(row1).set(card1, null);
    pyramid.get(row2).set(card2, null);
    score = score - TARGET;
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    testGameStarted();
    testRemovable(row, card);

    if (getCardAt(row, card).getRank() != TARGET) {
      throw new IllegalArgumentException("The value of this card is not " + TARGET);
    }
    pyramid.get(row).set(card, null);
    score = score - TARGET;
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    testGameStarted();
    testRemovable(row, card);

    if (drawIndex < 0) {
      throw new IllegalArgumentException(
          "The index is either out of bounds, or does not contain a card");
    }
    if (drawIndex > draw.size() - 1) {
      String msg = "Index " + drawIndex + " is greater than size " + draw.size();
      throw new IllegalArgumentException(msg);
    }
    if (draw.get(drawIndex) == null) {
      String msg = "Index " + drawIndex + " in draw is null";
      throw new IllegalArgumentException(msg);
    }
    if (draw.get(drawIndex).sum(getCardAt(row, card)) != TARGET) {
      throw new IllegalArgumentException("The cards do not sum up to " + TARGET);
    }
    discardDraw(drawIndex);
    int value = pyramid.get(row).get(card).getRank();
    pyramid.get(row).set(card, null);
    score = score - value;
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    testGameStarted();

    if (drawIndex > draw.size() - 1 || drawIndex < 0 || draw.get(drawIndex) == null) {
      throw new IllegalArgumentException(
          "The index is either out of bounds, or does not contain a card");
    }

    if (stock.size() > 0) {
      // if the stock still has cards, replace the discarded card with the next one from the stock
      draw.set(drawIndex, stock.poll());
    } else {
      // if there are no more cards to replace the discarded card, remove it.
      draw.set(drawIndex, null);
    }
  }

  @Override
  public int getNumRows() {
    return numRows;
  }

  @Override
  public int getNumDraw() {
    return numDraw;
  }

  @Override
  public int getRowWidth(int row) {
    testGameStarted();
    if (row < 0 || row > numRows - 1) {
      throw new IllegalArgumentException("Row number invalid");
    }
    return pyramid.get(row).size();
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    boolean isEmpty = true;
    testGameStarted();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < getRowWidth(i); j++) {
        if (getCardAt(i, j) != null) {
          isEmpty = false;
        }
      }
    }
    return isEmpty || isLost();
  }

  @Override
  public int getScore() throws IllegalStateException {
    testGameStarted();
    return score;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    testGameStarted();
    if (row > numRows - 1 || row < 0) {
      throw new IllegalArgumentException("Invalid row");
    }
    if (card >= pyramid.get(row).size() || card < 0) {
      throw new IllegalArgumentException("Invalid card");
    }

    return pyramid.get(row).get(card);
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    testGameStarted();
    return new ArrayList<Card>(draw);
  }

  /**
   * Checks to see if the game is lost.
   * 
   * @return whether or not the game has been lost.
   */
  boolean isLost() {
    ArrayList<Card> remainder = new ArrayList<Card>();
    for (Card c : draw) {
      if (c != null) {
        // there are still cards in the draw
        return false;
      }
    }
    if (stock.size() == 0 || numDraw == 0) {
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < getRowWidth(i); j++) {
          if (getCardAt(i, j) != null) {
            if (isCardPlayable(i,j)) {
              if (getCardAt(i,j).getRank() == TARGET) {
                // Card is playable and a King. Game is not lost
                return false;
              } else {
                // Card is playable, add to list to check for possible move
                remainder.add(getCardAt(i, j));
              }
            }
          }
        }
      }
      for (int i = 0; i < remainder.size() - 1; i++) {
        Card current = remainder.get(i);
        for (int j = i + 1; j < remainder.size(); j++) {
          Card next = remainder.get(j);
          if (current.sum(next) == TARGET) {
            return false;
          }
        }
      }
    } else {
      return false;
    }
    return true;
  }

  /**
   * Throw an exception if the game hasn't started.
   */
  protected void testGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException(
          "Cannot currently perform this action. Game has not been started.");
    }
  }

  /**
   * Determine if a card is removeable.
   * 
   * @param row the row where the card is located.
   * @param card the position where the card is in its row.
   * @throws IllegalArgumentException if the card is not removable
   */
  protected void testRemovable(int row, int card) {
    if (getCardAt(row, card) == null) {
      throw new IllegalArgumentException("Card does not exist");
    }
    if (isCovered(row, card)) {
      throw new IllegalArgumentException("Card is covered");
    }
  }

  /**
   * Determine if a card is removeable.
   * @param row1 row the row where the first card is located.
   * @param card1 card the position where the card is in its row.
   * @param row2 row the row where the second card is located.
   * @param card2 card the position where the card is in its row.
   * @throws IllegalArgumentException if the card is not removable
   */
  protected void testRemovable(int row1, int card1, int row2, int card2) {
    if (row1 == row2 && card1 == card2) {
      throw new IllegalArgumentException("The cards must be different");
    }
    testRemovable(row1, card1);
    testRemovable(row2, card2);
  }

  /**
   * Tests if the specified card is covered.
   *
   * @param row row of the desired card
   * @param card column of the desired card
   * @return true if the specified card is covered
   */
  protected boolean isCovered(int row, int card) {
    return (row < numRows - 1)
        && (getCardAt(row + 1, card) != null || getCardAt(row + 1, card + 1) != null);
  }

  /**
   * Tests it the give deck has duplicates.
   * 
   * @param deck a deck of cards
   * @return true if the deck has duplicate cards
   */
  protected boolean deckHasDuplicates(List<Card> deck) {
    for (int i = 0; i < deck.size() - 1; i++) {
      Card current = deck.get(i);
      // null cards are supported on input for testing
      if (current != null) {
        for (int j = i + 1; j < deck.size(); j++) {
          Card next = deck.get(j);
          if (current.equals(next)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Returns the valid deck size for this type of game.
   *
   * @return the valid deck size for this type of game.
   */
  protected int getValidDeckSize() {
    return DECK_SIZE;
  }

  /**
   * calculate the number of cards from the given number of rows.
   * @param row  the number of rows.
   * @return  the max number of cards.
   */
  protected int calculateNumCards(int row) {
    return (row * (row + 1)) / 2;
  }

  /**
   * Creates the pyramid for the game.
   *
   * @param numRows number of rows in the game.
   * @param deck    deck of cards for the game
   * @return the game pyramid
   */
  protected ArrayList<ArrayList<Card>> buildPyramid(int numRows, Iterator<Card> it) {
    ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>(numRows);
    for (int i = 0; i < numRows; i++) {
      ArrayList<Card> currentRow = new ArrayList<Card>();
      result.add(currentRow);
      for (int j = 0; j < i + 1; j++) {
        Card c = it.next();
        currentRow.add(c);
      }
    }
    return result;
  }

  /**
   * calculate the initial score for a game given its pyramid.
   * @return the score from the given pyramid.
   */
  protected int computeInitialScore(ArrayList<ArrayList<Card>> pyramid) {
    int s = 0;
    for (int i = 0; i < pyramid.size(); i++) {
      for (int j = 0; j < pyramid.get(i).size(); j++) {
        Card c = pyramid.get(i).get(j);
        if (c != null) {
          s += c.getRank();
        }
      }
    }
    return s;
  }

  /**
   * pads a deck to the standard deck size.
   * @param deck  deck to be padded.
   * @return  deck of the appropriate length.
   */
  ArrayList<Card> padDeck(List<Card> deck) {
    int remainder = 0;
    ArrayList<Card> myDeck = new ArrayList<>(deck);
    if (deck.size() < this.getValidDeckSize()) {
      remainder = this.getValidDeckSize() - deck.size();
    }

    for (int i = 0; i < remainder; i++) {
      myDeck.add(null);
    }
    return myDeck;
  }

  /**
   * Tests if the specified card is playable. This is used to determine
   * if a game is lost. If there is a playable card, the game is not lost.
   *
   * @param row   the row position of a card
   * @param card  the card position of a card
   * @return true if the specified card is playable.
   */
  protected boolean isCardPlayable(int row, int card) {
    return !isCovered(row, card);
  }
}
