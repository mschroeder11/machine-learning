package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Represents a factory class for creating a model 
 * for a game of one of three different types of pyramid solitaire.
 */
public class PyramidSolitaireCreator {
  
  /**
   * represents the possible gametypes.
   */
  public enum GameType {
    /**
     * the different possible gametypes.
     */
    BASIC, RELAXED, TRIPEAKS;
  }
  
  /**
   * Constructs a PyramidSolitaireCreator().
   * The constructor is private to make the class non-instantiable.
   */
  private PyramidSolitaireCreator() {}
  
  /**
   * create an instance of a pyramid solitaire game.
   * @param game  the type of game.
   * @return  a new instance of a pyramidsolitairemodel.
   */
  public static PyramidSolitaireModel<Card> create(GameType game) {
    PyramidSolitaireModel<Card> myGame;
    switch (game) {
      case BASIC:
        myGame = new BasicPyramidSolitaire();
        break;
      case RELAXED:
        myGame = new RelaxedPyramidSolitaire();
        break;
      case TRIPEAKS:
        myGame = new TriPeaksSolitaire();
        break;
      default:
        myGame = null;
        break;
    }
    return myGame;
  }

}
