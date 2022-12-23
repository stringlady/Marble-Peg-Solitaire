package cs3500.marblesolitaire;


import cs3500.marblesolitaire.controller.MVCController;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * This class has the main.
 */
public class Lab7Main {

  /**
   * This is the main method which helps play the Solitaire game.
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView gui = new SwingGuiView(model);
    MarbleSolitaireController controller = new MVCController(model, gui);
    controller.playGame();
  }
}
