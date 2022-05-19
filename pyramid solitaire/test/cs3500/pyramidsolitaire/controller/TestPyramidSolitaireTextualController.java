package cs3500.pyramidsolitaire.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Card.Rank;
import cs3500.pyramidsolitaire.model.hw02.Card.Suit;
import cs3500.pyramidsolitaire.model.hw02.ConfirmInputsSolitaireModel;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the private methods of PyramidSolitaireTextualController.
 */
public class TestPyramidSolitaireTextualController {

  /**
   * objects to use for testing.
   */
  PyramidSolitaireTextualController controllerStarted;
  PyramidSolitaireTextualController controllerOutputError;
  PyramidSolitaireTextualController controllerQuit;
  PyramidSolitaireTextualController controllerWin;
  PyramidSolitaireTextualController controllerLose;
  PyramidSolitaireTextualController controllerActive;
  PyramidSolitaireTextualController controllerInvCmd;
  BasicPyramidSolitaire model;
  BasicPyramidSolitaire modelLose;
  BasicPyramidSolitaire modelWin;
  BasicPyramidSolitaire modelStarted;
  List<Card> deck;
  List<Card> deckWin;
  List<Card> deckLose;
  Appendable apLose;
  Appendable apWin;
  Appendable apActive;
  Appendable apError;
  Appendable apStarted;
  Appendable apQuit;
  Appendable apInvCmd;  
  Scanner emptyScanner;
  
  @Before
  public void setupTestFixture() {
    model = new BasicPyramidSolitaire();
    deck = model.getDeck();
    modelStarted = new BasicPyramidSolitaire();
    modelStarted.startGame(deck, false, 7, 3);
    
    emptyScanner = new Scanner(new StringReader(""));
    
    apStarted = new StringBuilder();
    controllerStarted = new PyramidSolitaireTextualController(new StringReader(""), 
        apStarted, modelStarted);
    
    apError = new ErrorAppendable();
    controllerOutputError = new PyramidSolitaireTextualController(new StringReader("q"), 
        apError, modelStarted);
    
    apQuit = new StringBuilder();
    controllerQuit = new PyramidSolitaireTextualController(new StringReader("q"), 
        apQuit, modelStarted);
    
    Card[] cardsLose = {new Card(Suit.CLUB, Rank.ACE), new Card(Suit.CLUB, Rank.TEN),
        new Card(Suit.CLUB, Rank.TWO), new Card(Suit.CLUB, Rank.EIGHT),
        new Card(Suit.CLUB, Rank.SEVEN), new Card(Suit.CLUB, Rank.JACK)};
    deckLose = new ArrayList<Card>(Arrays.asList(cardsLose));
    modelLose = new BasicPyramidSolitaire(deckLose, 3, 3);
    apLose = new StringBuilder();
    controllerLose = new PyramidSolitaireTextualController(new StringReader(""), apLose, modelLose);

    Card[] cardsWin = {null, null, null, null, null, null, new Card(Suit.CLUB, Rank.ACE),
        new Card(Suit.CLUB, Rank.TEN), new Card(Suit.CLUB, Rank.TWO)};
    deckWin = new ArrayList<Card>(Arrays.asList(cardsWin));
    modelWin = new BasicPyramidSolitaire(deckWin, 3, 3);
    apWin = new StringBuilder();
    controllerWin = new PyramidSolitaireTextualController(new StringReader(""), apWin, modelWin);
    
    apActive = new StringBuilder();
    controllerActive = new PyramidSolitaireTextualController(new StringReader(""), 
        apActive, modelStarted);
    
    apInvCmd = new StringBuilder();
    controllerInvCmd = new PyramidSolitaireTextualController(new StringReader("xyz q"), apInvCmd);
  }
  
  // Test the run method for exception when no more input
  @Test(expected = IllegalStateException.class)
  public void testRunInputException() {
    controllerStarted.run();
  }
  
