package cs3500.marblesolitaire.model.hw04;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class is the Triangle equivalent to the peg oslitaire game.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  int dimensions;
  int sRow;
  int sCol;
  SlotState[][] board;

  /**
   * The default constructor with no parameters.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * This constructor allows you to choose an empty slot on the board.
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * This constructor allows the user to choose an arm thickness and submit an empty row
   * at the center automatically.
   *
   * @param dimensions
   * the arm thickness on the board
   */
  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  /**
   * This constructor allows a person to choose an arm thickness and any empty slot on the board.
   *
   * @param dimensions
   * the arm thickness of the board
   * @param sRow         the row of the empty slot
   * @param sCol         the column of the empty slot.
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    if (dimensions < 0) {
      throw new IllegalArgumentException("Thickness is not a positive integer");
    }
    this.dimensions = dimensions;
    this.sRow = sRow;
    this.sCol = sCol;

    if (this.isInvalid(sRow,sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + Integer.toString(sRow)
              + "," + Integer.toString(sCol) + ")");
    }
    this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
    this.initializeBoard();
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.dimensions;
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if ((fromRow < 0 || fromCol < 0) || (toRow < 0 || toCol < 0) ||
            (fromRow >= this.getBoardSize() || fromCol >= this.getBoardSize())
            || (toRow >= this.getBoardSize() || toCol >= this.getBoardSize())) {
      throw new IllegalArgumentException("Rows or Columns are invalid");
    }

    SlotState fromRandC = this.getSlotAt(fromRow, fromCol);
    SlotState toRandC = this.getSlotAt(toRow, toCol);

    if (fromRandC.equals(SlotState.Marble) && toRandC.equals(SlotState.Empty)) {
      if (fromRow == toRow && this.getSlotAt(fromRow,
              (fromCol + toCol) / 2).equals(SlotState.Marble)
              && Math.abs(fromCol - toCol) == 2) {
        this.board[fromRow][fromCol] = SlotState.Empty;
        this.board[toRow][toCol] = SlotState.Marble;
        this.board[toRow][(fromCol + toCol) / 2] = SlotState.Empty;

      } else if (fromCol == toCol && this.getSlotAt((fromRow + toRow) / 2,
              fromCol).equals(SlotState.Marble) && Math.abs(fromRow - toRow) == 2) {
        this.board[fromRow][fromCol] = SlotState.Empty;
        this.board[toRow][toCol] = SlotState.Marble;
        this.board[(fromRow + toRow) / 2][fromCol] = SlotState.Empty;
      } else {
        throw new IllegalArgumentException("Invalid Move");
      }
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }

  /**
   * Tests whether a specific row or column is considered invalid or not.
   *
   * @param row the selected row
   * @param col the selected column
   * @return return the invalid boolean
   */
  public boolean isInvalid(int row, int col) {
    return col > row;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        boolean up = r >= 2 && this.getSlotAt(r - 1, c).equals(SlotState.Marble)
                && this.getSlotAt(r - 2, c).equals(SlotState.Empty);
        boolean down = r < this.getBoardSize() - 2
                && this.getSlotAt(r + 1, c).equals(SlotState.Marble)
                && this.getSlotAt(r + 2, c).equals(SlotState.Empty);
        boolean left = c >= 2 && this.getSlotAt(r, c - 1).equals(SlotState.Marble)
                && this.getSlotAt(r, c - 2).equals(SlotState.Empty);
        boolean right = c < this.getBoardSize() - 2
                && this.getSlotAt(r, c + 1).equals(SlotState.Marble)
                && this.getSlotAt(r, c + 2).equals(SlotState.Empty);

        if (this.board[r][c].equals(SlotState.Marble)) {
          if (up || down || left || right) {
            return false;
          }
        }

      }
    }
    return true;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    SlotState slot = SlotState.Empty;
    if (row > this.getBoardSize() || col > this.getBoardSize() || row < 0 || col < 0) {
      throw new IllegalArgumentException("Row and/or columns are of invalid dimensions");
    } else {
      slot = this.board[row][col];
    }
    return slot;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int marbles = 0;
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        if (this.board[r][c].equals(SlotState.Marble)) {
          marbles++;
        }
      }
    }
    return marbles;
  }

  /**
   * This method will initialize the board at the beginning of the game and fill the slots
   * corresponding to the start without any moves made.
   */
  public void initializeBoard() {
    for (int r = 0; r < this.getBoardSize(); r++) {
      for (int c = 0; c < this.getBoardSize(); c++) {
        if (this.isInvalid(r, c)) {
          this.board[r][c] = SlotState.Invalid;
        } else if (r == this.sRow && c == this.sCol) {
          this.board[r][c] = SlotState.Empty;
        } else {
          this.board[r][c] = SlotState.Marble;
        }
      }
    }
  }

}
