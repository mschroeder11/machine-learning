package cs3500.pyramidsolitaire.model.hw04;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the protected methods in RelaxedPyramidSolitaire.
 */
public class TestRelaxedPyramidSolitaire {

  /**
   * objects to test methods on.
   */
  RelaxedPyramidSolitaire unstartedGame;
  RelaxedPyramidSolitaire startedDefaultGame;
  RelaxedPyramidSolitaire startedCustomGame1;
  RelaxedPyramidSolitaire startedCustomGame2;
  RelaxedPyramidSolitaire startedCustomGameRelaxed;
  List<Card> normalDeck;
  List<Card> relaxedDeck;

  // Initialize data
  @Before
  public void setup() {
    Card[] p1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    Card[] relaxed = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.SIX), null,
        new Card(Suit.CLUB, Rank.SEVEN), null,
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    normalDeck = new ArrayList<Card>(Arrays.asList(p1));
    relaxedDeck = new ArrayList<Card>(Arrays.asList(relaxed));
    unstartedGame = new RelaxedPyramidSolitaire();
    startedDefaultGame = makeGame(false, 7, 2);
    startedCustomGame1 = new RelaxedPyramidSolitaire(normalDeck, 3, 3);
    startedCustomGameRelaxed = new RelaxedPyramidSolitaire(relaxedDeck, 3, 3);
  }
  
  // test that testRemovable throws an exception for a null card
  @Test(expected = IllegalArgumentException.class) 
  public void testTestRemovableInvalid1() {
    startedCustomGameRelaxed.testRemovable(2, 0, 2, 2);
  }
  
  // test that testRemovable throws an exception if the second card is null
  @Test(expected = IllegalArgumentException.class) 
  public void testTestRemovableInvalid2() {
    startedCustomGameRelaxed.testRemovable(2, 1, 2, 2);
  }
  
  // test that testRemovable throws an exception for a non removable card
  @Test(expected = IllegalArgumentException.class)
  public void testTestRemovableInvalid3() {
    startedCustomGame1.testRemovable(1, 1, 2, 1);
  }
  
  // test isCoveredRelaxed
  @Test
  public void testIsCoveredRelaxed() {
    assertTrue(startedDefaultGame.isCoveredRelaxed(6, 2, 5, 2));
    assertFalse(startedCustomGameRelaxed.isCoveredRelaxed(1, 1, 2, 1));
    assertTrue(startedCustomGame1.isCoveredRelaxed(1, 1, 2, 1));
    assertTrue(startedCustomGame1.isCoveredRelaxed(0, 0, 2, 1));
  }
  
  /**
   * Create a game with a standard deck.
   *
   * @param shouldShuffle if {@code false}, use the order as given by {@code deck}, otherwise
   *        shuffle the cards
   * @param numRows number of rows in the pyramid
   * @param numDraw number of open piles
   * @return the game
   */
  private RelaxedPyramidSolitaire makeGame(boolean shuffle, int rows, int draw) {
    RelaxedPyramidSolitaire game = new RelaxedPyramidSolitaire();
    List<Card> stdDeck = game.getDeck();
    game.startGame(stdDeck, shuffle, rows, draw);
    return game;
  }

}
