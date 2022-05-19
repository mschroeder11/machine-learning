import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksSolitaire;
import java.io.InputStreamReader;

public class Solitaire {

  /**
   * play the game for testing purposes.
   * @param args  default.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(rd, ap);
    TriPeaksSolitaire model = new TriPeaksSolitaire();
    controller.playGame(model, model.getDeck(), false, 7, 3);
  }

}
