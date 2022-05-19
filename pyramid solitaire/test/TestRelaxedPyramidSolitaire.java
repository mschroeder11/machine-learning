import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the public methods of {@link RelaxedPyramidSolitaire}s.
 */
public class TestRelaxedPyramidSolitaire {

  /**
   * objects to test methods on.
   */
  RelaxedPyramidSolitaire unstartedGame;
  RelaxedPyramidSolitaire startedDefaultGame;
  RelaxedPyramidSolitaire startedCustomGame1;
  RelaxedPyramidSolitaire startedCustomGame2;
  RelaxedPyramidSolitaire startedCustomGameDraw;
  RelaxedPyramidSolitaire gameDraw2;
  RelaxedPyramidSolitaire gameWin;
  RelaxedPyramidSolitaire gameLose;
  RelaxedPyramidSolitaire unstartedGame2;
  RelaxedPyramidSolitaire startedDefaultGame2;
  RelaxedPyramidSolitaire defaultGameChangeDrawSize;
  RelaxedPyramidSolitaire defaultGameChangeNumRows;
  RelaxedPyramidSolitaire defaultShuffled;
  RelaxedPyramidSolitaire defaultGameDiscardDraw;
  RelaxedPyramidSolitaire defaultGameChangedStock;
  RelaxedPyramidSolitaire gameLostNoDraw;
  List<Card> deckWithStockChanged;
  List<Card> deckWithKing;
  List<Card> deckWithCardRemoved;
  List<Card> deck1;
  List<Card> deckWithStock;
  List<Card> deckWin;
  List<Card> deckLose;
  List<Card> draw2Cards;
  List<Card> empty;
  List<Card> deckDefault;
  List<Card> deckWithDuplicates;

