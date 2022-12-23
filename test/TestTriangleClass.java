import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class is a test class used to test the class methods.
 */
public class TestTriangleClass {

  /**
   * This method tests all the possible illegal argument exceptions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArguments() {
    MarbleSolitaireModel msm1 = new TriangleSolitaireModel(-2);
    MarbleSolitaireModel msm2 = new TriangleSolitaireModel(7, 7);
    MarbleSolitaireModel msm3 = new TriangleSolitaireModel(3, 7, 7);
    MarbleSolitaireView msv1 = new TriangleSolitaireTextView(null);
    MarbleSolitaireView msv2 = new TriangleSolitaireTextView(msm1, null);
    msm1.move(3, 7, 3, 3);
    msm2.getSlotAt(-1, -1);
    msm3.getSlotAt(8, 7);
  }


  /**
   * This method tests the getScore() method.
   */
  @Test
  public void testGetScore() {
    MarbleSolitaireModel msm4 = new TriangleSolitaireModel();
    assertEquals(14, msm4.getScore());
    msm4.move(2, 0, 0, 0);
    assertEquals(13, msm4.getScore());
  }

  /**
   * This method tests the getSlotAt() method.
   */
  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel msm5 = new TriangleSolitaireModel();
    MarbleSolitaireModelState.SlotState act1 = msm5.getSlotAt(0, 0);
    MarbleSolitaireModelState.SlotState act2 = msm5.getSlotAt(2, 1);
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
    MarbleSolitaireModel msm6 = new TriangleSolitaireModel();
    assertFalse(msm6.isGameOver());

  }

  /**
   * This method tests the getBoardSize() method.
   */
  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel msm7 = new TriangleSolitaireModel();
    assertEquals(5, msm7.getBoardSize());
  }

  /**
   * This method tests the isInvalid() method.
   */
  @Test
  public void testIsInvalid() {
    TriangleSolitaireModel esm = new TriangleSolitaireModel();
    assertFalse(esm.isInvalid(3, 2)); // this is not invalid
    assertTrue(esm.isInvalid(0, 1)); // this is invalid
  }

  /**
   * This method tests the toString() method.
   */
  @Test
  public void testToString() {
    MarbleSolitaireModel msm9 = new TriangleSolitaireModel();
    MarbleSolitaireView msv1 = new TriangleSolitaireTextView(msm9);
    String expected1 = "      _        \n     O O   \n    O O O  \n   O O O O \n  O O O O O\n";

    assertEquals(expected1, msv1.toString());
  }

  /**
   * This method tests the move() method.
   */
  @Test
  public void testMove() {
    MarbleSolitaireModel msm10 = new TriangleSolitaireModel();
    MarbleSolitaireView msv1 = new TriangleSolitaireTextView(msm10);
    String exp1 = "      O    \n     _ O   \n    _ O O  \n   O O O O \n  O O O O O\n";
    String exp2 = "      O    \n     _ O   \n    O _ _  \n   O O O O \n  O O O O O\n";

    msm10.move(2, 0, 0, 0); // up
    assertEquals(exp1, msv1.toString());
    msm10.move(2, 2, 2, 0); // left
    assertEquals(exp2, msv1.toString());
  }
}

