package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.List;


/**
 * Represents a model for a pyramid solitaire game with relaxed rules. The relaxed
 * rules allow a pair of cards to be removed if a card is covered by only one other card, 
 * and the player is trying to remove those two cards as a pair, 
 * and if the adds up to 13 as desired.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire {
  
  private final int TARGET = 13;

  /**
   * Constructs a relaxed pyramid solitaire model in an unstarted state.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }
  
  /**
   * Constructs a RelaxedPyramidSoliare model in a started state. 
   * This is to be used only for testing.
   *
   * @param deck The cards in the pyramid, as a list.
   * @param numRows Number of rows in the pyramid.
   * @param numDraw Number of cards in the draw.
   */
  public RelaxedPyramidSolitaire(List<Card> deck, int numRows, int numDraw) {
    super(deck, numRows, numDraw);
  } 

  @Override
  protected void testRemovable(int row1, int card1, int row2, int card2) {
    if (getCardAt(row1, card1) == null) {
      throw new IllegalArgumentException("Card does not exist");
    }
    if (getCardAt(row2, card2) == null) {
      throw new IllegalArgumentException("Card does not exist");
    }
    if (!isCovered(row1, card1) && !isCovered(row2, card2)) {
      return;
    }
    if (isCoveredRelaxed(row1, card1, row2, card2) || isCoveredRelaxed(row2, card2, row1, card1)) {
      throw new IllegalArgumentException("Card is covered");
    }
  }
  
  /**
   * Determine if a card is covered according to relaxed rules relative to second card.
   * If the second card is the only card covering the first card, the first card is
   * considered to be uncovered.
   *
   * @param row1 the row where the first card is located.
   * @param card1 the position where the first card is in its row.
   * @param row2 the row where the second card is located.
   * @param card2 the position where the second card is in its row.
   * @return true if the card is covered.
   */
  boolean isCoveredRelaxed(int row1, int card1, int row2, int card2) {
    // Re-orient so row1 is not below row2
    if (row1 > row2) {
      int temp = row1;
      row1 = row2;
      row2 = temp;
      temp = card1;
      card1 = card2;
      card2 = temp;
    }
    if (isCovered(row2, card2)) {
      // If lower card is covered, no point in checking anything else
      return true;
    } else if (row1 != row2 - 1 || Math.abs(card1 - card2) > 1) {
      // Cards are not close enough for the relaxed rules to apply
      return isCovered(row1, card1);  // already check row2/card2 above
    } else if (card2 == card1 + 1 && getCardAt(row2, card1) == null) {
      // left card below is empty
      return false;
    } else if (card2 == card1 && getCardAt(row2, card1 + 1) == null) {
      // right card below is empty
      return false;
    }
    return true;
  }
  
  /**
   * Tests if the specified card is playable. This is used to determine
   * if a game is lost. If there is a playable card, the game is not lost.
   * For the Relaxed game, a card is playable if there is an uncovered card
   * immediately below the given card and the sum of that card and the
   * given card is 13.
   *
   * @param row   the row position of a card
   * @param card  the card position of a card
   * @return true if the specified card is playable.
   */
  protected boolean isCardPlayable(int row, int card) {
    if (!isCovered(row, card)) {
      return true;
      // check the relaxed covered condition with the card on the lower left
    } else if (getCardAt(row + 1, card) != null && !isCoveredRelaxed(row, card, row + 1, card)
        && getCardAt(row, card).sum(getCardAt(row + 1, card)) == TARGET) {
      return true;
      // check the relaxed covered condition with the card on the lower right
    } else if (getCardAt(row + 1, card + 1) != null 
        && !isCoveredRelaxed(row, card, row + 1, card + 1)
        && getCardAt(row, card).sum(getCardAt(row + 1, card + 1)) == TARGET) {
      return true;
    }
    return false;
  }
}
