package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is an implementation of the MarbleSolitaireView interface
 * that prints a view of the game.
 */
public class MarbleSolitaireTextView extends ASolitaireTextView {

  /**
   * This constructor creates the view from the Model State.
   *
   * @param model the model state
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    super(model);
  }

  /**
   * This constructor creates the view from model and appendable.
   * @param m the model state
   * @param a the appendable object
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState m, Appendable a) {
    super(m,a);
  }

}
