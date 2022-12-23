package cs3500.marblesolitaire.view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Canvas;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class is the board panel.
 */
public class BoardPanel extends JPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  private final int cellDimension;

  /**
   * This is the constructor.
   * @param state the model state
   * @throws IllegalStateException will throw if no icon found
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension,
                       (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  /**
   * This class paints  the board.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    int originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize()
            * cellDimension / 2);
    int originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize()
            * cellDimension / 2);

    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    for (int r = 0; r < modelState.getBoardSize(); r++) {
      for (int c = 0; c < modelState.getBoardSize(); c++) {
        if (modelState.getSlotAt(originX, originY) == MarbleSolitaireModelState.SlotState.Empty) {
          g.drawImage(emptySlot, 3, 3, new Canvas());
        } else if (modelState.getSlotAt(originX, originY)
                == MarbleSolitaireModelState.SlotState.Invalid) {
          g.drawImage(blankSlot,3,3,new Canvas());
        } else {
          g.drawImage(marbleSlot,3,3,new Canvas());
        }
        originX++;
        originY++;
      }
    }
    
  }


 
}
