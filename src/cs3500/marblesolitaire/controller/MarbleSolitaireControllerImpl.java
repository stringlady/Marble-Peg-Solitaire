package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class implements the methods of the MarbleSolitaireController interface.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable readable;

  /**
   * This is the three parameter constructor for this class. If either of the parameters are null
   * then an Illegal Argument Exception will be thrown.
   *
   * @param m represents the model
   * @param v represents the view
   * @param r represents the readable
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel m, MarbleSolitaireView v, Readable r) {
    if (m == null || v == null || r == null) {
      throw new IllegalArgumentException("One of these options is null");
    }
    this.model = m;
    this.view = v;
    this.readable = r;
  }

  /**
   * Plays the game.
   *
   * @throws IllegalStateException if the message fails to transmit
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner s = new Scanner(this.readable);
    boolean keepGoing = true;
    Deque<Integer> c = new LinkedList<>(); //coordinates
    try {
      this.view.renderMessage("Enter from and to coordinates to play (example: 2 4 4 4).\n");
      this.view.renderMessage("Columns and rows start at 1.\n");
      this.view.renderMessage("Type 'q' if you wish to quit.\n");
      this.view.renderBoard();
      this.view.renderMessage("Score: " + this.model.getScore() + "\n");

      while (keepGoing) {
        this.view.renderMessage("Type from and to coordinates: \n");
        if (!s.hasNext()) {
          throw new IllegalStateException("Ran out of inputs\n");
        }

        String input = s.nextLine();
        String[] inputs = input.split(" ");

        for (String i : inputs) {
          if (i.toLowerCase(Locale.ROOT).equals("q")) {
            keepGoing = false;
            break;
          }

          try {
            c.add(Integer.parseInt(i));
          } catch (NumberFormatException n) {
            // don't do anything if caught
          }
        }

        if (c.size() < 4) {
          this.view.renderMessage("Please input a valid input.\n");
        } else {
          while (c.size() >= 4) {
            try {
              this.model.move(c.remove() - 1, c.remove() - 1,
                      c.remove() - 1, c.remove() - 1);
            } catch (IllegalArgumentException e) {
              this.view.renderMessage("Invalid move. Play again.\n");
            }
          }
        }

        if (keepGoing) {
          this.view.renderBoard();
          this.view.renderMessage("\nScore: " + this.model.getScore());
        }

        if (this.model.isGameOver()) {
          keepGoing = false;
          this.view.renderMessage("\nGame over! \n");
          this.view.renderBoard();
          this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
        } else if (!keepGoing) {
          this.view.renderMessage("Game quit!\n");
          this.view.renderMessage("State of game when quit:\n");
          this.view.renderBoard();
          this.view.renderMessage("Score: " + this.model.getScore() + "\n");
        }

      }
    } catch (IOException e) {
      throw new IllegalStateException("Message failed to transmit.");
    }
  }
}
