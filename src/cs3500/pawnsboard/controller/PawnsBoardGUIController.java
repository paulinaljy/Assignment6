package cs3500.pawnsboard.controller;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import cs3500.pawnsboard.model.ModelActions;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.view.GameOverFrame;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

/**
 * GUI controller for the Pawns Board game,
 * handling user interactions.
 */
public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view1;
  private PawnsBoardView view2;
  private Appendable transcript;

  /**
   * Constructs a GUI controller for the game.
   *
   * @param model model
   * @param view1 player 1's GUI view
   * @param view2 player 2's GUI view
   * @throws IllegalArgumentException if model or view is null.
   */
  public PawnsBoardGUIController(PawnsBoardModel model,
                                 PawnsBoardView view1, PawnsBoardView view2) {
    if (view1 == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.view1 = view1;
    this.view2 = view2;
    this.transcript = System.out;
  }

  @Override
  public void playGame() {
    this.view1.subscribe(this);
    this.view1.makeVisible();
    this.view2.subscribe(this);
    this.view2.makeVisible();
  }

  private void addTranscript(String message) {
    try {
      transcript.append(message + "\n");
    } catch (IOException e) {
      // ignore exception
    }
  }

  @Override
  public void placeCard() {
    String currentPlayer = "Player 1";
    if (model.getCurrentPlayer().getColor().equals(Color.blue)) {
      currentPlayer = "Player 2";
    }
    //addTranscript(currentPlayer + " placed card " + cardIdx);
    //model.placeCardInPosition(cardIdx, row, col);
    model.drawNextCard();
    view1.refresh();
    view2.refresh();
    if (model.isGameOver()) {
      processGameOver();
    }
  }

  @Override
  public void quit() {
    addTranscript("Game quit");
    System.exit(0);
  }

  @Override
  public void pass() {
    String currentPlayer = "Player 1";
    if (model.getCurrentPlayer().getColor().equals(Color.blue)) {
      currentPlayer = "Player 2";
    }
    addTranscript(currentPlayer + " passed");
    model.pass();
    if (model.isGameOver()) {
      processGameOver();
      return;
    }
    model.drawNextCard();
    view1.refresh();
    view2.refresh();
  }

  @Override
  public void setCardIdx(int cardIdx) {
    addTranscript("Player " + model.getCurrentPlayerID() + " selected card " + cardIdx);
  }

  @Override
  public void setSelectedCell(int row, int col) {
    addTranscript("Cell (" + row + "," + col + ") selected");
  }

  @Override
  public boolean isViewEnabled() {
    return false;
  }

  private void processGameOver() {
    if (model.isGameOver()) {
      JFrame gameOverFrame = new GameOverFrame(model);
      gameOverFrame.setVisible(true);
      gameOverFrame.setFocusable(true);
    }
  }
}
