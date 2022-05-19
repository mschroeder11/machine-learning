import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.ConfirmInputsSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * test the public methods for the TextualController class.
 */
public class TestTextualController {
  
  /**
   * objects for tests.
   */
  private static final int DECK_SIZE = 52;
  private static final int DECK_SIZE2 = 104;

  Appendable ap1;
  Appendable apMove1;
  Appendable apTriPeaksMove1;
  Appendable apError;
  Appendable ap1MoveLose;
  Appendable ap1MoveWin;
  Appendable apRelaxed1MoveWin;
  Appendable apTriPeaksLose;
  Readable emptyReader;
  List<Card> deck;
  List<Card> deckWin;
  List<Card> deckLose;
  List<Card> deck1MoveWin;
  List<Card> deck1MoveLose;
  List<Card> deckRelaxed1MoveWin;
  List<Card> deckTriPeaksLose;
  BasicPyramidSolitaire model;
  RelaxedPyramidSolitaire modelRelaxed;
  TriPeaksSolitaire modelTriPeaks;
  PyramidSolitaireTextualView view;
  PyramidSolitaireTextualController controller1MoveWin; 
  PyramidSolitaireTextualController controller1MoveLose;  
  PyramidSolitaireTextualController controller1;
  PyramidSolitaireTextualController controllerMove1;
  PyramidSolitaireTextualController controllerError;
  PyramidSolitaireTextualController controllerRelaxed1MoveWin;
  PyramidSolitaireTextualController controllerTriPeaksMove1;
  PyramidSolitaireTextualController controllerTriPeaksLose;
  
  Appendable apRelaxed5MoveWin;
  List<Card> deckRelaxed5MoveWin;
  PyramidSolitaireTextualController controllerRelaxed5MoveWin;
  
  Appendable apRelaxedLose;
  List<Card> deckRelaxedLose;
  PyramidSolitaireTextualController controllerRelaxedLose;
  
