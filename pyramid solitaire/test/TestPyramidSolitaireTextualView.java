import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * Test the public methods for {@link PyramidSolitaireTextualView}s.
 */
public class TestPyramidSolitaireTextualView {

  /**
   * objects to test methods on.
   */
  BasicPyramidSolitaire modelDefault;
  BasicPyramidSolitaire model3Rows;
  BasicPyramidSolitaire model4Rows1Blank;
  BasicPyramidSolitaire model7Rows;
  BasicPyramidSolitaire model7Rows3Draw;
  BasicPyramidSolitaire modelWin;
  BasicPyramidSolitaire model2CardsRemoved;
  BasicPyramidSolitaire modelLose;
  BasicPyramidSolitaire modelLowScore;
  BasicPyramidSolitaire modelBlankDraw;
  BasicPyramidSolitaire model3RowsSkipDraw;
  BasicPyramidSolitaire modelExample;
  BasicPyramidSolitaire modelBlankFirstCard;
  RelaxedPyramidSolitaire modelRelaxed7Rows;
  TriPeaksSolitaire triPeaksDefault;
  TriPeaksSolitaire triPeaksOneCard;
  TriPeaksSolitaire triPeaks8Rows;
  TriPeaksSolitaire triPeaks3Rows;
  List<Card> deck3Rows;
  List<Card> deck4Rows1Blank;
  List<Card> fullDeck;
  List<Card> deckWin;
  List<Card> deck2CardsRemoved;
  List<Card> deckLose;
  List<Card> deckLowScore;
  List<Card> deck3RowsSkipDraw;
  List<Card> deckExample;
  List<Card> deckFirstCardBlank;
  PyramidSolitaireTextualView viewDefault;
  PyramidSolitaireTextualView view3Rows;
  PyramidSolitaireTextualView view4Rows1Blank;
  PyramidSolitaireTextualView view7Rows;
  PyramidSolitaireTextualView view7Rows3Draw;
  PyramidSolitaireTextualView viewWin;
  PyramidSolitaireTextualView viewLose;
  PyramidSolitaireTextualView view2CardsRemoved;
  PyramidSolitaireTextualView viewLowScore;
  PyramidSolitaireTextualView viewBlankDraw;
  PyramidSolitaireTextualView view3RowsSkipDraw;
  PyramidSolitaireTextualView viewExample;
  PyramidSolitaireTextualView viewBlankFirstCard;
  PyramidSolitaireTextualView view7RowsOutputError;
  PyramidSolitaireTextualView view7RowsAppendable;
  PyramidSolitaireTextualView viewDefaultTriPeaks;
  PyramidSolitaireTextualView viewOneCardTriPeaks;
  PyramidSolitaireTextualView viewTriPeaks8Rows;
  PyramidSolitaireTextualView viewTriPeaks3Rows;
  PyramidSolitaireTextualView viewRelaxed7Rows;
  Appendable ap7Rows;

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
    view3Rows = new PyramidSolitaireTextualView(model3Rows);
    modelBlankDraw = new BasicPyramidSolitaire(deck3Rows, 3, 3);
    viewBlankDraw = new PyramidSolitaireTextualView(modelBlankDraw);

