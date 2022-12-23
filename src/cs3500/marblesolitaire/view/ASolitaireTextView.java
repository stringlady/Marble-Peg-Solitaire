package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is an abstract of all three of the view classes.
 */
public abstract class ASolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  private Appendable appendable;

  /**
   * This constructor creates the view from the Model State.
   *
   * @param m the model state
   */
  public ASolitaireTextView(MarbleSolitaireModelState m) {
    if (m == null) {
      throw new IllegalArgumentException("Model is null.");
    }
    this.model = m;
    this.appendable = System.out;
  }

  /**
   * This constructor creates the view from model and appendable.
   * @param m the model state
   * @param a the appendable object
   */
  public ASolitaireTextView(MarbleSolitaireModelState m, Appendable a) {
    if (m == null || a == null) {
      throw new IllegalArgumentException("One of these is null.");
    }
    this.model = m;
    this.appendable = a;
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
    for (int r = 0; r < this.model.getBoardSize(); r++) {
      boolean hasMarbleStarted = false;
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

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */

  @Override
  public void renderBoard() throws IOException {
    this.appendable.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
