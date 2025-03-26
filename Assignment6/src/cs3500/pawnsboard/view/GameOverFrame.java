package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

/**
 * Represents a GameOverFrame with behaviors, including drawing the game over message with the
 * winner and exiting the game.
 */
public class GameOverFrame extends JFrame implements PawnsBoardView {
  private final ReadonlyPawnsBoardModel pawnsBoardModel;
  public GameOverFrame(ReadonlyPawnsBoardModel pawnsBoardModel) {
    super("Game Over");
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.pawnsBoardModel = pawnsBoardModel;

    setSize(125, 100);
    setLocation(600, 200);

    JLabel gameOverLabel = new JLabel("Game Over!");
    String winner = "Winner: ";
    if (pawnsBoardModel.getWinner() == null) {
      winner += "Tie!";
    } else if (pawnsBoardModel.getWinner().getColor().equals(Color.red)) {
      winner += "Player 1";
    } else {
      winner += "Player 2";
    }
    JLabel winnerLabel = new JLabel(winner);

    JButton okButton = new JButton("OK");
    okButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.exit(0);
      }
    });

    setLayout(new FlowLayout());
    this.getContentPane().add(gameOverLabel);
    this.getContentPane().add(winnerLabel);
    this.getContentPane().add(okButton);
    setVisible(true);
    setFocusable(true);
  }
  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void subscribe(ViewActions observer) {
    observer.quit();
  }
}