    Card[] cards4Rows1Blank = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE), null, null, null, null,
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck4Rows1Blank = new ArrayList<Card>(Arrays.asList(cards4Rows1Blank));
    model4Rows1Blank = new BasicPyramidSolitaire(deck4Rows1Blank, 4, 2);
    view4Rows1Blank = new PyramidSolitaireTextualView(model4Rows1Blank);

    Card[] cardsWin = {null, null, null, null, null, null, new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.TWO)};
    deckWin = new ArrayList<Card>(Arrays.asList(cardsWin));
    modelWin = new BasicPyramidSolitaire(deckWin, 3, 3);
    viewWin = new PyramidSolitaireTextualView(modelWin);

    Card[] cardsLose = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)};
    deckLose = new ArrayList<Card>(Arrays.asList(cardsLose));
    modelLose = new BasicPyramidSolitaire(deckLose, 3, 3);
    viewLose = new PyramidSolitaireTextualView(modelLose);

    Card[] cardsLowScore = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TWO),
        new Card(Suit.CLUB, Rank.THREE)};
    deckLowScore = new ArrayList<Card>(Arrays.asList(cardsLowScore));
    modelLowScore = new BasicPyramidSolitaire(deckLowScore, 3, 2);
    viewLowScore = new PyramidSolitaireTextualView(modelLowScore);

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
    model7Rows3Draw = new BasicPyramidSolitaire(fullDeck, 7, 3);
    view7Rows3Draw = new PyramidSolitaireTextualView(model7Rows3Draw);

    deck2CardsRemoved = modelDefault.getDeck();
    // 2 cards removed from bottom row
    deck2CardsRemoved.set(22, null);
    deck2CardsRemoved.set(25, null);
    model2CardsRemoved = new BasicPyramidSolitaire(deck2CardsRemoved, 7, 3);
    view2CardsRemoved = new PyramidSolitaireTextualView(model2CardsRemoved);

    Card[] cardsExample = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TWO),
        new Card(Suit.CLUB, Rank.THREE), new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.FIVE), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.HEART, Rank.TEN), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.JACK), new Card(Suit.CLUB, Rank.QUEEN),
        new Card(Suit.CLUB, Rank.KING), new Card(Suit.DIAMOND, Rank.ACE),
        new Card(Suit.DIAMOND, Rank.TWO), new Card(Suit.DIAMOND, Rank.THREE),
        new Card(Suit.DIAMOND, Rank.FOUR), null, new Card(Suit.DIAMOND, Rank.SIX),
        new Card(Suit.DIAMOND, Rank.SEVEN), new Card(Suit.DIAMOND, Rank.EIGHT),
        new Card(Suit.DIAMOND, Rank.NINE), new Card(Suit.DIAMOND, Rank.TEN), null, null,
        new Card(Suit.DIAMOND, Rank.KING), new Card(Suit.HEART, Rank.ACE), null,
        new Card(Suit.HEART, Rank.FIVE)};
    deckExample = new ArrayList<Card>(Arrays.asList(cardsExample));
    modelExample = new BasicPyramidSolitaire(deckExample, 7, 1);
    viewExample = new PyramidSolitaireTextualView(modelExample);

    Card[] cardsFirstBlank = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), null, new Card(Suit.CLUB, Rank.SEVEN),
        new Card(Suit.CLUB, Rank.NINE), new Card(Suit.CLUB, Rank.FOUR),
        new Card(Suit.CLUB, Rank.THREE)};
    deckFirstCardBlank = new ArrayList<Card>(Arrays.asList(cardsFirstBlank));
    modelBlankFirstCard = new BasicPyramidSolitaire(deckFirstCardBlank, 3, 2);
    viewBlankFirstCard = new PyramidSolitaireTextualView(modelBlankFirstCard);
    
    triPeaksDefault = new TriPeaksSolitaire();
    triPeaksDefault.startGame(triPeaksDefault.getDeck(), false, 7, 3);
    viewDefaultTriPeaks = new PyramidSolitaireTextualView(triPeaksDefault);
    
    triPeaksOneCard = new TriPeaksSolitaire();
    triPeaksOneCard.startGame(triPeaksOneCard.getDeck(), false, 1, 3);
    viewOneCardTriPeaks = new PyramidSolitaireTextualView(triPeaksOneCard);

    triPeaks8Rows = new TriPeaksSolitaire();
    triPeaks8Rows.startGame(triPeaksDefault.getDeck(), false, 8, 3);
    viewTriPeaks8Rows = new PyramidSolitaireTextualView(triPeaks8Rows);
    triPeaks3Rows = new TriPeaksSolitaire();
    triPeaks3Rows.startGame(triPeaksDefault.getDeck(), false, 3, 3);
    viewTriPeaks3Rows = new PyramidSolitaireTextualView(triPeaks3Rows);
    
    ap7Rows = new StringBuilder();
    view7RowsAppendable = new PyramidSolitaireTextualView(model7Rows3Draw, ap7Rows);
    view7RowsOutputError = new PyramidSolitaireTextualView(model7Rows, new ErrorAppendable());
    
    modelRelaxed7Rows = new RelaxedPyramidSolitaire();
    modelRelaxed7Rows.startGame(modelRelaxed7Rows.getDeck(), false, 7, 3);
    viewRelaxed7Rows = new PyramidSolitaireTextualView(modelRelaxed7Rows); 
  }

  // Test unstarted game
  @Test
  public void testUnstartedGameToString() {
    assertEquals("", viewDefault.toString());
  }

  // Test game with 3 rows of cards and 2 draw cards
  @Test
  public void testToString3Rows() {
    String expected = ""
        + "    A♣\n"
        + "  10♣ 2♣\n"
        + "6♣  7♣  9♣\n"
        + "Draw: 4♣, 3♣";
    assertEquals(expected, view3Rows.toString());
  }

  // Test a game where the first card in a row is blank
  @Test
  public void testToStringBlankFirstCard() {
    String expected = ""
        + "    A♣\n"
        + "  10♣ 2♣\n"
        + "    7♣  9♣\n"
        + "Draw: 4♣, 3♣";
    assertEquals(expected, viewBlankFirstCard.toString());
  }

  // Test game with 3 rows of cards and 3 draw cards with a blank draw card
  @Test
  public void testToString3RowsSkipDraw() {
    String expected = "    A♣\n" + "  10♣ 2♣\n" + "6♣  7♣  9♣\n" + "Draw: 4♣, 3♣";
    assertEquals(expected, view3RowsSkipDraw.toString());
  }

  // Test game with a blank card in draw
  @Test
  public void testBlankCard() {
    String expected = "    A♣\n" + "  10♣ 2♣\n" + "6♣  7♣  9♣\n" + "Draw: 4♣, 3♣";
    assertEquals(expected, viewBlankDraw.toString());
  }

  // Test game with a blank row
  @Test
  public void testToString4Rows1Blank() {
    String expected = "      A♣\n" + "    10♣ 2♣\n" + "  6♣  7♣  9♣\n" + "\n" + "Draw: 4♣, 3♣";
    assertEquals(expected, view4Rows1Blank.toString());
  }

  // Test game with 7 rows and no draw cards
  @Test
  public void testToString7Rows() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n" + "Draw:";
    assertEquals(expected, view7Rows.toString());
  }

  // Test game with 7 rows and 3 draw cards
  @Test
  public void testToString7Rows3Draw() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n" + "Draw: 3♥, 4♥, 5♥";

    assertEquals(expected, view7Rows3Draw.toString());
  }

  // Test relaxed game with 7 rows and 3 draw cards
  @Test
  public void testToStringRelaxed7Rows3Draw() {
    String expected = "" 
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥";

    assertEquals(expected, viewRelaxed7Rows.toString());
  }

  // Test game with 2 cards removed
  @Test
  public void testToString2CardsRemoved() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦      J♦  Q♦      A♥  2♥\n" + "Draw: 3♥, 4♥, 5♥";

    assertEquals(expected, view2CardsRemoved.toString());
  }

  // Test the example in the assignment
  @Test
  public void testExample() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  10♥ 10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦      6♦  7♦  8♦\n"
        + "9♦  10♦         K♦  A♥\n" + "Draw: 5♥";
    assertEquals(expected, viewExample.toString());
  }
  
  // test a default triPeaks game
  @Test
  public void testDefaultTriPeaks() {
    String expected = ""
        + "            A♣          2♣          3♣\n"
        + "          4♣  5♣      6♣  7♣      8♣  9♣\n"
        + "        10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n"
        + "      6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "    3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\n"
        + "  A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n"
        + "K♠  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n"
        + "Draw: K♣, A♦, 2♦";
    assertEquals(expected, viewDefaultTriPeaks.toString());
  }

  // test a triPeaks game with 8 rows
  @Test
  public void test8RowsTriPeaks() {
    String expected = ""
        + "              A♣              2♣              3♣\n"
        + "            4♣  5♣          6♣  7♣          8♣  9♣\n"
        + "          10♣ J♣  Q♣      K♣  A♦  2♦      3♦  4♦  5♦\n" 
        + "        6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥  3♥  4♥\n" 
        + "      5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥  A♠  2♠  3♠  4♠\n" 
        + "    5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠  K♠  A♣  2♣  3♣  4♣  5♣\n" 
        + "  6♣  7♣  8♣  9♣  10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦  6♦  7♦\n" 
        + "8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n" 
        + "Draw: J♥, Q♥, K♥";
    assertEquals(expected, viewTriPeaks8Rows.toString());
  }

  // test a triPeaks game with 3 rows
  @Test
  public void test3RowsTriPeaks() {
    String expected = ""
        + "    A♣  2♣  3♣\n"
        + "  4♣  5♣  6♣  7♣\n"
        + "8♣  9♣  10♣ J♣  Q♣\n"
        + "Draw: K♣, A♦, 2♦";
    assertEquals(expected, viewTriPeaks3Rows.toString());
  }

  // test a triPeaks game with 3 rows
  @Test
  public void testOneCardTriPeaks() {
    String expected = "A♣\n" 
        + "Draw: 2♣, 3♣, 4♣";
    assertEquals(expected, viewOneCardTriPeaks.toString());
  }

  // Test winning game
  @Test
  public void testGameWonToString() {
    assertEquals("You win!", viewWin.toString());
  }

  // Test losing game
  @Test
  public void testGameLostToString() {
    assertEquals("Game over. Score: 39", viewLose.toString());
    assertEquals("Game over. Score: 6", viewLowScore.toString());
  }

  // Test passing a null to the constructor
  @Test(expected = IllegalArgumentException.class)
  public void testNullConstructor() {
    PyramidSolitaireTextualView invalid = new PyramidSolitaireTextualView(null);
    assertEquals(invalid.toString(), "");
  }

  //Test passing a null model to the 2-parameter constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullModelConstructor() {
    new PyramidSolitaireTextualView(null, ap7Rows);
  }

  // Test passing a null Appendable to the constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendableConstructor() {
    new PyramidSolitaireTextualView(model7Rows, null);
  }

  // Test that IOException received if error writing to Appendable.
  @Test(expected = IOException.class)
  public void testIoException() throws IOException {
    view7RowsOutputError.render();
  }

  // Test that the render method writes to an Appendable.
  @Test
  public void testRender() {
    String expected = "            A♣\n" + "          2♣  3♣\n" + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n" + "    J♣  Q♣  K♣  A♦  2♦\n" + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n" + "Draw: 3♥, 4♥, 5♥";
    try {
      view7RowsAppendable.render();
      assertEquals(expected, ap7Rows.toString());
    } catch (IOException e) {
      fail("IOException rendering view");
    }
  }
  
  // Test that IOException received if render called when view created
  // without an appendable
  @Test(expected = IOException.class)
  public void testNoAppendable() throws IOException {
    viewLose.render();
  }


  /**
   * Represents an Appendable that throws an IOException.
   */
  static class ErrorAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException("Unable to append");
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException("Unable to append");
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException("Unable to append");
    }

  }

}
