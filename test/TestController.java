import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This is a test class that will test all of the methods in the controller.
 */
public class TestController {

  /**
   * Test for Illegal State Exceptions.
   */
  @Test(expected = IllegalStateException.class)
  public void testIllegalStateExceptions() {
    MarbleSolitaireModel msm1 = new EnglishSolitaireModel();
    Appendable a = new StringBuilder();
    Readable r = new StringReader("lol");
    MarbleSolitaireView msv1 = new MarbleSolitaireTextView(msm1, a);
    MarbleSolitaireController msc1 = new MarbleSolitaireControllerImpl(msm1,msv1,r);
    msc1.playGame();

  }

  /**
   * Test the renderMessage() method.
   */
  @Test
  public void testRenderMessage() {
    Appendable a1 = new StringBuilder();
    MarbleSolitaireModel msm2 = new EnglishSolitaireModel();
    MarbleSolitaireView msv2 = new MarbleSolitaireTextView(msm2, a1);
    try {
      msv2.renderMessage("Message Test");
    } catch (IOException e) {
      assertEquals("Message Test", a1.toString());
    }
  }

  /**
   * Tests the renderBoard() method.
   */
  @Test
  public void testRenderBoard() {
    Appendable a1 = new StringBuilder();
    MarbleSolitaireModel msm2 = new EnglishSolitaireModel();
    MarbleSolitaireView msv2 = new MarbleSolitaireTextView(msm2, a1);
    try {
      msv2.renderBoard();
    } catch (IOException e) {
      assertEquals(msv2.toString(),a1.toString());
    }
  }

  /**
   * Tests the playGame() method.
   */
  @Test
  public void testPlayGame() {

    Appendable a1 = new StringBuilder();
    Appendable a2 = new StringBuilder();
    Appendable a3 = new StringBuilder();
    Appendable a4 = new StringBuilder();
    Appendable a5 = new StringBuilder();
    Readable r1 = new StringReader("Q");
    Readable r2 = new StringReader("q");
    Readable r3 = new StringReader("4 2 4 4 q");
    Readable r4 = new StringReader("3 1 3 3 q");
    Readable r5 = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 6 4 4 4 3 4 5 4 1 4 3");
    MarbleSolitaireModel msm2 = new EnglishSolitaireModel();
    MarbleSolitaireModel msm3 = new EnglishSolitaireModel();
    MarbleSolitaireModel msm4 = new EnglishSolitaireModel();
    MarbleSolitaireModel msm5 = new EnglishSolitaireModel();
    MarbleSolitaireModel msm6 = new EnglishSolitaireModel();
    MarbleSolitaireView msv2 = new MarbleSolitaireTextView(msm2, a1);
    MarbleSolitaireView msv3 = new MarbleSolitaireTextView(msm3, a2);
    MarbleSolitaireView msv4 = new MarbleSolitaireTextView(msm4, a3);
    MarbleSolitaireView msv5 = new MarbleSolitaireTextView(msm5, a4);
    MarbleSolitaireView msv6 = new MarbleSolitaireTextView(msm6, a5);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(msm2, msv2,r1);
    MarbleSolitaireController controller1 = new MarbleSolitaireControllerImpl(msm3, msv3,r2);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(msm4, msv4,r3);
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(msm5, msv5,r4);
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(msm6, msv6,r5);
    String quit = "Game quit!\nState of game when quit:\n" + msv2.toString();
    String start = "Enter from and to coordinates to play (example: 2 4 4 4).\n" +
            "Columns and rows start at 1.\nType 'q' if you wish to quit.\n" + msv2.toString() +
            "Score: " + msm2.getScore() + "\nType from and to coordinates: \n";
    String lessThan = "Please input a valid input.\n";
    String invalidMove = "Invalid move. Play again.\n";

    controller.playGame();
    controller1.playGame();
    controller2.playGame();
    controller3.playGame();
    controller4.playGame();
    assertEquals(start + lessThan + quit + "Score: 32\n"
            , a1.toString()); // Capital Q quit
    assertEquals(start + lessThan + quit + "Score: 32\n", a2.toString()); // Lower Q quit
    msm4.move(3,1,3,3);
    assertEquals(start + lessThan + msv4.toString() + "\nScore: 31\n" +
            quit + "Score: 31\n", a3.toString()); // Move
    assertEquals(start + invalidMove + quit, a4.toString()); // Invalid Move

    msm6.move(1,3,3,3);
    msm6.move(4,3,2,3);
    msm6.move(6,3,4,3);
    msm6.move(3,5,3,3);
    msm6.move(3,2,3,4);
    msm6.move(3,0,3,2);
    assertEquals(start + msm6.toString() + "\nScore: 26\n" +
            "Game over!" + msm6.toString() + "\nScore: 26", a5.toString()); // Gmae Over
  }
}
