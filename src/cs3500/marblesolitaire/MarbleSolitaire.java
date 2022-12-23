package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.SwingGuiView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class contains the main method which allows you to play the game.
 */
public final class MarbleSolitaire {
  /**
   * This is the main method which helps play the Solitaire game.
   */
  public static void main(String[] args) {
    String s = ""; //start of the game
    int size = 0; //size
    int sRow = 0; // empty slot row
    int sCol = 0; //empty slot column

    if (args[0].equals("english") || args[0].equals("european")) {
      s = args[0];
      size = 3;
      sRow = 3;
      sCol = 3;
    } else if (args[0].equals("triangular")) {
      s = args[0];
      size = 5;
      sRow = 0;
      sCol = 0;
    }

    if (args.length >= 2 && args[2].equals("-size")) {
      size = Integer.parseInt(args[2]);
      sRow = Integer.parseInt(args[2]) - 1;
      sCol = Integer.parseInt(args[2]) - 1;
    } else if (args.length >= 2 && args[1].equals("-hole")) {
      sRow = Integer.parseInt(args[2]) - 1;
      sCol = Integer.parseInt(args[3]) - 1;
    }

    if (args.length >= 5 && args[4].equals("-size")) {
      size = Integer.parseInt(args[5]);
    } else if (args.length >= 5 && args[3].equals("-hole")) {
      sRow = Integer.parseInt(args[4]) - 1;
      sCol = Integer.parseInt(args[5]) - 1;
    }
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    MarbleSolitaireController controller = null;

    switch (s) {
      case "triangular":
        MarbleSolitaireModel mTriangular = new TriangleSolitaireModel(size,sRow,sCol);
        MarbleSolitaireView vTriangular = new TriangleSolitaireTextView(mTriangular, ap);
        controller = new MarbleSolitaireControllerImpl(mTriangular,
                vTriangular, rd);
        break;
      case "european":
        MarbleSolitaireModel mEuropean = new EuropeanSolitaireModel(size,sRow,sCol);
        MarbleSolitaireView vEuropean = new EuropeanSolitaireTextView(mEuropean, ap);
        controller = new MarbleSolitaireControllerImpl(mEuropean,
                vEuropean, rd);
        break;
      case "english":
        MarbleSolitaireModel mEnglish = new EnglishSolitaireModel(size,sRow,sCol);
        MarbleSolitaireView vEnglish = new MarbleSolitaireTextView(mEnglish, ap);
        controller = new MarbleSolitaireControllerImpl(mEnglish,
                vEnglish, rd);
        break;
      default:
        break;
    }
    MarbleSolitaireModel mEnglish = new EnglishSolitaireModel(size,sRow,sCol);
    MarbleSolitaireView vEnglish = new MarbleSolitaireTextView(mEnglish, ap);
    SwingGuiView sgv = new SwingGuiView(mEnglish);
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(mEnglish,
            vEnglish, rd);
    controller1.playGame();

  }
}
