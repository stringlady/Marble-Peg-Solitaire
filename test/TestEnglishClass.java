import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class is a test class used to test the class methods.
 */
public class TestEnglishClass {

  /**
   * This method tests all the possible illegal argument exceptions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArguments() {
    MarbleSolitaireModel msm1 = new EnglishSolitaireModel(2);
    MarbleSolitaireModel msm2 = new EnglishSolitaireModel(7, 7);
    MarbleSolitaireModel msm3 = new EnglishSolitaireModel(3, 7, 7);
    MarbleSolitaireView msv1 = new MarbleSolitaireTextView(null);
    MarbleSolitaireView msv2 = new MarbleSolitaireTextView(msm1, null);
    msm1.move(3, 7, 3, 3);
    msm2.getSlotAt(-1, -1);
    msm3.getSlotAt(8, 7);
  }


  /**
   * This method tests the getScore() method.
   */
  @Test
  public void testGetScore() {
    MarbleSolitaireModel msm4 = new EnglishSolitaireModel();
    assertEquals(32, msm4.getScore());
    msm4.move(3, 1, 3, 3);
    assertEquals(31, msm4.getScore());
  }

  /**
   * This method tests the getSlotAt() method.
   */
  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel msm5 = new EnglishSolitaireModel();
    MarbleSolitaireModelState.SlotState act1 = msm5.getSlotAt(3, 3);
    MarbleSolitaireModelState.SlotState act2 = msm5.getSlotAt(4, 5);
    MarbleSolitaireModelState.SlotState act3 = msm5.getSlotAt(0, 1);
    MarbleSolitaireModelState.SlotState exp1 = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState exp2 = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState exp3 = MarbleSolitaireModelState.SlotState.Invalid;
    assertEquals(exp1, act1); // Empty
    assertEquals(exp2, act2); // Marble
    assertEquals(exp3, act3); // Invalid
  }

  /**
   * This method tests the isGameOver() method.
   */
  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel msm6 = new EnglishSolitaireModel();
    assertFalse(msm6.isGameOver());

  }

  /**
   * This method tests the getBoardSize() method.
   */
  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel msm7 = new EnglishSolitaireModel();
    assertEquals(7, msm7.getBoardSize());
  }

  /**
   * This method tests the isInvalid() method.
   */
  @Test
  public void testIsInvalid() {
    EnglishSolitaireModel esm = new EnglishSolitaireModel();
    assertFalse(esm.isInvalid(3, 2)); // this is not invalid
    assertTrue(esm.isInvalid(0, 0)); // this is invalid
  }

  /**
   * This method tests the toString() method.
   */
  @Test
  public void testToString() {
    MarbleSolitaireModel msm9 = new EnglishSolitaireModel();
    MarbleSolitaireView msv1 = new MarbleSolitaireTextView(msm9);
    String expected1 = "    O O O  \n    O O O  \nO O O O O O O\nO O O _ O O O" +
            "\nO O O O O O O\n    O O O  \n    O O O  \n";

    assertEquals(expected1, msv1.toString());
  }

  /**
   * This method tests the move() method.
   */
  @Test
  public void testMove() {
    MarbleSolitaireModel msm10 = new EnglishSolitaireModel();
    MarbleSolitaireView msv1 = new MarbleSolitaireTextView(msm10);
    String exp1 = "    O O O  \n    O O O  \nO O O O O O O\nO _ _ O O O O" +
            "\nO O O O O O O\n    O O O  \n    O O O  \n";
    String exp2 = "    O O O  \n    _ O O  \nO O _ O O O O\nO _ O O O O O" +
            "\nO O O O O O O\n    O O O  \n    O O O  \n";
    String exp3 = "    O O O  \n    _ O O  \nO O O O O O O\nO _ _ O O O O" +
            "\nO O _ O O O O\n    O O O  \n    O O O  \n";
    String exp4 = "    O O O  \n    _ O O  \nO O O O O O O\nO _ _ O O O O" +
            "\nO O O _ _ O O\n    O O O  \n    O O O  \n";

    msm10.move(3, 1, 3, 3); // to the right
    assertEquals(exp1, msv1.toString());
    msm10.move(1, 2, 3, 2); // down
    assertEquals(exp2, msv1.toString());
    msm10.move(4, 2, 2, 2); // up
    assertEquals(exp3, msv1.toString());
    msm10.move(4, 4, 4, 2); // to the left
    assertEquals(exp4, msv1.toString());
  }
}
