package cs3500.marblesolitaire.controller;

/**
 * This interface is for the MarbleSolitaireControllerImpl class and carries its methods.
 */
public interface MarbleSolitaireController {

  /**
   * Plays the game.
   *
   * @throws IllegalArgumentException if the messages failed to transmit
   */
  void playGame() throws IllegalStateException;
}
