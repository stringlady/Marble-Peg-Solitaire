package cs3500.marblesolitaire.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * This is the new controller.
 */
public class MVCController implements MarbleSolitaireController, ActionListener {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;

  /**
   * This is the constructor.
   * @param m the model
   * @param v the view
   */
  public MVCController(MarbleSolitaireModel m, MarbleSolitaireGuiView v) {
    this.model = m;
    this.view = v;
  }

  /**
   * Plays the game.
   *
   * @throws IllegalArgumentException if the messages failed to transmit
   */
  @Override
  public void playGame() throws IllegalStateException {
    this.model.getBoardSize();
    this.view.refresh();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    e.getActionCommand();
  }
}
