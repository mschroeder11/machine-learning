import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksSolitaire;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the public methods of TriPeaksSolitaire.
 */
public class TestTriPeaksSolitaire {
  
  // test getDeck
  // test getRowWidth
  /**
   * objects to test methods on.
   */
  TriPeaksSolitaire unstartedGame;
  TriPeaksSolitaire startedDefaultGame;
  TriPeaksSolitaire startedCustomGame1;
  TriPeaksSolitaire startedCustomGame2;
  TriPeaksSolitaire startedCustomGameDraw;
  TriPeaksSolitaire gameDraw2;
  TriPeaksSolitaire gameWin;
  TriPeaksSolitaire gameLose;
  TriPeaksSolitaire unstartedGame2;
  TriPeaksSolitaire startedDefaultGame2;
  TriPeaksSolitaire defaultGameChangeDrawSize;
  TriPeaksSolitaire defaultGameChangeNumRows;
  TriPeaksSolitaire defaultShuffled;
  TriPeaksSolitaire defaultGameDiscardDraw;
  TriPeaksSolitaire defaultGameChangedStock;
  TriPeaksSolitaire gameLostNoDraw;
  TriPeaksSolitaire gameDraw2DifferentPeaks;
  TriPeaksSolitaire gameNotOver;
  List<Card> deckRow0;
  TriPeaksSolitaire gameRemoveRow0;
  List<Card> deckWithStockChanged;
  List<Card> deckWithKing;
  List<Card> deckWithCardRemoved;
  List<Card> deck1;
  List<Card> deckDraw2;
  List<Card> deckWin;
  List<Card> deckLose;
  List<Card> draw2Cards;
  List<Card> empty;
  List<Card> deckDraw2DifferentPeaks;
  List<Card> deckDefault;
  List<Card> deckWithDuplicates;
  List<Card> deckGameNotOver;
  List<Card> deckWithDuplicates2;
  List<Card> deckWithDuplicates3;
  List<Card> deckWithDuplicates4;


