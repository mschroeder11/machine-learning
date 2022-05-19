package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;

import java.io.InputStreamReader;


/**
 * A class to provide a simple main() method for playing a game of pyramid solitaire.
 */
public class PyramidSolitaire {

  /**
   * main method.
   */
  public static void main(String[] args) {
    if (args.length < 1 || args.length > 3) {
      usage();
      return;
    }

    String gametype = args[0];
    int numRows = 7;
    int numDraw = 3;
    if (args.length > 1) {
      try {
        numRows = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println("Not a valid number " + args[1]);
        return;
      }
    }
    if (args.length > 2) {
      try {
        numDraw = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.out.println("Not a valid number: " + args[2]);
        return;
      }
    }
    PyramidSolitaireModel<Card> model;
    switch (gametype) {
      case "basic":
        model = PyramidSolitaireCreator.create(GameType.BASIC);
        break;
      case "relaxed":
        model = PyramidSolitaireCreator.create(GameType.RELAXED);
        break;
      case "tripeaks":
        model = PyramidSolitaireCreator.create(GameType.TRIPEAKS);
        break;
      default:
        usage();
        return;
    }
    
    Readable r = new InputStreamReader(System.in); 
    Appendable ap = System.out;
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(r, ap);
    try {
      controller.playGame(model, model.getDeck(), false, numRows, numDraw);
    } catch (Exception e) {
      System.out.println("Error playing game: " + e.getMessage());
    }
  }

  /**
   * Displays a program usage message.
   */
  private static void usage() {
    System.out.println("Usage: PyramidSolitaire <game type> [nbrRows [nbrDraw]]\n");
    System.out.println(" game type is one of: 'basic', 'relaxed', 'tripeaks'");
    System.out.println("nbrRows is the number of rows in the game");
    System.out.println("nbrDraw is the size of the draw in the game");
  }
}