  RelaxedPyramidSolitaire gameRelaxedRemove;
  RelaxedPyramidSolitaire gameRelaxedRemove2;
  RelaxedPyramidSolitaire gameRelaxedRemove3;
  RelaxedPyramidSolitaire gameRelaxedRemove4;
  RelaxedPyramidSolitaire gameRelaxedFail1;
  RelaxedPyramidSolitaire gameRelaxedFail2;
  RelaxedPyramidSolitaire gameRelaxedFail3;
  RelaxedPyramidSolitaire gameRelaxedFail4;
  RelaxedPyramidSolitaire gameRelaxedFail5;
  List<Card> deckRelaxedFail5;
  List<Card> deckRelaxedRemove;
  List<Card> deckRelaxedRemove2;
  List<Card> deckRelaxedRemove3;
  List<Card> deckRelaxedRemove4;
  List<Card> deckRelaxedFail1;
  List<Card> deckRelaxedFail2;
  List<Card> deckRelaxedFail3;
  List<Card> deckRelaxedFail4;
  List<Card> deckWithDuplicates2;
  List<Card> deckWithDuplicates3;
  List<Card> deckWithDuplicates4;
  
 
  // Initialize data
  @Before
  public void setup() {
    Card[] p1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deckWithKing = new ArrayList<Card>(Arrays.asList(p1));
    Card[] p2 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck1 = new ArrayList<Card>(Arrays.asList(p2));
    Card[] p3 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE),
        new Card(Suit.CLUB, Rank.EIGHT)};
    deckWithStock = new ArrayList<Card>(Arrays.asList(p3));
    Card[] p4 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), null, new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.THREE)};
    deckWithCardRemoved = new ArrayList<Card>(Arrays.asList(p4));

    Card[] cardsWin = {null, null, null, null, null, null, new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.TWO)};
    deckWin = new ArrayList<Card>(Arrays.asList(cardsWin));
    Card[] cardsLose = {new Card(Suit.CLUB, Rank.KING), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)}; 
    deckLose = new ArrayList<Card>(Arrays.asList(cardsLose));
    Card[] p6 = {new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    draw2Cards = new ArrayList<Card>(Arrays.asList(p6));
    empty = new ArrayList<Card>();
    unstartedGame = new RelaxedPyramidSolitaire();
    deckWithDuplicates = unstartedGame.getDeck();
    deckWithDuplicates.set(0,  deckWithDuplicates.get(8));
    deckDefault = unstartedGame.getDeck();
    startedDefaultGame = makeGame(false, 7, 2);
    startedCustomGame1 = new RelaxedPyramidSolitaire(deckWithKing, 3, 0);
    startedCustomGameDraw = new RelaxedPyramidSolitaire(deck1, 3, 2);
    gameDraw2 = new RelaxedPyramidSolitaire(deckWithStock, 3, 2);
    startedCustomGame2 = new RelaxedPyramidSolitaire(deckWithCardRemoved, 3, 2);
    gameWin = new RelaxedPyramidSolitaire(deckWin, 3, 3);
    gameLose = new RelaxedPyramidSolitaire(deckLose, 3, 3);
    gameLostNoDraw = new RelaxedPyramidSolitaire(deckLose, 3, 0);
    unstartedGame2 = new RelaxedPyramidSolitaire();
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
    defaultGameChangedStock = new RelaxedPyramidSolitaire(deckWithStockChanged, 7, 2);

    // Queen covers Ace, null card on right - relaxed rules should allow this
    Card[] relaxedRemove = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.QUEEN)};
    deckRelaxedRemove = new ArrayList<Card>(Arrays.asList(relaxedRemove));
    gameRelaxedRemove = new RelaxedPyramidSolitaire(deckRelaxedRemove, 7, 3);
    // Queen covers Ace, null card on left - relaxed rules should allow this
    Card[] relaxedRemove2 = {new Card(Suit.CLUB, Rank.ACE), null, new Card(Suit.CLUB, Rank.QUEEN)};
    deckRelaxedRemove2 = new ArrayList<Card>(Arrays.asList(relaxedRemove2));
    gameRelaxedRemove2 = new RelaxedPyramidSolitaire(deckRelaxedRemove2, 7, 3);
    // Ace in row 2, covered by Queen to the right in row 3, null to the left
    Card[] relaxedRemove3 = {new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.FIVE),
        new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.SIX), null,
        new Card(Suit.CLUB, Rank.QUEEN)};
    deckRelaxedRemove3 = new ArrayList<Card>(Arrays.asList(relaxedRemove3));
    gameRelaxedRemove3 = new RelaxedPyramidSolitaire(deckRelaxedRemove3, 7, 3);
    // Ace covered by Queen to the left and Four to the right. Should not allow   
    Card[] relaxedFail1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.QUEEN),
        new Card(Suit.CLUB, Rank.FOUR)};
    deckRelaxedFail1 = new ArrayList<Card>(Arrays.asList(relaxedFail1));
    gameRelaxedFail1 = new RelaxedPyramidSolitaire(deckRelaxedFail1, 7, 3);
    // Ace covered by Queen to the left, null, to the right, but Queen is also covered
    Card[] relaxedFail2 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.QUEEN),
        null, new Card(Suit.CLUB, Rank.FOUR)};
    deckRelaxedFail2 = new ArrayList<Card>(Arrays.asList(relaxedFail2));
    gameRelaxedFail2 = new RelaxedPyramidSolitaire(deckRelaxedFail2, 7, 3);
    // Ace covered by Queen to the right, null, to the left, but Queen is also covered
    Card[] relaxedFail3 = {new Card(Suit.CLUB, Rank.ACE), null, new Card(Suit.CLUB, Rank.QUEEN),
        null, null, new Card(Suit.CLUB, Rank.FOUR)};
    deckRelaxedFail3 = new ArrayList<Card>(Arrays.asList(relaxedFail3));
    gameRelaxedFail3 = new RelaxedPyramidSolitaire(deckRelaxedFail3, 7, 3);
    // Ace and Queen separated by more than 1 row
    Card[] relaxedFail4 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.FIVE), new Card(Suit.CLUB, Rank.QUEEN)};
    deckRelaxedFail4 = new ArrayList<Card>(Arrays.asList(relaxedFail4));
    gameRelaxedFail4 = new RelaxedPyramidSolitaire(deckRelaxedFail4, 7, 3);
    // Ace covered by Queen to the right, null, to the left, but Queen is also covered
    Card[] relaxedFail5 = {new Card(Suit.CLUB, Rank.ACE), null, new Card(Suit.CLUB, Rank.QUEEN),
        null, new Card(Suit.CLUB, Rank.FOUR), null};
    deckRelaxedFail5 = new ArrayList<Card>(Arrays.asList(relaxedFail5));
    gameRelaxedFail5 = new RelaxedPyramidSolitaire(deckRelaxedFail5, 7, 3);
    
    Card[] relaxedRemove4 = {new Card(Suit.CLUB, Rank.QUEEN), new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SEVEN), null,
        new Card(Suit.CLUB, Rank.JACK), new Card(Suit.CLUB, Rank.SIX)};
    deckRelaxedRemove4 = new ArrayList<Card>(Arrays.asList(relaxedRemove4));
    gameRelaxedRemove4 = new RelaxedPyramidSolitaire(deckRelaxedRemove4, 7, 3);
    
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
    assertEquals(52, deck.size());
    boolean duplicate = false;
    for (int i = 0; i < deck.size() - 1; i++) {
      Card current = deck.get(i);
      if (current != null) {
        for (int j = i + 1; j < deck.size(); j++) {
          Card next = deck.get(j);
          if (current.equals(next)) {
            duplicate = true;
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

  // Test startGame with numRows too big
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame1() {
    unstartedGame.startGame(deckWithKing, true, 66, 0);
  }

  // Test startGame with numDraw too big
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame2() {
    unstartedGame.startGame(deckWithKing, true, 3, 100);
  }

  // Test startGame with numRows less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame3() {
    unstartedGame.startGame(deckWithKing, true, -1, 0);
  }

  // Test startGame with numDraw less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame4() {
    unstartedGame.startGame(deckWithKing, true, 3, -1);
  }

  // Test startGame with null deck
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame5() {
    unstartedGame.startGame(null, true, 3, 2);
  }
  
  // Test startGame with a deck with duplicates
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame6() {
    List<Card> deck = unstartedGame.getDeck();
    deck.set(0,  deck.get(10));
    unstartedGame.startGame(deck, false, 7, 2);
  }
  
  // Test startGame with a deck with duplicates shuffled
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame7() {
    unstartedGame.startGame(deckWithDuplicates, true, 7, 2);
  }
  
  // Test startGame with a deck with duplicates shuffled
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
  
  // Test startGame with a deck with duplicates at first 2 cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame10() {
    unstartedGame.startGame(deckWithDuplicates2, false, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at first and last cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame11() {
    unstartedGame.startGame(deckWithDuplicates3, false, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at last 2 cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame12() {
    unstartedGame.startGame(deckWithDuplicates4, false, 7, 3);
  }

  // Test startGame with a deck with duplicates at first 2 cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame13() {
    unstartedGame.startGame(deckWithDuplicates2, true, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at first and last cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame14() {
    unstartedGame.startGame(deckWithDuplicates3, true, 7, 3);
  }
 
  // Test startGame with a deck with duplicates at last 2 cards
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartGame15() {
    unstartedGame.startGame(deckWithDuplicates4, true, 7, 3);
  }
  
  // test that the deck gets shuffled appropriately
  @Test
  public void testShuffle() {
    // In a truly random shuffling, every permutation is possible
    // and there are 52! permutations. There is a very small
    // probability that all cards could end up in their original
    // positions.
    List<Card> unshuffled = defaultShuffled.getDeck();
    Iterator<Card> it = unshuffled.iterator();
    int count = 0;
    for (int i = 0; i < defaultShuffled.getNumRows(); i++) {
      for (int j = 0; j < defaultShuffled.getRowWidth(i); j++) {
        if (defaultShuffled.getCardAt(i, j).equals(it.next())) {
          count++;
        }
      }
    }
    assertTrue("At least one card must be shuffled", count < unshuffled.size());
  }

  // Test removeWithRows using a valid move
  @Test
  public void testRemoveWithRows() {
    assertNotNull(startedCustomGame1.getCardAt(2, 0));
    assertNotNull(startedCustomGame1.getCardAt(2, 1));
    int score = startedCustomGame1.getScore() - 13;
    startedCustomGame1.remove(2, 0, 2, 1);
    assertNull(startedCustomGame1.getCardAt(2, 0));
    assertNull(startedCustomGame1.getCardAt(2, 1));
    assertEquals(score, startedCustomGame1.getScore());

  }

  // Test removeWithRows on unstarted game
  @Test(expected = IllegalStateException.class)
  public void testRemoveWithRowsInvalid1() {
    unstartedGame.remove(0, 0, 0, 0);
  }

  // test removeWithRows with row1 too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid3() {
    startedCustomGame1.remove(7, 0, 4, 1);
  }

  // test removeWithRows with row1 negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid4() {
    startedCustomGame1.remove(-1, 0, 2, 1);
  }

  // test removeWithRows with row2 too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid5() {
    startedCustomGame1.remove(2, 0, 3, 1);
  }

  // test removeWithRows with row2 negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid6() {
    startedCustomGame1.remove(2, 0, -1, 1);
  }

  // test removeWithRows with card1 too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid7() {
    startedCustomGame1.remove(2, 2, 1, 1);
  }

  // test removeWithRows with card1 negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid8() {
    startedCustomGame1.remove(2, -1, 1, 1);
  }

  // test removeWithRows with card2 too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid9() {
    startedCustomGame1.remove(2, 0, 2, 3);
  }

  // test removeWithRows with card2 negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid10() {
    startedCustomGame1.remove(2, 1, 1, -1);
  }

  // test removeWithRows on covered card
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid11() {
    startedCustomGame1.remove(0, 0, 1, 0);
  }

  // test removeWithRows on card already removed
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid12() {
    startedCustomGame2.remove(2, 2, 1, 0);
  }
  
  // test removeWithRows on cards that do not sum to 13
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWithRowsInvalid13() {
    startedCustomGame1.remove(0, 0, 0, 2);
  }

  // Test removing cards with relaxed conditions.
  @Test
  public void testRelaxedRemove() {
    assertEquals(13, gameRelaxedRemove.getScore());
    gameRelaxedRemove.remove(0, 0, 1, 0);
    assertEquals(0, gameRelaxedRemove.getScore());
    assertTrue(gameRelaxedRemove.isGameOver());
    assertEquals(13, gameRelaxedRemove2.getScore());
    gameRelaxedRemove2.remove(0, 0, 1, 1);
    assertEquals(0, gameRelaxedRemove2.getScore());
    assertTrue(gameRelaxedRemove2.isGameOver());
    gameRelaxedRemove3.remove(1, 1, 2, 2);
    assertFalse(gameRelaxedRemove4.isGameOver());
    assertEquals(39, gameRelaxedRemove4.getScore());
    gameRelaxedRemove4.remove(2, 0, 3, 0);
    assertEquals(26, gameRelaxedRemove4.getScore());
  }
  
  // Test removing cards with relaxed conditions, reversing card order
  @Test
  public void testRelaxedRemove2() {
    assertEquals(13, gameRelaxedRemove.getScore());
    gameRelaxedRemove.remove(1, 0, 0, 0);
    assertEquals(0, gameRelaxedRemove.getScore());
    assertTrue(gameRelaxedRemove.isGameOver());
    assertEquals(13, gameRelaxedRemove2.getScore());
    gameRelaxedRemove2.remove(1, 1, 0, 0);
    assertEquals(0, gameRelaxedRemove2.getScore());
    assertTrue(gameRelaxedRemove2.isGameOver());
    gameRelaxedRemove3.remove(2, 2, 1, 1);  // reverse order
    assertTrue(gameRelaxedFail1.isGameOver());
    assertTrue(gameRelaxedFail2.isGameOver());
    assertTrue(gameRelaxedFail3.isGameOver());
    assertTrue(gameRelaxedFail4.isGameOver());
  }

  // Test removing card with almost relaxed condition fails
  // where two cards cover one of the cards
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail1() {
    gameRelaxedFail1.remove(0, 0, 1, 0);
  }

  // Test removing card with almost relaxed condition fails
  // where 2nd card is covered by 1 card.
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail2() {
    gameRelaxedFail2.remove(0, 0, 1, 0);
  }

  // Test removing card with almost relaxed condition fails
  // where 2nd card is covered by 1 card.
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail3() {
    gameRelaxedFail3.remove(0, 0, 1, 0);
  }

  // Test removing card with almost relaxed condition fails
  // where 2nd card is covered by 1 card.
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail3a() {
    gameRelaxedFail3.remove(0, 0, 1, 1);
  }

  // Test removing card with almost relaxed condition fails
  // where the cards are not in adjacent rows
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail4() {
    gameRelaxedFail4.remove(0, 0, 2, 0);
  }

  // Test removing card with almost relaxed condition fails
  // where the 1 card not actually covering
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail4a() {
    gameRelaxedFail4.remove(1, 1, 2, 0);
  }
  
  // Test removing card with almost relaxed condition fails
  // where 2nd card is covered by 1 card to the left.
  @Test(expected = IllegalArgumentException.class)
  public void testRelaxedFail5() {
    gameRelaxedFail3.remove(0, 0, 1, 1);
  }

  @Test
  public void testRemoveSingleCard() {
    assertNotNull(startedCustomGame1.getCardAt(2, 2));
    assertEquals(new Card(Suit.CLUB, Rank.SEVEN), startedCustomGame1.getCardAt(2, 1));
    int score = startedCustomGame1.getScore() - 13;
    startedCustomGame1.remove(2, 2);
    assertNull(startedCustomGame1.getCardAt(2, 2));
    assertEquals(score, startedCustomGame1.getScore());
    assertEquals(new Card(Suit.CLUB, Rank.SEVEN), startedCustomGame1.getCardAt(2, 1));
  }

  // Test removing single card that is not of proper rank
  @Test(expected = IllegalStateException.class)
  public void testRemoveSingleCardInvalid1() {
    unstartedGame.remove(0, 0);
  }

  // Test removing single card with row too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid2() {
    startedCustomGame1.remove(3, 0);
  }

  // Test removing single card with row negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid3() {
    startedCustomGame1.remove(-1, 0);
  }

  // Test removing single card with card too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid4() {
    startedCustomGame1.remove(2, 3);
  }

  // Test removing single card with card negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid5() {
    startedCustomGame1.remove(1, -1);
  }

  // Test removing single card that was already removed
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid6() {
    startedCustomGame2.remove(2, 2);
  }
  
  // Test removing single card that is covered
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveSingleCardInvalid7() {
    startedCustomGame2.remove(0, 0);
  }

  // Test successfull removeFromDraw
  @Test
  public void testRemoveFromDraw() {
    int score = startedCustomGameDraw.getScore() - 9;
    assertNotNull(startedCustomGameDraw.getCardAt(2, 2));
    assertEquals(2, startedCustomGameDraw.getNumDraw());
    startedCustomGameDraw.removeUsingDraw(0, 2, 2);
    assertNull(startedCustomGameDraw.getCardAt(2, 2));
    assertEquals(2, startedCustomGameDraw.getNumDraw());
    assertNull(startedCustomGameDraw.getDrawCards().get(0));
    assertEquals(score, startedCustomGameDraw.getScore());
  }

  // Test removeFromDraw on unstarted game
  @Test(expected = IllegalStateException.class)
  public void testRemoveDrawInvalid1() {
    unstartedGame.removeUsingDraw(0, 0, 0);
  }

  // Test removeDraw with row too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid2() {
    startedCustomGameDraw.removeUsingDraw(0, 3, 0);
  }

  // Test removeDraw with row negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid3() {
    startedCustomGameDraw.removeUsingDraw(0, -1, 0);
  }

  // Test removeDraw with card too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid4() {
    startedCustomGameDraw.removeUsingDraw(0, 1, 2);
  }

  // Test removeDraw with card negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid5() {
    startedCustomGameDraw.removeUsingDraw(0, 1, -1);
  }

  // Test removeDraw with draw too big
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid6() {
    startedCustomGameDraw.removeUsingDraw(2, 0, 0);
  }

  // Test removeDraw with draw negative
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid7() {
    startedCustomGameDraw.removeUsingDraw(-1, 2, 0);
  }

  // Test removeDraw with covered card
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid8() {
    startedCustomGameDraw.removeUsingDraw(0, 0, 0);
  }

  // Test removeDraw with removed card
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid9() {
    startedCustomGame2.removeUsingDraw(0, 2, 2);
  }

  // Test removeDraw with invalid sum
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid10() {
    startedCustomGame2.removeUsingDraw(0, 2, 0);
  }
  
  // Test removeDraw with an invalid index
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid11() {
    startedCustomGame2.removeUsingDraw(2, 2, 0);
  }
  
  // Test removeUsingDraw with empty draw slot
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDrawInvalid12() {
    startedCustomGame2.discardDraw(0);
    startedCustomGame2.removeUsingDraw(0, 2, 0);
  } 

  // Test discarding from draw
  @Test
  public void testDiscardDraw() {
    // Remove from draw and replace from stock
    assertEquals(new Card(Suit.CLUB, Rank.FOUR), gameDraw2.getDrawCards().get(0));
    gameDraw2.discardDraw(0);
    assertEquals(new Card(Suit.CLUB, Rank.EIGHT), gameDraw2.getDrawCards().get(0));
    assertEquals(2, gameDraw2.getDrawCards().size());

    // Remove from draw, stock already empty
    gameDraw2.discardDraw(1);
    assertEquals(2, gameDraw2.getNumDraw());
    assertNull(gameDraw2.getDrawCards().get(1));

  }

  // test discardDraw on unstarted game
  @Test(expected = IllegalStateException.class)
  public void testDiscardDrawInvalid1() {
    unstartedGame.discardDraw(0);
  }

  // test discardDraw with draw too big
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid2() {
    startedDefaultGame.discardDraw(10);
  }

  // test discardDraw with draw negative
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid3() {
    startedDefaultGame.discardDraw(-1);
  }
  
  // test discardDraw with removed draw
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardDrawInvalid4() {
    startedCustomGameDraw.discardDraw(0);
    startedCustomGameDraw.discardDraw(0);
  }
  

  // test getNumRows
  @Test
  public void testGetNumRows() {
    assertEquals(-1, unstartedGame.getNumRows());
    assertEquals(7, startedDefaultGame.getNumRows());

  }

  // test getNumDraws
  @Test
  public void testGetNumDraws() {
    assertEquals(-1, unstartedGame.getNumDraw());
    assertEquals(2, startedDefaultGame.getNumDraw());

  }

  // test getRowWidth
  @Test
  public void testGetRowWidth() {
    assertEquals(6, startedDefaultGame.getRowWidth(5));
    assertEquals(1, startedDefaultGame.getRowWidth(0));
    assertEquals(7, startedDefaultGame.getRowWidth(6));

  }

  // test getRowWidth on unstarted game
  @Test(expected = IllegalStateException.class)
  public void testGetRowWidthInvalid1() {
    unstartedGame.getRowWidth(0);

  }

  // test getRowWidth with row too big
  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidthInvalid2() {
    startedDefaultGame.getRowWidth(20);
  }

  // test getRowWidth with row negative
  @Test(expected = IllegalArgumentException.class)
  public void testGetRowWidthInvalid3() {
    startedDefaultGame.getRowWidth(-2);
  }

  // test getCardAt
  @Test
  public void testGetCardAt() {
    assertEquals(new Card(Suit.CLUB, Rank.SEVEN), startedCustomGame1.getCardAt(2, 1));
    assertEquals(new Card(Suit.CLUB, Rank.ACE), startedCustomGame1.getCardAt(0, 0));
    assertEquals(new Card(Suit.CLUB, Rank.TEN), startedCustomGame1.getCardAt(1, 0));
    assertEquals(new Card(Suit.CLUB, Rank.TWO), startedCustomGame1.getCardAt(1, 1));
    assertEquals(new Card(Suit.CLUB, Rank.KING), startedCustomGame1.getCardAt(2, 2));
    assertNull(startedCustomGame2.getCardAt(2, 2));
  }

  // test getCardAt on unstarted game
  @Test(expected = IllegalStateException.class)
  public void testGetCardInvalid1() {
    unstartedGame.getCardAt(0, 0);

  }

  // test getCardAt with row too big
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid2() {
    startedDefaultGame.getCardAt(10, 1);
  }

  // test getCardAt with row negative
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid3() {
    startedDefaultGame.getCardAt(-1, 1);
  }

  // test getCardAt with card too big
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid4() {
    startedDefaultGame.getCardAt(1, 2);
  }

  // test getCardAt with card negative
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardInvalid5() {
    startedDefaultGame.getCardAt(1, -1);
  }

  // test isGameOver
  @Test
  public void testIsGameOver() {
    assertFalse(startedCustomGame1.isGameOver());
    assertTrue(gameWin.isGameOver());
    assertTrue(gameLose.isGameOver());
    assertTrue(gameLostNoDraw.isGameOver());
    assertFalse(startedCustomGameDraw.isGameOver());
    assertFalse(gameRelaxedRemove.isGameOver());
    assertFalse(gameRelaxedRemove2.isGameOver());
    assertFalse(gameRelaxedRemove3.isGameOver());
    assertTrue(gameRelaxedFail1.isGameOver());
    assertTrue(gameRelaxedFail2.isGameOver());
    assertTrue(gameRelaxedFail3.isGameOver());
    assertTrue(gameRelaxedFail4.isGameOver());
  }

  // test isGameOver for game not started
  @Test(expected = IllegalStateException.class)
  public void testIsGameOverInvalid() {
    unstartedGame.isGameOver();
  }

  // Test getScore
  @Test
  public void testGetScore() {
    assertEquals(0, gameWin.getScore());
    assertNotEquals(0, gameLose.getScore());
  }

  // Test getScore for game not started
  @Test(expected = IllegalStateException.class)
  public void testGetScoreInvalid() {
    unstartedGame.getScore();
  }
  
  // Test getDrawCards
  @Test
  public void testGetDrawCards() {
    List<Card> draw = startedCustomGameDraw.getDrawCards();
    assertEquals(draw.get(0), draw2Cards.get(0));
    assertEquals(draw.get(1), draw2Cards.get(1));
    
  }
  
  // Test empty getDrawCards
  @Test
  public void testEmptyGetDrawCards() {
    List<Card> draw = startedCustomGame1.getDrawCards();
    assertEquals(draw, empty);
  }
  
  // Test getDrawCards for game not started
  @Test(expected = IllegalStateException.class)
  public void testGetDrawCardsInvalid1() {
    unstartedGame.getDrawCards();
  }
  
  // Test that the game properly shuffles the game when told to do so
  @Test
  public void testIsShuffled() {
    assertTrue(isShuffled(makeGame(true, 7, 3)));
    assertFalse(isShuffled(makeGame(false, 7, 3)));
  }
  
  /**
   * Tests to see if the deck is the given model is shuffled.
   * In a shuffled deck, there is a non-zero probability that one or
   * more cards will end up in their default position. The deck is considered
   * to be shuffled if at least one card is not in its default position.
   *
   * @return true if the deck is shuffled.
   */
  private boolean isShuffled(RelaxedPyramidSolitaire model) {
    Iterator<Card> it = model.getDeck().iterator();
    for (int i = 0; i < model.getNumRows(); i++) {
      for (int j = 0; j < model.getRowWidth(i); j++) {
        if (!model.getCardAt(i, j).equals(it.next())) {
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
  RelaxedPyramidSolitaire makeGame(boolean shuffle, int rows, int draw) {
    RelaxedPyramidSolitaire game = new RelaxedPyramidSolitaire();
    List<Card> stdDeck = game.getDeck();
    game.startGame(stdDeck, shuffle, rows, draw);
    return game;
  }

}
