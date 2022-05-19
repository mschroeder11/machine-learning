package cs3500.pyramidsolitaire.model.hw02;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.List;
import java.util.Objects;


/**
 * confirms inputs for a solitaire model.
 */
public class ConfirmInputsSolitaireModel implements PyramidSolitaireModel<Card> {
  final StringBuilder log;
  
  public ConfirmInputsSolitaireModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List<Card> getDeck() {
    // we dont care about this
    return null;
  }

  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    // we dont care about this either
    
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    log.append(String.format("remove(%d, %d, %d, %d)\n",
        row1, card1, row2, card2)); 
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    log.append(String.format("remove(%d, %d)\n",
        row, card));
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    log.append(String.format("removeUsingDraw(%d, %d, %d)\n",
        drawIndex, row, card));
    
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    log.append(String.format("discardDraw(%d)\n",
        drawIndex));
    
  }

  @Override
  public int getNumRows() {
    // don't care about this
    return 0;
  }

  @Override
  public int getNumDraw() {
    //still don't care about this
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    // still don't care about this
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    // still dont care about this
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    // don't care about this either
    return 0;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    log.append(String.format("getCardAt(%d, %d)\n", row, card));
    return null;
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    // TODO Auto-generated method stub
    return null;
  }

}