  /**
   * initialize testing variables.
   */
  @Before
  public void testSetupFixture() {
    ap1 = new StringBuilder();
    emptyReader = new StringReader("");
    controller1 = new PyramidSolitaireTextualController(emptyReader, ap1);
    apMove1 = new StringBuilder();
    controllerMove1 = new PyramidSolitaireTextualController(new StringReader("rm1 7 5"), apMove1);
    apError = new ErrorAppendable();
    controllerError = new PyramidSolitaireTextualController(new StringReader("q"), apError);
    model = new BasicPyramidSolitaire();
    modelRelaxed = new RelaxedPyramidSolitaire();
    deck = model.getDeck();
    Card[] cardsWin = {null, null, null, null, null, null, new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.TWO)};
    deckWin = completeDeck(new ArrayList<Card>(Arrays.asList(cardsWin)), DECK_SIZE);
    Card[] cardsLose = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)};
    deckLose = completeDeck(new ArrayList<Card>(Arrays.asList(cardsLose)),  DECK_SIZE);
    Card[] cards1MoveWin = {new Card(Suit.CLUB, Rank.KING), null, null, null, null, null,
        new Card(Suit.CLUB, Rank.FOUR), new Card(Suit.CLUB, Rank.THREE)};
    deck1MoveWin = completeDeck(Arrays.asList(cards1MoveWin), DECK_SIZE);
    ap1MoveWin = new StringBuilder();
    controller1MoveWin =
        new PyramidSolitaireTextualController(new StringReader("rm1 1 x 1"), ap1MoveWin);

    Card[] cards1MoveLose = {new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.NINE), null,
        null, null, null, new Card(Suit.CLUB, Rank.FOUR)};
    deck1MoveLose = completeDeck(Arrays.asList(cards1MoveLose), DECK_SIZE);
    ap1MoveLose = new StringBuilder();
    controller1MoveLose = new PyramidSolitaireTextualController(new StringReader("rmwd 1 2 1"),
        ap1MoveLose);

    Card[] cardsRelaxed1MoveWin = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.QUEEN)};
    deckRelaxed1MoveWin = completeDeck(Arrays.asList(cardsRelaxed1MoveWin), DECK_SIZE);
    apRelaxed1MoveWin = new StringBuilder();
    controllerRelaxed1MoveWin =
        new PyramidSolitaireTextualController(new StringReader("rm2 1 1 2 1"), apRelaxed1MoveWin);
    
    apTriPeaksMove1 = new StringBuilder();
    modelTriPeaks = new TriPeaksSolitaire();
    controllerTriPeaksMove1 =
        new PyramidSolitaireTextualController(new StringReader("rm1 7 1 q"), apTriPeaksMove1);
    
    Card[] cardsRelaxed5MoveWin = {new Card(Suit.CLUB, Rank.QUEEN), new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.SEVEN), null,
        new Card(Suit.CLUB, Rank.JACK), new Card(Suit.CLUB, Rank.SIX),
        new Card(Suit.CLUB, Rank.EIGHT), new Card(Suit.CLUB, Rank.FIVE),
        new Card(Suit.CLUB, Rank.KING)};
    apRelaxed5MoveWin = new StringBuilder();
    deckRelaxed5MoveWin = completeDeck(Arrays.asList(cardsRelaxed5MoveWin), DECK_SIZE);
    StringReader reader5Moves =
        new StringReader("rm1 4 4 rm2 4 2 4 3 rm2 4 1 3 1 rm2 3 3 2 2 rm2 1 1 2 1");
    controllerRelaxed5MoveWin =
        new PyramidSolitaireTextualController(reader5Moves, apRelaxed5MoveWin);
    
    Card[] cardsRelaxedLose = {new Card(Suit.CLUB, Rank.QUEEN), new Card(Suit.CLUB, Rank.ACE),
        null, new Card(Suit.CLUB, Rank.SEVEN)};
    apRelaxedLose = new StringBuilder();
    deckRelaxedLose = completeDeck(Arrays.asList(cardsRelaxedLose), DECK_SIZE);
    StringReader readerRelaxedLose =
        new StringReader("rm2 1 1 2 1");
    controllerRelaxedLose =
        new PyramidSolitaireTextualController(readerRelaxedLose, apRelaxedLose);
    

    Card[] cardsTriPeaksLose = {new Card(Suit.CLUB, Rank.QUEEN), new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.SEVEN), null, null, null, null, new Card(Suit.CLUB, Rank.FIVE),
        new Card(Suit.CLUB, Rank.FOUR)};
    apTriPeaksLose = new StringBuilder();
    deckTriPeaksLose = completeDeck(Arrays.asList(cardsTriPeaksLose), DECK_SIZE2);
    StringReader readerTriPeaksLose =
        new StringReader("rm2 1 1 1 4");
    controllerTriPeaksLose =
        new PyramidSolitaireTextualController(readerTriPeaksLose, apTriPeaksLose);
    
  }

  // test that a null readable throws an exception
  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    PyramidSolitaireTextualController nullcontroller = 
        new PyramidSolitaireTextualController(null, ap1);
  }

  // test that a null appendable throws an exception
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    PyramidSolitaireTextualController nullcontroller = 
        new PyramidSolitaireTextualController(emptyReader, null);
  }

  // test that playGame throws an IllegalArgumentException if model is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    controller1.playGame(null, deck, false, 7, 3);
  }

  // Test that controller throws exception on when game cannot be started
  @Test(expected = IllegalStateException.class)
  public void testPlayGameStartException() {
    controller1.playGame(model, null, true, 7, 3);
  }

  // test that the inputs are read properly into the model
  @Test
  public void testInputs() {
    setupTestInput("rm2 7 1 5 3 q", "remove(6, 0, 4, 2)\n");
    setupTestInput("rm2 7 5 3 1 q", "remove(6, 4, 2, 0)\n");
    setupTestInput("rm1 5 3 q", "remove(4, 2)\n");
    setupTestInput("rm1 5 x 3 q", "remove(4, 2)\n");
    setupTestInput("rmwd 1 7 2 1 q", "removeUsingDraw(0, 6, 1)\n");
    setupTestInput("rmwd 2 4 3 1 q", "removeUsingDraw(1, 3, 2)\n");
    setupTestInput("dd 1 q", "discardDraw(0)\n");
    setupTestInput("dd 2 q", "discardDraw(1)\n");
    // Test with 'q' inserted in parameter list
    setupTestInput("rm2 7 1 q 3\n", "");
    setupTestInput("rm1 q 3\n", "");
    setupTestInput("rmwd 2 4 q\n", "");
    setupTestInput("dd q", "");
  }
  
  // test that invalid commands get rejected properly
  @Test
  public void testInvalidInputs() {
    setupTestInvalidInput("cc1", "\nInvalid command: cc1\n");
    setupTestInvalidInput("rm3", "\nInvalid command: rm3\n");
    setupTestInvalidInput("rmwx", "\nInvalid command: rmwx\n");
  }

  // test that performing a move updates the game state
  @Test
  public void testGameMove1() {
    String expected = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦      A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n";
    try {
      controllerMove1.playGame(model, deck, false, 7, 3);
    } catch (IllegalStateException e) {
      // do nothing. this is intentional.
      // we just want to see the game state that is rendered after this move
    }

    assertEquals(expected, apMove1.toString());
  }

  @Test
  public void testPlayGameNoMoves() {
    String expected = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n";
    try {
      // play the game, expect the exception because there was no input
      controller1.playGame(model, deck, false, 7, 3);
    } catch (IllegalStateException e) {
      // Do nothing. This happens because there is no more input,
      // which is intentional. We just want to see the game state rendered
      // as part of the game's play loop.
    }
    // Test that the string in the Appendable matches what we expected
    assertEquals(expected, ap1.toString());
  }
  
  // test that shuffled properly passed into the model
  @Test
  public void testPlayGameShuffle() {
    String expected = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n";
    try {
      // play the game, expect the exception because there was no input
      controller1.playGame(model, deck, true, 7, 3);
    } catch (IllegalStateException e) {
      // Do nothing. This happens because there is no more input,
      // which is intentional. We just want to see the game state rendered
      // as part of the game's play loop.
    }
    // Test that the string in the Appendable matches what we expected
    assertNotEquals(expected, ap1.toString());
  }

  // test the playGame method after the user quits
  @Test
  public void testGameQuit() {
    String expected = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n";
    String[] quitCommands = {"q", "Q", "rm2 q 1 2 3", "rm2 1 q 3 4", "rm2 1 2 3 q"};
    PyramidSolitaireController controller;
    for (String s : quitCommands) {
      Appendable ap = new StringBuilder();
      controller = new PyramidSolitaireTextualController(new StringReader(s), ap);
      controller.playGame(model, deck, false, 7, 3);
      assertEquals(expected, ap.toString());
    }
  }

  // test that an empty input throws an error
  @Test(expected = IllegalStateException.class)
  public void testNoInputError() {
    controller1.playGame(model, deck, false, 7, 3);
  }

  // test that the appendable throws the appropriate error
  @Test(expected = IllegalStateException.class)
  public void testAppendError() {
    controllerError.playGame(model, deck, false, 7, 3);
  }
  
  // test that the game state updates after a winning move 
  @Test
  public void testGameWin1Move() {
    String expected = ""
        + "    K♣\n"
        + "\n"
        + "\n"
        + "Draw: 4♣, 3♣\n"
        + "Score: 13\n"
        + "x is not a valid input. please enter a number: \n"
        + "You win!\n";
    controller1MoveWin.playGame(model, deck1MoveWin, false, 3, 3);
    assertEquals(expected, ap1MoveWin.toString());
  }
  
  // test that the game state updates after a losing move
  @Test
  public void testGameLose1Move() {
    String expected = ""
        + "    7♣\n"
        + "  9♣\n"
        + "\n"
        + "Draw: 4♣\n"
        + "Score: 16\n"
        + "Game over. Score: 7\n";
    controller1MoveLose.playGame(model, deck1MoveLose, false, 3, 3);
    assertEquals(expected, ap1MoveLose.toString());
  }
  
  // test that the controller outputs the right thing when the model receives an invalid parameter
  @Test
  public void testInvalidParam() {
    // Commands to test. Commands should end with a 'q' command so the game will
    // end gracefully and not throw an exception.
    String[] commands = {
        "rmwd 1 8 3 q", "rmwd 4 4 3 q", "rmwd 1 4 5 q", "rmwd -1 4 5 q",
        "rmwd 1 -1 5 q", "dd 4 q", "dd -1 q", "rm2 8 1 3 2 q", "rm2 -1 1 3 2 q", "rm2 6 7 3 2 q",
        "rm2 6 -1 3 2 q", "rm2 6 5 8 2 q", "rm2 6 5 -1 2 q", "rm2 6 6 3 4 q", "rm2 6 6 3 -1 q",
        "rm1 8 1  q", "rm1 2 3 q", "rm1 -1 3 q", "rm1 4 -1 q"
    };
    String expected = "Invalid move. Play again. ";
    for (String c : commands) {
      Appendable ap = new StringBuilder();
      PyramidSolitaireController controller = 
          new PyramidSolitaireTextualController(new StringReader(c), ap);
      controller.playGame(model, deck, false, 7, 3);
      assertTrue("Output should contain: " + expected, ap.toString().contains(expected));
    }
  }
  
  // tests that the controller does the correct output when the game is won
  @Test
  public void testGameWon() {
    String expected = "You win!\n";
    controller1.playGame(model, deckWin, false, 3, 3);
    assertEquals(expected, ap1.toString());
  }
  
  // tests that the controller does the correct output when the game is lost
  @Test
  public void testGameLost() {
    String expected = "Game over. Score: 39\n";
    controller1.playGame(model, deckLose, false, 3, 3);
    assertEquals(expected, ap1.toString());
  }
  
  // test that the controller properly reads in input after invalid inputs
  @Test
  public void testInvalidValues() {
    setupInvalidValues("rm2 7 5 x x 3 1 q", "remove(6, 4, 2, 0)\n");
    setupInvalidValues("rm2 7 x x x 3 1 1 q", "remove(6, 2, 0, 0)\n");
    setupInvalidValues("rmwd 2 4 x x x 3 q", "removeUsingDraw(1, 3, 2)\n");
    setupInvalidValues("rmwd 2 4 x rm2 x 3 q", "removeUsingDraw(1, 3, 2)\n");
    setupInvalidValues("dd x 2 q", "discardDraw(1)\n");
    setupInvalidValues("rm1 5 x x x 3 q", "remove(4, 2)\n");
    setupInvalidValues("rm1 5 rm1 dd x 3 q", "remove(4, 2)\n");
  }

  // test that the game state updates after a winning move for relaxed game
  @Test
  public void testRelaxedGameWin1Move() {
    String expected = ""
        + "    A♣\n"
        + "  Q♣\n"
        + "\n"
        + "Draw:\n"
        + "Score: 13\n"
        + "You win!\n";
    controllerRelaxed1MoveWin.playGame(modelRelaxed, deckRelaxed1MoveWin, false, 3, 3);
    assertEquals(expected, apRelaxed1MoveWin.toString());
  }
  
  // test that the game state updates after a winning move for relaxed game
  @Test
  public void testRelaxedGameWin5Move() {
    String expected = ""
        + "      Q♣\n"
        + "    A♣  2♣\n" 
        + "  7♣      J♣\n" 
        + "6♣  8♣  5♣  K♣\n" 
        + "Draw:\n" 
        + "Score: 65\n" 
        + "      Q♣\n" 
        + "    A♣  2♣\n" 
        + "  7♣      J♣\n" 
        + "6♣  8♣  5♣\n"
        + "Draw:\n" 
        + "Score: 52\n" 
        + "      Q♣\n" 
        + "    A♣  2♣\n" 
        + "  7♣      J♣\n" 
        + "6♣\n"
        + "Draw:\n" 
        + "Score: 39\n" 
        + "      Q♣\n" 
        + "    A♣  2♣\n" 
        + "          J♣\n" 
        + "\n" 
        + "Draw:\n" 
        + "Score: 26\n"
        + "      Q♣\n"
        + "    A♣\n"
        + "\n"
        + "\n"
        + "Draw:\n"
        + "Score: 13\n"
        + "You win!\n";
    controllerRelaxed5MoveWin.playGame(modelRelaxed, deckRelaxed5MoveWin, false, 4, 3);
    assertEquals(expected, apRelaxed5MoveWin.toString());
  }
  
  // test losing relaxed game with a card covering the possible relaxed move.
  @Test
  public void testRelaxedGameLose() {
    String expected = "Game over. Score: 20\n";
    controllerRelaxedLose.playGame(modelRelaxed, deckRelaxedLose, false, 3, 3);
    assertEquals(expected, apRelaxedLose.toString());
  }
  
  // test that the game state updates after a losing move.
  @Test
  public void testTriPeaksLose1Move() {
    String expected = ""
        + "            Q♣          A♣          7♣\n"
        + "                                  5♣  4♣\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "Draw:\n"
        + "Score: 29\n"
        + "Game over. Score: 16\n";
    controllerTriPeaksLose.playGame(modelTriPeaks, deckTriPeaksLose, false, 7, 3);
    assertEquals(expected, apTriPeaksLose.toString());
  }
  
  // test that performing a move updates the TriPeaks game state.
  @Test
  public void testGameTriPeaksMove1() {
    String expected = ""
        + "Game quit!\n" 
        + "State of game when quit:\n" 
        + "            A♣          2♣          3♣\n" 
        + "          4♣  5♣      6♣  7♣      8♣  9♣\n" 
        + "        10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n" 
        + "      6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n" 
        + "    3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\n" 
        + "  A♠  2♠  3♠  4♠  5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\n" 
        + "    A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n" 
        + "Draw: K♣, A♦, 2♦\n" 
        + "Score: 429\n";
    controllerTriPeaksMove1.playGame(modelTriPeaks, modelTriPeaks.getDeck(), false, 7, 3);
    assertTrue("Expected final deck after quit", apTriPeaksMove1.toString().endsWith(expected));
  }

  /**
   * Tests that the given input command produces an expected output from
   * a Mock model. This is used to test that the controller interprets commands
   * and calls the proper method in the model.
   * @param input the input string.
   * @param ex the expected string.
   */
  void setupTestInput(String input, String ex) {
    Reader fakeUserInput = new StringReader(input);
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ConfirmInputsSolitaireModel inputsModel = new ConfirmInputsSolitaireModel(log);
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(fakeUserInput, dontCareOutput);
    controller.playGame(inputsModel, deck, false, 7, 3);
    assertEquals(ex, log.toString());
  }
  
  /**
   * Tests that the given input command produces an expected output from
   * a Mock model. This is used to test that the controller interprets commands
   * and calls the proper method in the model.
   * @param input   Input to be read
   * @param ex   Expected output from mock model
   */
  void setupTestInvalidInput(String input, String ex) {
    String gameState = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n";
    Reader fakeUserInput = new StringReader(input);
    StringBuilder controlOutput = new StringBuilder(); 
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(fakeUserInput, controlOutput);
    try {
      controller.playGame(model, deck, false, 7, 3);
    } catch (IllegalStateException e) {
      // Do nothing. This happens because there is no more input,
      // which is intentional. We just want to see that the controller correctly
      // called the model.
    }
    assertEquals(gameState + ex, controlOutput.toString());
  }
  
  /**
   * Tests that the given input command produces an expected output from
   * a Mock model when invalid values are present and the controller re-tries
   * reading a value.
   * Also tests that the expected error message is presented.
   * @param input the input string.
   * @param ex the expected string.
   */
  void setupInvalidValues(String input, String ex) {
    String expected = "not a valid input.";
    Reader fakeUserInput = new StringReader(input);
    StringBuilder controlOutput = new StringBuilder(); 
    StringBuilder log = new StringBuilder();
    ConfirmInputsSolitaireModel inputsModel = new ConfirmInputsSolitaireModel(log);
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(fakeUserInput, controlOutput);
    controller.playGame(inputsModel, deck, false, 7, 3);
    assertEquals(ex, log.toString());
    assertTrue("Error message displayed for: " + input, 
        controlOutput.toString().contains(expected));
  }
  
  /**
   * Takes a partial deck of cards and fills the deck out to be 52 cards.
   * @param deck a partial deck of cards.
   * @return a complete deck of 52 cards.
   */
  List<Card> completeDeck(List<Card> deck, int size) {
    ArrayList<Card> myDeck = new ArrayList<>(deck);
    int remainder = 0;
    if (deck.size() < size) {
      remainder = size - deck.size();
      for (int i = 0; i < remainder; i++) {
        myDeck.add(null);
      }
    }
    return myDeck;
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