  // Initialize data
  @Before
  public void setup() {
    Card[] p1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR)};
    deckWithKing = new ArrayList<Card>(Arrays.asList(p1));
    startedCustomGame1 = new TriPeaksSolitaire(deckWithKing, 3, 0);
    Card[] p2 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.EIGHT), new Card(Suit.CLUB, Rank.SEVEN),
        new Card(Suit.CLUB, Rank.NINE),
        null, null, null, null, null,
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck1 = new ArrayList<Card>(Arrays.asList(p2));
    startedCustomGameDraw = new TriPeaksSolitaire(deck1, 3, 2);
    Card[] p3 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE), null, 
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE),
        new Card(Suit.CLUB, Rank.EIGHT)};
    deckDraw2 = new ArrayList<Card>(Arrays.asList(p3));
    gameDraw2 = new TriPeaksSolitaire(deckDraw2, 2, 2);
    Card[] p4 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), null, new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.THREE)};
    deckWithCardRemoved = new ArrayList<Card>(Arrays.asList(p4));
    startedCustomGame2 = new TriPeaksSolitaire(deckWithCardRemoved, 3, 2);
    
    Card[] p5 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TWO),
        new Card(Suit.CLUB, Rank.THREE), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.FIVE),
        new Card(Suit.CLUB, Rank.NINE), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN)};
    deckDraw2DifferentPeaks = new ArrayList<Card>(Arrays.asList(p5));
    gameDraw2DifferentPeaks = new TriPeaksSolitaire(deckDraw2DifferentPeaks, 7, 3);

    Card[] cardsWin = {null, null, null, null, null, null, null, null, null, null, null, null, 
        new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.TWO)};
    deckWin = new ArrayList<Card>(Arrays.asList(cardsWin));
    gameWin = new TriPeaksSolitaire(deckWin, 3, 3);
    Card[] cardsLose = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)}; 
    deckLose = new ArrayList<Card>(Arrays.asList(cardsLose));
    gameLose = new TriPeaksSolitaire(deckLose, 3, 3);
    gameLostNoDraw = new TriPeaksSolitaire(deckLose, 3, 0);
    Card[] p6 = {new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    draw2Cards = new ArrayList<Card>(Arrays.asList(p6));
    empty = new ArrayList<Card>();
    unstartedGame = new TriPeaksSolitaire();
    deckWithDuplicates = unstartedGame.getDeck();
    deckWithDuplicates.set(0,  deckWithDuplicates.get(8));
    deckDefault = unstartedGame.getDeck();
    startedDefaultGame = makeGame(false, 7, 2);
    unstartedGame2 = new TriPeaksSolitaire();
    startedDefaultGame2 = makeGame(false, 7, 2);
    defaultGameChangeDrawSize = makeGame(false, 7, 3);
    defaultGameChangeNumRows = makeGame(false, 6, 2);
    defaultShuffled = makeGame(true, 7, 2);
    defaultGameDiscardDraw = makeGame(false, 7, 2);
    defaultGameDiscardDraw.discardDraw(0);
    // Make a deck with the stock cards modified
    deckWithStockChanged = unstartedGame.getDeck();
    Card c = deckWithStockChanged.get(51);
    deckWithStockChanged.set(51, deckWithStockChanged.get(50));
    deckWithStockChanged.set(50,  c);
    defaultGameChangedStock = new TriPeaksSolitaire(deckWithStockChanged, 7, 2);
    Card[] p0 = {new Card(Suit.CLUB, Rank.SIX), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN)};
    deckRow0 = new  ArrayList<Card>(Arrays.asList(p0));
    gameRemoveRow0 = new TriPeaksSolitaire(deckRow0, 4, 0);
    Card[] cardsGameNotOver = {new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.SEVEN),
        new Card(Suit.CLUB, Rank.SIX), new Card(Suit.CLUB, Rank.THREE),
        new Card(Suit.CLUB, Rank.FOUR)}; 
    deckGameNotOver = new ArrayList<Card>(Arrays.asList(cardsGameNotOver));
    gameNotOver = new TriPeaksSolitaire(deckGameNotOver, 7, 3);
    
    // First two cards are duplicates
    deckWithDuplicates2 = unstartedGame.getDeck();
    deckWithDuplicates2.set(0,  deckWithDuplicates.get(deckWithDuplicates2.size() - 1));
    // First and last cards are duplicates
    deckWithDuplicates3 = unstartedGame.getDeck();
    deckWithDuplicates3.set(0,  deckWithDuplicates.get(1));
    // Last 2 cards are duplicates
    deckWithDuplicates4 = unstartedGame.getDeck();
    deckWithDuplicates4.set(deckWithDuplicates2.size() - 2,
        deckWithDuplicates.get(deckWithDuplicates2.size() - 1));
  }

  // test the getDeck method 
  @Test
  public void testGetDeck() {
    List<Card> deck = unstartedGame.getDeck();
    assertNotNull(deck);
    assertEquals(104, deck.size());
    boolean duplicate = false;
    // test if a card appears more than twice.
    for (int i = 0; i < deck.size() - 1; i++) {
      Card current = deck.get(i);
      if (current != null) {
        int count = 1;
        for (int j = i + 1; j < deck.size(); j++) {
          Card next = deck.get(j);
          if (current.equals(next)) {
            count++;
            if (count > 2) {
              duplicate = true;
              break;
            }
          }
        }
      }
    }
    assertFalse(duplicate);
  }

  // test the startGame method
  @Test
  public void testStartGame() {
    // test valid deck for shuffled
    // test deck for not shuffled
    unstartedGame.startGame(unstartedGame.getDeck(), false, 3, 2);
    assertEquals(2, unstartedGame.getNumDraw());
    assertEquals(3, unstartedGame.getNumRows());
    assertEquals(2, unstartedGame.getDrawCards().size());
    unstartedGame.startGame(unstartedGame.getDeck(), true, 4, 3);
    assertEquals(3, unstartedGame.getNumDraw());
    assertEquals(4, unstartedGame.getNumRows());
    assertEquals(3, unstartedGame.getDrawCards().size());
  }
  
  // Test deck deal
  @Test
  public void testDeckDeal() {
    List<Card> temp = new ArrayList<>(deckDefault);
    unstartedGame2.startGame(deckDefault, false, 7, 3);
    assertEquals(temp, deckDefault);
    unstartedGame2.startGame(deckDefault, true, 7, 3);
    assertEquals(temp, deckDefault);
  }

  // Test that cards are dealt where we expect them.
  @Test
  public void testDealLocations() {
    List<Card> deck = new ArrayList<>(startedDefaultGame.getDeck()); // copy of the deck
    assertEquals(deck.get(0), startedDefaultGame.getCardAt(0, 0)); // per interface spec
    Iterator<Card> it = deck.iterator();
    for (int row = 0; row < startedDefaultGame.getNumRows(); row++) {
      for (int card = 0; card < startedDefaultGame.getRowWidth(row); card++) {
        if (startedDefaultGame.getCardAt(row,  card) != null) {
          assertEquals(it.next(), startedDefaultGame.getCardAt(row,  card));
        } else if (row == 0) {
          assertTrue(card % 3 != 0);
        } else if (row == 1) {
          assertTrue(card % 3 == 2);
        }
      }
    }
  }

  // Test startGame with numRows too big.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame1() {
    unstartedGame.startGame(deckDefault, true, 9, 0);
  }

  // Test startGame with numDraw too big.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame2() {
    unstartedGame.startGame(deckDefault, true, 8, 17);
  }

  // Test startGame with numDraw too big.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame2a() {
    unstartedGame.startGame(deckDefault, true, 7, 41);
  }

  // Test startGame with numRows less than 0.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame3() {
    unstartedGame.startGame(deckDefault, true, -1, 0);
  }

  // Test startGame with numRows  0.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame3a() {
    unstartedGame.startGame(deckDefault, true, 0, 0);
  }

  // Test startGame with numDraw less than 0.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame4() {
    unstartedGame.startGame(deckDefault, true, 7, -1);
  }

  // Test startGame with null deck shuffled.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame5() {
    unstartedGame.startGame(null, true, 7, 3);
  }
  
  // Test startGame with null deck unshuffled.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame5a() {
    unstartedGame.startGame(null, false, 7, 3);
  }
  
  // Test startGame with a deck with duplicates.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame6() {
    unstartedGame.startGame(deckWithDuplicates, false, 7, 2);
  }
  
  // Test startGame with a deck with duplicates shuffled.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame7() {
    unstartedGame.startGame(deckWithDuplicates, true, 7, 2);
  }
  
  // Test startGame with a deck with duplicates shuffled.
  // after game was already started
  @Test(expected = IllegalStateException.class)
  public void testInvalidStartGame8() {
    unstartedGame.startGame(deckDefault, false, 7, 3);
    try {
      unstartedGame.startGame(deckWithDuplicates, true, 7, 3);
    } catch (IllegalArgumentException e) {
      // Expecting this exception
    }
    assertEquals(-1, unstartedGame.getNumDraw());
    assertEquals(-1, unstartedGame.getNumRows());
    unstartedGame.getScore(); // expect exception.
  }
  
  // Test startGame with a deck that is too small.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame9() {
    deckDefault.remove(0);
    unstartedGame.startGame(deckDefault, true, 7, 2);
  }
  
  // Test startGame with a deck that is too large.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame9a() {
    deckDefault.add(null);
    unstartedGame.startGame(deckDefault, true, 7, 3);
  }
  
  // Test startGame with a deck with duplicates at first 2 cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame10() {
    unstartedGame.startGame(deckWithDuplicates2, false, 7, 3);
  }
  
  // Test startGame with a deck with duplicates at first and last cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame11() {
    unstartedGame.startGame(deckWithDuplicates3, false, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at last 2 cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame12() {
    unstartedGame.startGame(deckWithDuplicates4, false, 7, 3);
  }

  // Test startGame with a deck with duplicates at first 2 cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame13() {
    unstartedGame.startGame(deckWithDuplicates2, true, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at first and last cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame14() {
    unstartedGame.startGame(deckWithDuplicates3, true, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at last 2 cards.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame15() {
    unstartedGame.startGame(deckWithDuplicates4, true, 7, 3);
  }
  
  // test that the deck gets shuffled appropriately.
  @Test
  public void testShuffle() {
    // In a truly random shuffling, every permutation is possible
    // and there are many permutations. There is a very small
    // probability that all cards could end up in their original
    // positions.
    List<Card> unshuffled = defaultShuffled.getDeck();
    Iterator<Card> it = unshuffled.iterator();
    int count = 0;
    for (int i = 0; i < defaultShuffled.getNumRows(); i++) {
      for (int j = 0; j < defaultShuffled.getRowWidth(i); j++) {
        if (defaultShuffled.getCardAt(i, j) != null
            && defaultShuffled.getCardAt(i, j).equals(it.next())) {
          count++;
        }
      }
    }
    assertTrue("At least one card must be shuffled", count < unshuffled.size());
  }

  // Test removeWithRows using a valid move.
  @Test
  public void testRemoveWithRows() {
    assertNotNull(startedCustomGame1.getCardAt(1, 0));
    assertNotNull(startedCustomGame1.getCardAt(1, 1));
    int score = startedCustomGame1.getScore() - 13;
    startedCustomGame1.remove(1, 0, 1, 1);
    assertEquals(score, startedCustomGame1.getScore());
    assertNull(startedCustomGame1.getCardAt(1, 0));
    assertNull(startedCustomGame1.getCardAt(1, 1));
    // Test remove 2 in different peaks
    score = gameDraw2DifferentPeaks.getScore() - 13;
    gameDraw2DifferentPeaks.remove(1, 0, 1, 7);
    assertEquals(score, gameDraw2DifferentPeaks.getScore());
    assertNotNull(gameRemoveRow0.getCardAt(0,  0));
    assertNotNull(gameRemoveRow0.getCardAt(0,  4));
    gameRemoveRow0.remove(0, 0, 0, 4);
    assertNull(gameRemoveRow0.getCardAt(0,  0));
    assertNull(gameRemoveRow0.getCardAt(0,  4));
  }

  // Test removeWithRows on unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testRemoveWithRowsInvalid1() {
    unstartedGame.remove(0, 0, 0, 0);
  }

  // test removeWithRows with row1 too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid3() {
    startedDefaultGame.remove(7, 0, 4, 1);
  }

  // test removeWithRows with row1 negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid4() {
    startedDefaultGame.remove(-1, 0, 2, 1);
  }

  // test removeWithRows with row2 too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid5() {
    startedDefaultGame.remove(2, 0, 7, 1);
  }

  // test removeWithRows with row2 negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid6() {
    startedDefaultGame.remove(2, 0, -1, 1);
  }

  // test removeWithRows with card1 too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid7() {
    startedDefaultGame.remove(2, 9, 1, 1);
  }

  // test removeWithRows with card1 negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid8() {
    startedDefaultGame.remove(2, -1, 1, 1);
  }

  // test removeWithRows with card2 too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid9() {
    startedDefaultGame.remove(2, 0, 2, 9);
  }

  // test removeWithRows with card2 negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid10() {
    startedDefaultGame.remove(2, 1, 1, -1);
  }

  // test removeWithRows on covered card.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid11() {
    startedDefaultGame.remove(0, 0, 1, 0);
  }

  // test removeWithRows on card already removed.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid12() {
    startedCustomGame2.remove(1, 1, 1, 2);
  }
  
  // test removeWithRows on cards that do not sum to 13.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid13() {
    startedDefaultGame.remove(6, 1, 6, 2);
  }

  // Test removing a single card successfully.
  @Test
  public void testRemoveSingleCard() {
    assertEquals(new Card(Suit.SPADE, Rank.KING), startedDefaultGame.getCardAt(6,  0));
    int score = startedDefaultGame.getScore() - 13;
    startedDefaultGame.remove(6, 0);
    assertEquals(score, startedDefaultGame.getScore());
    assertNull(startedDefaultGame.getCardAt(6, 0));
  }

  // Test removing single card in unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testRemoveSingleCardInvalid1() {
    unstartedGame.remove(0, 0);
  }

  // Test removing single card with row too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid2() {
    startedDefaultGame.remove(7, 0);
  }

  // Test removing single card with row negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid3() {
    startedDefaultGame.remove(-1, 0);
  }

  // Test removing single card with card too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid4() {
    startedDefaultGame.remove(1, 8);
  }

  // Test removing single card with card negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid5() {
    startedDefaultGame.remove(1, -1);
  }

  // Test removing single card that was already removed.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid6() {
    startedCustomGame2.remove(1, 2);
  }
  
  // Test removing single card that is covered.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid7() {
    startedCustomGame2.remove(0, 0);
  }

  // Test successful removeFromDraw.
  @Test
  public void testRemoveFromDraw() {
    assertNotNull(startedCustomGameDraw.getCardAt(1, 3));
    assertEquals(2, startedCustomGameDraw.getNumDraw());
    int score = startedCustomGameDraw.getScore() - 9;
    startedCustomGameDraw.removeUsingDraw(0, 1, 3);
    assertEquals(score, startedCustomGameDraw.getScore());
    assertNull(startedCustomGameDraw.getCardAt(1, 3));
    assertEquals(2, startedCustomGameDraw.getNumDraw());
    assertNull(startedCustomGameDraw.getDrawCards().get(0));
  }

  // Test removeFromDraw on unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testRemoveDrawInvalid1() {
    unstartedGame.removeUsingDraw(0, 0, 0);
  }

  // Test removeDraw with row too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid2() {
    startedDefaultGame.removeUsingDraw(0, 7, 0);
  }

  // Test removeDraw with row negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid3() {
    startedDefaultGame.removeUsingDraw(0, -1, 0);
  }

  // Test removeDraw with card too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid4() {
    startedDefaultGame.removeUsingDraw(0, 1, 8);
  }

  // Test removeDraw with card negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid5() {
    startedDefaultGame.removeUsingDraw(0, 1, -1);
  }

  // Test removeDraw with draw too big.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid6() {
    startedDefaultGame.removeUsingDraw(2, 0, 0);
  }

  // Test removeDraw with draw negative.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid7() {
    startedDefaultGame.removeUsingDraw(-1, 2, 0);
  }

  // Test removeDraw with covered card.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid8() {
    startedDefaultGame.removeUsingDraw(0, 0, 0);
  }

  // Test removeDraw with removed card.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid9() {
    startedCustomGameDraw.removeUsingDraw(0, 2, 0);
  }

  // Test removeDraw with invalid sum.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid10() {
    startedDefaultGame.removeUsingDraw(0, 6, 1);
  }
  
  // Test removeDraw with an invalid index.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid11() {
    startedDefaultGame.removeUsingDraw(2, 2, 0);
  }
  
  // Test removeUsingDraw with empty draw slot.
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid12() {
    startedCustomGame2.removeUsingDraw(0, 2, 0);
  } 

  // Test discarding from draw.
  @Test
  public void testDiscardDraw() {
    // Remove from draw and replace from stock
    assertEquals(new Card(Suit.CLUB, Rank.FOUR), gameDraw2.getDrawCards().get(0));
    gameDraw2.discardDraw(0);
    assertEquals(new Card(Suit.CLUB, Rank.EIGHT), gameDraw2.getDrawCards().get(0));
    assertEquals(2, gameDraw2.getDrawCards().size());

    // Remove from draw, stock already empty.
    gameDraw2.discardDraw(1);
    assertEquals(2, gameDraw2.getNumDraw());
    assertNull(gameDraw2.getDrawCards().get(1));
  }

  // test discardDraw on unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testDiscardDrawInvalid1() {
    unstartedGame.discardDraw(0);
  }

  // test discardDraw with draw too big.
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid2() {
    startedDefaultGame.discardDraw(2);
  }

  // test discardDraw with draw negative.
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid3() {
    startedDefaultGame.discardDraw(-1);
  }
  
  // test discardDraw with removed draw.
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid4() {
    startedCustomGameDraw.discardDraw(0);
    startedCustomGameDraw.discardDraw(0);
  }
  

  // test getNumRows.
  @Test
  public void testGetNumRows() {
    assertEquals(-1, unstartedGame.getNumRows());
    assertEquals(7, startedDefaultGame.getNumRows());

  }

  // test getNumDraws.
  @Test
  public void testGetNumDraws() {
    assertEquals(-1, unstartedGame.getNumDraw());
    assertEquals(2, startedDefaultGame.getNumDraw());

  }

  // test getRowWidth.
  @Test
  public void testGetRowWidth() {
    assertEquals(7, startedDefaultGame.getRowWidth(0));
    assertEquals(8, startedDefaultGame.getRowWidth(1));
    assertEquals(9, startedDefaultGame.getRowWidth(2));
    assertEquals(10, startedDefaultGame.getRowWidth(3));
    assertEquals(11, startedDefaultGame.getRowWidth(4));
    assertEquals(12, startedDefaultGame.getRowWidth(5));
    assertEquals(13, startedDefaultGame.getRowWidth(6));

  }

  // test getRowWidth on unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testGetRowWidthInvalid1() {
    unstartedGame.getRowWidth(0);

  }

  // test getRowWidth with row too big.
  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidthInvalid2() {
    startedDefaultGame.getRowWidth(7);
  }

  // test getRowWidth with row negative.
  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidthInvalid3() {
    startedDefaultGame.getRowWidth(-1);
  }

  // test getCardAt.
  @Test
  public void testGetCardAt() {
    assertEquals(new Card(Suit.CLUB, Rank.ACE), startedCustomGame1.getCardAt(0, 0));
    assertEquals(new Card(Suit.CLUB, Rank.TEN), startedCustomGame1.getCardAt(0, 1));
    assertEquals(new Card(Suit.CLUB, Rank.TWO), startedCustomGame1.getCardAt(0, 2));
    assertEquals(new Card(Suit.CLUB, Rank.SIX), startedCustomGame1.getCardAt(1, 0));
    assertEquals(new Card(Suit.CLUB, Rank.SEVEN), startedCustomGame1.getCardAt(1, 1));
    assertEquals(new Card(Suit.CLUB, Rank.KING), startedCustomGame1.getCardAt(1, 2));
    for (int i = 0; i < startedDefaultGame.getNumRows(); i++) {
      for (int j = 0; j < startedDefaultGame.getRowWidth(i); j++) {
        // test that getCardAt() works for all locations
        startedDefaultGame.getCardAt(i, j);
        // After the first 2 rows, there are no null cards
        if (i >= 2) {
          assertNotNull("Card not null at " + i + "," + j, startedDefaultGame.getCardAt(i, j));
        }
      }
    }
    assertNull(startedCustomGame2.getCardAt(2, 2));
  }

  // test getCardAt on unstarted game.
  @Test(expected = IllegalStateException.class)
  public void testGetCardInvalid1() {
    unstartedGame.getCardAt(0, 0);

  }

  // test getCardAt with row too big.
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid2() {
    startedDefaultGame.getCardAt(7, 1);
  }

  // test getCardAt with row negative.
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid3() {
    startedDefaultGame.getCardAt(-1, 1);
  }

  // test getCardAt with card too big.
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid4() {
    startedDefaultGame.getCardAt(1, 8);
  }

  // test getCardAt with card negative.
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid5() {
    startedDefaultGame.getCardAt(1, -1);
  }

  // test isGameOver.
  @Test
  public void testIsGameOver() {
    assertFalse(startedCustomGame1.isGameOver());
    assertTrue(gameWin.isGameOver());
    assertTrue(gameLose.isGameOver());
    assertTrue(gameLostNoDraw.isGameOver());
    assertFalse(startedCustomGameDraw.isGameOver());
    assertFalse(gameNotOver.isGameOver());
  }

  // test isGameOver for game not started.
  @Test(expected = IllegalStateException.class)
  public void testIsGameOverInvalid() {
    unstartedGame.isGameOver();
  }

  // Test getScore.
  @Test
  public void testGetScore() {
    assertEquals(0, gameWin.getScore());
    assertNotEquals(0, gameLose.getScore());
    int score = 0;
    for (int i = 0; i < startedDefaultGame.getNumRows(); i++) {
      for (int j = 0; j < startedDefaultGame.getRowWidth(i); j++) {
        if (startedDefaultGame.getCardAt(i, j) != null) {
          score += startedDefaultGame.getCardAt(i, j).getRank();
        }
      }
    }
    assertEquals(score, startedDefaultGame.getScore());
  }

  // Test getScore for game not started.
  @Test(expected = IllegalStateException.class)
  public void testGetScoreInvalid() {
    unstartedGame.getScore();
  }
  
  // Test getDrawCards.
  @Test
  public void testGetDrawCards() {
    List<Card> draw = startedCustomGameDraw.getDrawCards();
    assertEquals(draw.get(0), draw2Cards.get(0));
    assertEquals(draw.get(1), draw2Cards.get(1));
    
  }
  
  // Test empty getDrawCards.
  @Test
  public void testEmptyGetDrawCards() {
    List<Card> draw = startedCustomGame1.getDrawCards();
    assertEquals(draw, empty);
  }
  
  // Test getDrawCards for game not started.
  @Test(expected = IllegalStateException.class)
  public void testGetDrawCardsInvalid1() {
    unstartedGame.getDrawCards();
  }
  
  // Test that the game properly shuffles the game when told to do so.
  @Test
  public void testIsShuffled() {
    assertTrue(isShuffled(makeGame(true, 7, 3)));
    assertFalse(isShuffled(makeGame(false, 7, 3)));
  }
  
  // Test deal order for unshuffled game.
  @Test
  public void testDealOrderUnshuffled() {
    List<Card> deck = startedDefaultGame.getDeck();
    Iterator<Card> it = deck.iterator();
    for (int i = 0; i < startedDefaultGame.getNumRows(); i++) {
      for (int j = 0; j < startedDefaultGame.getRowWidth(i); j++) {
        if (startedDefaultGame.getCardAt(i, j) != null) {
          assertEquals(it.next(), startedDefaultGame.getCardAt(i, j));
        }
      }
    }
  }
  
  /**
   * Tests to see if the deck is the given model is shuffled.
   * In a shuffled deck, there is a non-zero probability that one or
   * more cards will end up in their default position. The deck is considered
   * to be shuffled if at least one card is not in its default position.
   *
   * @return true if the deck is shuffled.
   */
  private boolean isShuffled(TriPeaksSolitaire model) {
    Iterator<Card> it = model.getDeck().iterator();
    for (int i = 0; i < model.getNumRows(); i++) {
      for (int j = 0; j < model.getRowWidth(i); j++) {
        if (model.getCardAt(i, j) != null && !model.getCardAt(i, j).equals(it.next())) {
          return true;
        }
      }
    }
    return false;
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
  private TriPeaksSolitaire makeGame(boolean shuffle, int rows, int draw) {
    TriPeaksSolitaire game = new TriPeaksSolitaire();
    List<Card> stdDeck = game.getDeck();
    game.startGame(stdDeck, shuffle, rows, draw);
    return game;
  }

}
