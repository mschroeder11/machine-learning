import static org.junit.Assert.assertTrue;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksSolitaire;

import org.junit.Test;


/**
 * Tests for the public PyramidSolitaireCreator methods.
 */
public class TestPyramidSolitaireCreator {
  
  // test the create method
  @Test
  public void testCreate() {
    assertTrue(PyramidSolitaireCreator.create(GameType.BASIC) instanceof BasicPyramidSolitaire);
    assertTrue(PyramidSolitaireCreator.create(GameType.RELAXED) instanceof RelaxedPyramidSolitaire);
    assertTrue(PyramidSolitaireCreator.create(GameType.TRIPEAKS) instanceof TriPeaksSolitaire);
  }

}
