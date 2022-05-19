package cs3500.pyramidsolitaire.model.hw04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the protected methods of {@link TriPyramidSolitaire}s.
 */
public class TestPrivateTriPeaksSolitaire {
  
  /**
   * objects for testing.
   */
  TriPeaksSolitaire unstartedModel;
  TriPeaksSolitaire model1;
  TriPeaksSolitaire model1card;
  TriPeaksSolitaire customGame1;
  TriPeaksSolitaire customGame2;
  List<Card> customDeck;
  List<Card> deckWithCardRemoved;
  List<Card> deck1;
  List<Card> deckWithStock;
  List<Card> deckWin;
  List<Card> deckLose;
  List<Card> draw2Cards;
  List<Card> empty;
  List<Card> validDuplicates;
  List<Card> invalidDuplicates;
  Iterator<Card> it1;
  ArrayList<Card> customRow1;
  ArrayList<Card> customRow2;

  /**
   * setup the objects for testing.
   */
  @Before
  public void setup() {
    Card[] p1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE),
        new Card(Suit.DIAMOND, Rank.THREE), new Card(Suit.DIAMOND, Rank.ACE),
        new Card(Suit.DIAMOND, Rank.FIVE), new Card(Suit.DIAMOND, Rank.KING),
        new Card(Suit.DIAMOND, Rank.SEVEN), new Card(Suit.DIAMOND, Rank.SIX), 
        new Card(Suit.SPADE, Rank.THREE), new Card(Suit.SPADE, Rank.ACE)};
    customDeck = new ArrayList<Card>(Arrays.asList(p1));
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
    Card[] d1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE), 
        new Card(Suit.CLUB, Rank.THREE), new Card(Suit.DIAMOND, Rank.FOUR),
        new Card(Suit.CLUB, Rank.TEN)};
    validDuplicates = new ArrayList<Card>(Arrays.asList(d1));
    Card[] d2 = {new Card(Suit.CLUB, Rank.THREE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE), 
        new Card(Suit.CLUB, Rank.THREE), new Card(Suit.DIAMOND, Rank.FOUR),
        new Card(Suit.CLUB, Rank.TEN)};
    invalidDuplicates = new ArrayList<Card>(Arrays.asList(d2));
    
    unstartedModel = new TriPeaksSolitaire();
    model1 = new TriPeaksSolitaire();
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1card = new TriPeaksSolitaire();
    model1card.startGame(model1card.getDeck(), false, 1, 3);
    customGame1 = new TriPeaksSolitaire(deckWithStock, 3, 3);
    customGame2 = new TriPeaksSolitaire(customDeck, 7, 3);
    it1 = customDeck.iterator();
    Card[] r1 = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN), 
        new Card(Suit.CLUB, Rank.TWO)};
    customRow1 = new ArrayList<Card>(Arrays.asList(r1));
    Card[] r2 = {new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.KING),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE),
        new Card(Suit.DIAMOND, Rank.THREE)};
    customRow2 = new ArrayList<Card>(Arrays.asList(r2));    
  }
  
  // test deckHasDuplicates
  @Test
  public void testDeckHasDuplicates() {
    assertFalse(model1.deckHasDuplicates(empty));
    assertTrue(model1.deckHasDuplicates(invalidDuplicates));
    assertFalse(model1.deckHasDuplicates(validDuplicates));
    assertFalse(model1.deckHasDuplicates(customDeck));
  }
  
  // test getValidDeckSize
  @Test
  public void testGetValidDeckSize() {
    assertEquals(104, model1.getValidDeckSize());
  }
  
  
  // test calculateNumCards  
  @Test
  public void testCalculateNumCards() {
    assertEquals(1, model1.calculateNumCards(1));
    assertEquals(64, model1.calculateNumCards(7));
  }
  
  // test buildPyramid
  
  
  // test addCardsToRow
  @Test
  public void testAddCardsToRow() {
    ArrayList<Card> first = new ArrayList<>();
    ArrayList<Card> second = new ArrayList<>();
    customGame2.addCardsToRow(3, it1, first);
    customGame2.addCardsToRow(6, it1, second);
    assertEquals(customRow1, first);
    assertEquals(customRow2, second);
  }

}
