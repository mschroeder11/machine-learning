import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the public methods of {@link Card}s.
 */
public class TestCard {

  /**
   * objects to test methods on.
   */
  Card card1;
  Card card2;
  Card card3;
  Card card4;
  Card card5;
  Card card6;
  Card card7;

  @Before
  public void setupTestData() {
    card1 = new Card(Suit.CLUB, Rank.ACE);
    card2 = new Card(Suit.CLUB, Rank.KING);
    card3 = new Card(Suit.CLUB, Rank.SEVEN);
    card4 = new Card(Suit.CLUB, Rank.ACE);
    card5 = new Card(Suit.HEART, Rank.ACE);
    card6 = new Card(Suit.DIAMOND, Rank.ACE);
    card7 = new Card(Suit.SPADE, Rank.ACE);
  }

  // test that the toString prints the right thing
  @Test
  public void testToString() {
    assertEquals("7♣", card3.toString());
    assertEquals("K♣", card2.toString());
  }

  // test the equals method
  @Test
  public void testEquals() {
    assertTrue(card1.equals(card4));
    assertFalse(card1.equals(card2));
    assertFalse(card4.equals(card5));
    assertFalse(card4.equals(card6));
    assertFalse(card4.equals(card7));
    assertTrue(card1.equals(card1));
    assertNotNull(card1);
    assertFalse(card1.equals("A♣"));
  }

  // test the hashcode method
  @Test
  public void testHashcode() {
    assertEquals(card1.hashCode(), (new Card(Suit.CLUB, Rank.ACE)).hashCode());
  }

  // test that addScore properly adds the score
  @Test
  public void testAddScore() {
    assertEquals(14, card1.sum(card2));
    assertEquals(8, card1.sum(card3));
  }

  // test the getRank properly gets the value of the card
  @Test
  public void testGetRank() {
    assertEquals(1, card1.getRank());
    assertEquals(13, card2.getRank());
  }

  // test toString for the Suit enum
  @Test
  public void testSuitToString() {
    assertEquals("♣", Suit.CLUB.toString());
    assertEquals("♥", Suit.HEART.toString());
    assertEquals("♦", Suit.DIAMOND.toString());
    assertEquals("♠", Suit.SPADE.toString());
  }

  // test toString for the Rank enum
  @Test
  public void testRankToString() {
    assertEquals("A", Rank.ACE.toString());
    assertEquals("2", Rank.TWO.toString());
    assertEquals("3", Rank.THREE.toString());
    assertEquals("4", Rank.FOUR.toString());
    assertEquals("5", Rank.FIVE.toString());
    assertEquals("6", Rank.SIX.toString());
    assertEquals("7", Rank.SEVEN.toString());
    assertEquals("8", Rank.EIGHT.toString());
    assertEquals("9", Rank.NINE.toString());
    assertEquals("10", Rank.TEN.toString());
    assertEquals("J", Rank.JACK.toString());
    assertEquals("Q", Rank.QUEEN.toString());
    assertEquals("K", Rank.KING.toString());
  }

  // test value for the Rank enum
  @Test
  public void testRankValue() {
    assertEquals(1, Rank.ACE.value());
    assertEquals(2, Rank.TWO.value());
    assertEquals(3, Rank.THREE.value());
    assertEquals(4, Rank.FOUR.value());
    assertEquals(5, Rank.FIVE.value());
    assertEquals(6, Rank.SIX.value());
    assertEquals(7, Rank.SEVEN.value());
    assertEquals(8, Rank.EIGHT.value());
    assertEquals(9, Rank.NINE.value());
    assertEquals(10, Rank.TEN.value());
    assertEquals(11, Rank.JACK.value());
    assertEquals(12, Rank.QUEEN.value());
    assertEquals(13, Rank.KING.value());
  }

}
