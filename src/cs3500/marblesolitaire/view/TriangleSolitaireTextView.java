package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is the triangle view equivalent to the peg solitaire game.
 */
public class TriangleSolitaireTextView extends ASolitaireTextView {

  /**
   * This constructor creates the view from the Model State.
   *
   * @param m the model state
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState m) {
    super(m);
  }

  /**
   * This constructor creates the view from model and appendable.
   * @param m the model state
   * @param a the appendable object
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState m, Appendable a) {
    super(m,a);
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */
  public String toString() {
    StringBuilder toReturn = new StringBuilder();
    int s = this.model.getBoardSize();
    for (int r = 0; r < this.model.getBoardSize(); r++) {
      boolean hasMarbleStarted = false;
      for (int j = 0; j <= s - r; j++) {
        toReturn.append(" ");
      }
      for (int c = 0; c < this.model.getBoardSize(); c++) {
        switch (this.model.getSlotAt(r,c)) {
          case Empty:
            if (c < this.model.getBoardSize() && c > 0) {
              toReturn.append(" ");
            }
            toReturn.append("_");
            break;
          case Marble:
            hasMarbleStarted = true;
            if (c < this.model.getBoardSize() && c > 0) {
              toReturn.append(" ");
            }
            toReturn.append("O");
            break;
          case Invalid:
            if (!hasMarbleStarted) {
              if (c < this.model.getBoardSize() && c > 0) {
                toReturn.append(" ");
              }
            }
            toReturn.append(" ");
            break;
          default:
            toReturn.append("");
            break;
        }
        if (c == this.model.getBoardSize() - 1) {
          toReturn.append("\n");
        }

      }
    }

    return toReturn.toString();
  }
}
