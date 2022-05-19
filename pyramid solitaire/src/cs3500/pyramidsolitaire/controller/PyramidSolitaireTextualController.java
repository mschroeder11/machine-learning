package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**     
 * Represents a controller in the model-view-controller paradigm for a pyramid solitaire game.
 * The controller represents the object that handles user input and interacts with the model.
 * This controller class is text-based, receiving user input from a Readable object and directing
 * output to a textual view class via an Appendable object.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {
  private final Readable in;
  private final Appendable out;
  private PyramidSolitaireModel<?> model;
  private PyramidSolitaireTextualView view;

  /**
   * Constructor for a controller for the text-based pyramid solitaire.
   * @param in   Readable object that receives input.
   * @param out  Appendable object that writes output to the user.
   * @throws IllegalArgumentException   if either input is null.
   */
  public PyramidSolitaireTextualController(Readable in, Appendable out) 
      throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("null parameter not allowed");
    }
    this.in = in;
    this.out = out;

  }

  /**
   * this is only used for testing purposes and takes in a model
   * to create a custom Controller.
   * @param in   Readable object that receives input.
   * @param out  Appendable object that writes output to the user.
   * @param model The game of solitaire to be played.
   * @throws IllegalArgumentException if any input is null.
   */
  public PyramidSolitaireTextualController(Readable in, Appendable out, 
      PyramidSolitaireModel<?> model) throws IllegalArgumentException {
    if (in == null || out == null || model == null) {
      throw new IllegalArgumentException("null parameter not allowed");
    }
    this.in = in;
    this.out = out;
    this.model = model;
    view = new PyramidSolitaireTextualView(this.model, this.out);
  }

  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
      view = new PyramidSolitaireTextualView(model, this.out); 
    } catch (Exception e) {
      throw new IllegalStateException("Game could not be started " + e.getMessage());
    }
    try {
      run();
    } catch (IllegalStateException e) {
      throw e;
    } catch (NullPointerException e) {
      // In case there is a bug somewhere, don't let it crash the program
      append("Unexpected error: " + e.getMessage());
    }

  }

  /**
   * run the game.
   * @throws IllegalStateException if unable to trasmit input or read input.
   */
  void run() {
    Scanner scan = new Scanner(this.in);
    try {
      boolean active = true;
      while (active && !this.model.isGameOver()) {
        transmitGameState();
        append("Score: " + this.model.getScore() + "\n");
        while (true) {
          try {
            active = processInput(scan);
            break;
          } catch (NoSuchElementException e) {
            // Error from the Scanner
            throw new IllegalStateException("Error reading input");
          } catch (IllegalArgumentException e) {
            append("Invalid move. Play again. " + e.getMessage() + "\n");
          }
        }
      }
    } finally {
      scan.close();
    }
    if (this.model.isGameOver()) {
      transmitGameState();
    } else {
      append("Game quit!\n");
      append("State of game when quit:\n");
      transmitGameState();
      append("Score: " + this.model.getScore() + "\n");
    }
  }

  /**
   * scans for a given number of values. 
   * @param s  Scanner to be read from.
   * @param n  The number of values to be read.
   * @param result ArrayList to put values in.
   * @return true if the values were scanned, false if there is a 'q'.  
   * @throws NoSuchElementException if the scanner has no more input.
   */
  boolean scanValues(Scanner s, int n, ArrayList<Integer> result) {
    int idx = 0;
    while (idx < n) {
      if (s.hasNext("[qQ]")) {
        // if the user wants to quit, read the next character and return
        s.next();
        return false;
      } else if (s.hasNextInt()) {
        // if the next thing is a valid integer, scan it and add it to result
        result.add(s.nextInt() - 1);
        idx++;
      } else {
        append(s.next() + " is not a valid input. please enter a number: \n");
      }
    }
    return true;
  }

  /**
   * Processes input and parse commands + associated values to execute them.
   * @param s  The Scanner to read values from.
   * @return true if the game should continue, false if the user wants to quit.
   */
  boolean processInput(Scanner s) {
    boolean active = false;
    boolean cmdValid = false;
    while (!cmdValid) {
      String cmd = s.next().toLowerCase();
      ArrayList<Integer> values = new ArrayList<>();
      active = false;
      cmdValid = true;
      switch (cmd) {
        case "rm1":
          active = scanValues(s, 2, values);
          if (active) {
            model.remove(values.get(0), values.get(1));
          }
          break;
        case "rm2":
          active = scanValues(s, 4, values);
          if (active) {
            model.remove(values.get(0), values.get(1), values.get(2), values.get(3));
          }
          break;
        case "rmwd":
          active = scanValues(s, 3, values);
          if (active) {
            model.removeUsingDraw(values.get(0), values.get(1), values.get(2));
          }
          break;
        case "dd":
          active = scanValues(s, 1, values);
          if (active) {
            model.discardDraw(values.get(0));
          }
          break;
        case "q":
          active = false;
          break;
        default:
          cmdValid = false;
          append("\nInvalid command: " + cmd + "\n");
          active = true;
          break;
      }
    }
    return active;
  }

  /**
   * attempts to write output to the appendable.
   * @param s  the string to be appened.
   * @throws IllegalStateException if the appendable cannot be written to
   */
  void append(String s) {
    try {
      out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to the appendable. "
          + "Error: " + e.getMessage());
    }
  }

  /**
   * transmit the current game state to the appendable.
   * @throws IllegalStateException if an IOexception occurred on reading.
   */
  void transmitGameState() {
    try {
      this.view.render();
      append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error. Could not render game state");
    }
  }

}