  // test the run method when the game has started
  @Test
  public void testRunGameStart() {
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
      controllerStarted.run();
    } catch (IllegalStateException e) {
      // do nothing. there is no more input
      // we just want to see how the method behaves upon starting the game
    }
    assertEquals(expected, apStarted.toString());
  }
  
  // test the run method when the game has been quit
  @Test
  public void testRunGameQuit() {
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
    controllerQuit.run();
    assertEquals(expected, apQuit.toString());
  }
  
  // test the run method when the game is over
  @Test
  public void testRunGameOver() {
    String expectedWin = "You win!\n";
    controllerWin.run();
    assertEquals(expectedWin, apWin.toString());
    String expectedLose = "Game over. Score: 39\n";
    controllerLose.run();
    assertEquals(expectedLose, apLose.toString());
  }
  
  // test that processInput throws the appropriate exception when input can't be read
  @Test(expected = NoSuchElementException.class) 
  public void testProcessInputUnreadable() {
    controllerStarted.processInput(emptyScanner);
  }
  
  // test the scanValues method 
  @Test
  public void testScanValues() {
    assertFalse(controllerStarted.scanValues(new Scanner(new StringReader("1 2 q 4")), 
        4, new ArrayList<Integer>())); 
    assertFalse(controllerStarted.scanValues(new Scanner(new StringReader("q 2 3 4")), 
        4, new ArrayList<Integer>())); 
    assertFalse(controllerStarted.scanValues(new Scanner(new StringReader("1 2 3 Q")), 
        4, new ArrayList<Integer>())); 
    scanValuesSetup("1 2 3 4", 4, new Integer[] {0, 1, 2, 3});
    scanValuesSetup("6 x 3", 2, new Integer[] {5, 2});
    scanValuesSetup("1 3 5 7", 2, new Integer[] {0, 2});
  }
  
  // test append
  @Test
  public void testAppend() {
    String expected = "hello";
    controllerStarted.append("hello");
    assertEquals(expected, apStarted.toString());
  }
  
  // test that append throws the appropriate error
  @Test(expected = IllegalStateException.class)
  public void testAppendError() {
    controllerOutputError.append("");
  }
  
  // test trasmitGameState when the game is won
  @Test
  public void testTrasmitGameStateWon() {
    String expected = "You win!\n";
    controllerWin.transmitGameState();
    assertEquals(expected, apWin.toString());
  }
  
  // test transmitGameState when the game is lot
  @Test
  public void testTrasmitGameStateLost() {
    String expected = "Game over. Score: 39\n";
    controllerLose.transmitGameState();
    assertEquals(expected, apLose.toString());
  }
  
  // test trasmitGameState when the game is active
  @Test
  public void testTrasmitGameStateActive() {
    String expected = ""
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n";
    controllerActive.transmitGameState();
    assertEquals(expected, apActive.toString()); 
  }
  
  // test the processInput method
  @Test
  public void testProcessInput() {
    setupTestInput("rm2 7 1 5 3\n", "remove(6, 0, 4, 2)\n");
    setupTestInput("rm2 7 5 3 1\n", "remove(6, 4, 2, 0)\n");
    setupTestInput("rm1 5 3\n", "remove(4, 2)\n");
    setupTestInput("rm1 5 x 3\nq", "remove(4, 2)\n");
    setupTestInput("rmwd 1 7 2 1\n", "removeUsingDraw(0, 6, 1)\n");
    setupTestInput("rmwd 2 4 3 1\n", "removeUsingDraw(1, 3, 2)\n");
    setupTestInput("dd 1", "discardDraw(0)\n");
    setupTestInput("dd 2", "discardDraw(1)\n");
    // Test with 'q' inserted in parameter list
    setupTestInput("rm2 7 1 q 3\n", "");
    setupTestInput("rm1 q 3\n", "");
    setupTestInput("rmwd 2 4 q\n", "");
    setupTestInput("dd q", "");
  }
  
  // test that invalid commands output an error message.
  @Test
  public void testInvalidCommand() {
    controllerInvCmd.playGame(modelStarted, deck, false, 7, 3);
    assertTrue(apInvCmd.toString().contains("Invalid command:"));
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
    Scanner scanner = new Scanner(fakeUserInput);
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ConfirmInputsSolitaireModel inputsModel = new ConfirmInputsSolitaireModel(log);
    PyramidSolitaireTextualController controller =
        new PyramidSolitaireTextualController(fakeUserInput, dontCareOutput, inputsModel);
    controller.processInput(scanner);
    assertEquals(ex, log.toString());
  }
  
  /**
   * Runs a test on scanValues for one input string. Groups all the details of 
   * setting up and running a test.
   * @param input  the string input.
   * @param count  the number of inputs that we want.
   * @param values the expected parameter values as a list of Integers.
   */
  void scanValuesSetup(String input, int count, Integer[] values) {
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(values));
    ArrayList<Integer> result = new ArrayList<>();
    assertTrue(controllerStarted.scanValues(new Scanner(new StringReader(input)), count, result));
    assertEquals(expected, result);
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
