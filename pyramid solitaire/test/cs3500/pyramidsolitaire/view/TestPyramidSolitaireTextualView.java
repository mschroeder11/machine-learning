package cs3500.pyramidsolitaire.view;

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for private methods in PyramidSolitaireTextualView.
 */
public class TestPyramidSolitaireTextualView {

  /**
   * objects to test methods on.
   */
  BasicPyramidSolitaire modelDefault;
  BasicPyramidSolitaire model3Rows;
  BasicPyramidSolitaire model7Rows;
  BasicPyramidSolitaire model2CardsRemoved;
  BasicPyramidSolitaire modelBlankDraw;
  BasicPyramidSolitaire model3RowsSkipDraw;
  BasicPyramidSolitaire model4Rows1Blank;
  BasicPyramidSolitaire modelBlankFirstCard;
  List<Card> deck3Rows;
  List<Card> deck4Rows1Blank;
  List<Card> deck2CardsRemoved;
  List<Card> deck3RowsSkipDraw;
  List<Card> fullDeck;
  List<Card> deckFirstCardBlank;
  
  PyramidSolitaireTextualView viewDefault;
  PyramidSolitaireTextualView viewBlankDraw;
  PyramidSolitaireTextualView view2CardsRemoved;
  PyramidSolitaireTextualView view7Rows;
  PyramidSolitaireTextualView view4Rows1Blank;
  PyramidSolitaireTextualView view3RowsSkipDraw;
  PyramidSolitaireTextualView viewBlankFirstCard;
  

  @Before
  public void setupTestFixture() {
    // reinitialize all data
    modelDefault = new BasicPyramidSolitaire();
    viewDefault = new PyramidSolitaireTextualView(modelDefault);

    Card[] cards3Rows = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck3Rows = new ArrayList<Card>(Arrays.asList(cards3Rows));
    model3Rows = new BasicPyramidSolitaire(deck3Rows, 3, 2);
    modelBlankDraw = new BasicPyramidSolitaire(deck3Rows, 3, 3);
    viewBlankDraw = new PyramidSolitaireTextualView(modelBlankDraw);

    Card[] cards4Rows1Blank = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE), null, null, null, null,
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck4Rows1Blank = new ArrayList<Card>(Arrays.asList(cards4Rows1Blank));
    model4Rows1Blank = new BasicPyramidSolitaire(deck4Rows1Blank, 4, 2);
    view4Rows1Blank = new PyramidSolitaireTextualView(model4Rows1Blank);

    Card[] cards3RowsSkipDraw = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE),
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE), null};
    deck3RowsSkipDraw = new ArrayList<Card>(Arrays.asList(cards3RowsSkipDraw));
    model3RowsSkipDraw = new BasicPyramidSolitaire(deck3RowsSkipDraw, 3, 3);
    view3RowsSkipDraw = new PyramidSolitaireTextualView(model3RowsSkipDraw);

    fullDeck = modelDefault.getDeck();
    model7Rows = new BasicPyramidSolitaire(fullDeck, 7, 0);
    view7Rows = new PyramidSolitaireTextualView(model7Rows);
    
    deck2CardsRemoved = modelDefault.getDeck();
    // 2 cards removed from bottom row
    deck2CardsRemoved.set(22, null);
    deck2CardsRemoved.set(25, null);
    model2CardsRemoved = new BasicPyramidSolitaire(deck2CardsRemoved, 7, 3);
    view2CardsRemoved = new PyramidSolitaireTextualView(model2CardsRemoved);

    Card[] cardsFirstBlank = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), null, new Card(Suit.CLUB, Rank.SEVEN),
        new Card(Suit.CLUB, Rank.NINE), new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.THREE)};
    deckFirstCardBlank = new ArrayList<Card>(Arrays.asList(cardsFirstBlank));
    modelBlankFirstCard = new BasicPyramidSolitaire(deckFirstCardBlank, 3, 2);
    viewBlankFirstCard = new PyramidSolitaireTextualView(modelBlankFirstCard);

  }

  // Test unstarted game pyramid.
  @Test
  public void testUnstartedGameRenderPyramid() {
    assertEquals("", viewDefault.renderPyramid());
  }

  // Test unstarted game draw.
  @Test
  public void testUnstartedGameRenderDraw() {
    assertEquals("", viewDefault.renderDraw());
  }

  // Test render pyramid with 3 rows of cards and 3 draw cards with a blank draw card.
  @Test
  public void testRenderPyramid3RowsSkipDraw() {
    String expected = "    A♣\n" + "  10♣ 2♣\n" + "6♣  7♣  9♣\n";
    assertEquals(expected, view3RowsSkipDraw.renderPyramid());
  }

  // Test render draw with 3 rows of cards and 3 draw cards with a blank draw card.
  @Test
  public void testRenderDrawRowsSkipDraw() {
    String expected = "Draw: 4♣, 3♣";
    assertEquals(expected, view3RowsSkipDraw.renderDraw());
  }

  // Test render with a blank card in draw.
  @Test
  public void testRenderPyramidBlankCard() {
    String expected = "Draw: 4♣, 3♣";
    assertEquals(expected, viewBlankDraw.renderDraw());
  }

  // Test renderPyramid with a blank row.
  @Test
  public void testRenderPyramid4Rows1Blank() {
    String expected = "      A♣\n" + "    10♣ 2♣\n" + "  6♣  7♣  9♣\n\n";
    assertEquals(expected, view4Rows1Blank.renderPyramid());
  }

  // Test renderPyramid with 7 rows.
  @Test
  public void testRenderPyramid7Rows3Draw() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n";
    assertEquals(expected, view7Rows.renderPyramid());
  }

  // Test renderPyramid with blank draw..
  @Test
  public void testRenderBlankDraw() {
    String expected = "Draw:";
    assertEquals(expected, view7Rows.renderDraw());
  }

  // Test renderPyramid with 2 cards removed.
  @Test
  public void testrenderPyramid2CardsRemoved() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦      J♦  Q♦      A♥  2♥\n";
    assertEquals(expected, view2CardsRemoved.renderPyramid());
  }

  
  // Test game with a blank card in draw
  @Test
  public void testBlankCard() {
    String expected = "    A♣\n" + "  10♣ 2♣\n" + "6♣  7♣  9♣\n";
    assertEquals(expected, viewBlankDraw.renderPyramid());
  }

}
