package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;

/**
 * Represents a textual model of a PyramidSolitaire game.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {

  private final PyramidSolitaireModel<?> model;
  private final Appendable out;

  /**
   * Constructs a PyramidSolitaireTextualView.
   *
   * @param model the model for this view.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
    out = null;
  }
  
  /**
   * Constructs a PyramidSolitaireTextualView.
   * @param model   model for this view.
   * @param out   appendable for this view.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    if (out == null) {
      throw new IllegalArgumentException("out cannot be null");
    }
    this.out = out;
    this.model = model;
  } 


  @Override
  public String toString() {
    try {
      if (model.getNumRows() == -1) {
        return "";
      }
      if (model.isGameOver()) {
        if (model.getScore() == 0) {
          return "You win!";
        } else {
          return "Game over. Score: " + model.getScore();
        }
      }
      String game = renderPyramid();
      game += renderDraw();
      return game;
    } catch (Exception e) {
      // If any unexpected exception occurs, return an empty string.
      return "";
    }
  }
  
  /**
   * Returns a string rendering of the pyramid.
   * 
   * @return a string rendering of the pyramid.
   */
  String renderPyramid() {
    String game = "";
    int numRows = model.getNumRows();
    for (int i = 0; i < numRows; i++) {
      String current = "";
      for (int k = 0; k < numRows - 1 - i; k++) {
        current += "  ";
      }
      for (int j = 0; j < model.getRowWidth(i); j++) {
        String card = "   ";
        if (model.getCardAt(i, j) != null) {
          card = model.getCardAt(i, j).toString();
        }
        if (card.length() < 3) {
          card += " ";
        }
        current += card;
        current += " ";
      }
      current = current.stripTrailing();
      game += current + "\n";
    }
    return game;
  }
  
  /**
   * Returns a string rendering of the draw.
   * 
   * @return a string rendering of the draw.
   */
  String renderDraw() {
    if (model.getNumDraw() < 0) {
      return "";
    }
    String draw = "";
    for (int k = 0; k < model.getNumDraw(); k++) {
      if (model.getDrawCards().get(k) != null) {
        if (draw.length() != 0) {
          draw += ", ";
        } else {
          draw += " ";
        }
        draw += model.getDrawCards().get(k).toString();
      }
    }
    return "Draw:" + draw;
  }


  @Override
  public void render() throws IOException {
    if (this.out == null) {
      throw new IOException("No appendable to render to");
    }
    this.out.append(this.toString());
  }
  
}
