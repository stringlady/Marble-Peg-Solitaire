package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is the European view equivalent to the solitaire peg game.
 */
public class EuropeanSolitaireTextView extends ASolitaireTextView {
  private MarbleSolitaireModelState model;
  private Appendable appendable;

  /**
   * This constructor creates the view from the Model State.
   *
   * @param m the model state
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState m) {
    super(m);
  }

  /**
   * This constructor creates the view from model and appendable.
   * @param m the model state
   * @param a the appendable object
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState m, Appendable a) {
    super(m,a);
  }
}
