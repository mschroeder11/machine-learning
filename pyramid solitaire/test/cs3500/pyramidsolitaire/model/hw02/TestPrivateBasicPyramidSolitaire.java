package cs3500.pyramidsolitaire.model.hw02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the private methods of {@link BasicPyramidSolitaire}s.
 */
public class TestPrivateBasicPyramidSolitaire {

  /**
   * objects to test methods on.
   */
  BasicPyramidSolitaire model1;
  BasicPyramidSolitaire model2;
  BasicPyramidSolitaire model3;
  BasicPyramidSolitaire gameLose;
  BasicPyramidSolitaire gameNotLost;
  BasicPyramidSolitaire gameWithStock;
  BasicPyramidSolitaire gameWithMove;
  BasicPyramidSolitaire gameNotLost2;
  List<Card> notLost2;
  List<Card> deckLose;
  List<Card> withStock;
  List<Card> notLost;
  List<Card> sumTo13;
  List<Card> normalDeck;
  List<Card> deckWithDuplicates1;
  List<Card> deckWithDuplicates2;
  List<Card> paddedDeck1;
  ArrayList<ArrayList<Card>> pyramid1;
  Iterator<Card> itP1;

  // Set up the test environment.
  @Before
  public void setup() {
    model1 = new BasicPyramidSolitaire();
    model2 = new BasicPyramidSolitaire();
    model2.startGame(model2.getDeck(), false, 7, 2);
    model3 = new BasicPyramidSolitaire();
    model3.startGame(model2.getDeck(), true, 7, 2);
    normalDeck = model1.getDeck();
    Card[] cardsLose = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)}; 
    Card[] cardsNotLost = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK), 
        new Card(Suit.DIAMOND, Rank.TWO), new Card(Suit.DIAMOND, Rank.SIX)}; 
    Card[] stock = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK), 
        new Card(Suit.DIAMOND, Rank.TWO), new Card(Suit.DIAMOND, Rank.SIX), 
        new Card(Suit.DIAMOND, Rank.SEVEN),
        new Card(Suit.CLUB, Rank.ACE), new Card(Suit.HEART, Rank.EIGHT), 
        new Card(Suit.HEART, Rank.NINE),
        new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.KING)};
    Card[] goodSum = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.FIVE), new Card(Suit.CLUB, Rank.JACK)};
    Card[] cardsNotLost2 = {new Card(Suit.CLUB, Rank.JACK), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        null, null}; 
    Card[] cardsPadded = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK),
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null}; 
    paddedDeck1 = new ArrayList<Card>(Arrays.asList(cardsPadded));
    notLost2 = new ArrayList<Card>(Arrays.asList(cardsNotLost2));
    notLost = new ArrayList<Card>(Arrays.asList(cardsNotLost));
    deckLose = new ArrayList<Card>(Arrays.asList(cardsLose));
    withStock = new ArrayList<Card>(Arrays.asList(stock));
    sumTo13 = new ArrayList<Card>(Arrays.asList(goodSum));
    gameNotLost2 = new BasicPyramidSolitaire(notLost2, 3, 3);
    deckWithDuplicates1 = model1.getDeck();
    deckWithDuplicates1.set(12, deckWithDuplicates1.get(13));
    deckWithDuplicates2 = model1.getDeck();
    deckWithDuplicates2.set(0, deckWithDuplicates2.get(51));
    gameLose = new BasicPyramidSolitaire(deckLose, 3, 3);
    gameNotLost = new BasicPyramidSolitaire(notLost, 3, 3);
    gameWithStock = new BasicPyramidSolitaire(withStock, 3, 3);
    gameWithMove = new BasicPyramidSolitaire(sumTo13, 3, 3);
    itP1 = withStock.iterator();
    Card[] stock1 = {new Card(Suit.CLUB, Rank.KING)};
    Card[] stock2 = {new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO)};
    Card[] stock3 = {new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)};
    ArrayList<Card> r1 = new ArrayList<Card>(Arrays.asList(stock1));
    ArrayList<Card> r2 = new ArrayList<Card>(Arrays.asList(stock2));
    ArrayList<Card> r3 = new ArrayList<Card>(Arrays.asList(stock3));
    pyramid1 = new ArrayList<ArrayList<Card>>();
    pyramid1.add(r1);
    pyramid1.add(r2);
    pyramid1.add(r3);
  }

  // Tests the testRemovable method for cards that should be removeable.
  @Test(expected = Test.None.class /* no exception expected */)
  public void testRemovableValid() {
    model2.testRemovable(6, 0);
    model2.testRemovable(6, 1);
  }

  // Tests the testRemovable method for cards that are not removable.
  @Test(expected = IllegalArgumentException.class)
  public void testRemovableInvalid1() {
    model2.testRemovable(0, 0);
  }

  // Tests the testRemovable method for cards that are not removable.
  @Test(expected = IllegalArgumentException.class)
  public void testRemovableInvalid2() {
    model2.testRemovable(5, 1);
  }

  // Tests the validateGameStarted method throws exception when game not started.
  @Test(expected = IllegalStateException.class)
  public void testGameStartedFalse() {
    model1.testGameStarted();
  }

  // Tests the validateGameStarted method doesn't throws exception when game is started.
  @Test(expected = Test.None.class /* no exception expected */)
  public void testGameStartedTrue() {
    model2.testGameStarted();
  }

  // Tests the isCovered method
  @Test
  public void testIsCovered() {
    assertFalse(model2.isCovered(6, 0));
    assertTrue(model2.isCovered(3, 3));
  }
  
  // Test the isLost method
  @Test
  public void testIsLost() {
    assertFalse(model2.isLost());
    assertTrue(gameLose.isLost());
    assertFalse(gameNotLost.isLost());
    assertFalse(gameWithStock.isLost());
    assertFalse(gameWithMove.isLost());
    assertFalse(gameNotLost2.isLost());
  }
  
  // Test the deckHasDuplicates method
  @Test
  public void testDeckHasDuplicates() {
    assertFalse(model1.deckHasDuplicates(normalDeck));
    assertTrue(model1.deckHasDuplicates(deckWithDuplicates1));
    assertTrue(model1.deckHasDuplicates(deckWithDuplicates2));  
  }
  
  // test getValidDeckSize
  @Test
  public void testGetValidDeckSize() {
    assertEquals(model1.getValidDeckSize(), 52);
  } 
  
  // test calculateNumCards
  @Test
  public void testCalculateNumCards() {
    assertEquals(model1.calculateNumCards(3), 6);
    assertEquals(model1.calculateNumCards(9), 45);
    assertEquals(model1.calculateNumCards(1), 1);
  }
  
  // test buildPyramid
  @Test
  public void testBuildPyramid() {
    assertEquals(gameWithStock.buildPyramid(3, itP1), this.pyramid1);
  }
  
  // test computeInitialScore
  @Test
  public void testComputeInitialScore() {
    assertEquals(gameWithStock.computeInitialScore(pyramid1), 51);
  }
  
  // test padDedk
  @Test
  public void testPadDeck() {
    assertEquals(model2.padDeck(deckLose), paddedDeck1);
  }
}
