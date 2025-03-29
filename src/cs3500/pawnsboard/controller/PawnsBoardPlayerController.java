package cs3500.pawnsboard.controller;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import cs3500.pawnsboard.model.GamePlayer;
import cs3500.pawnsboard.model.ModelActions;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.GameOverFrame;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

/**
 * GUI controller for the Pawns Board game,
 * handling user interactions.
 */
public class PawnsBoardPlayerController implements PawnsBoardController, ViewActions, ModelActions {

  private PawnsBoardModel model;
  private PawnsBoardView view;
  private GamePlayer player;
  private Appendable transcript;
  private int cardIdxSelected;
  private Point cellSelected;
  private boolean viewEnabled;

  /**
   * Constructs a GUI controller for the game.
   *
   * @param model model
   * @param view player's GUI view
   * @param player player
   * @throws IllegalArgumentException if model or view is null.
   */
  public PawnsBoardPlayerController(PawnsBoardModel model, GamePlayer player, PawnsBoardView view) {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (player == null) {
      throw new IllegalArgumentException("GamePlayer cannot be null");
    }
    this.model = model;
    this.view = view;
    this.transcript = System.out;
    this.viewEnabled = false;
    this.player = player;
    this.cellSelected = null;
    this.cardIdxSelected = -1;
  }

  @Override
  public void playGame() {
    this.view.subscribe(this);
    this.view.makeVisible();
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
    if (cardIdxSelected == -1) {
      JOptionPane.showMessageDialog(null, currentPlayer + ": " + "Please " +
                      "select a card from hand first", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    if (cellSelected == null) {
      JOptionPane.showMessageDialog(null, currentPlayer + ": " + "Please " +
              "select a cell on the board first", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    try {
      addTranscript(currentPlayer + " placed card " + cardIdxSelected);
      model.placeCardInPosition(cardIdxSelected, (int)cellSelected.getX(), (int)cellSelected.getY());
      model.drawNextCard();
      view.refresh();
      cardIdxSelected = -1;
      cellSelected = null;
    } catch (IllegalArgumentException | IllegalStateException e) {
      JOptionPane.showMessageDialog(null, currentPlayer + ": " + e.getMessage()
              + "Please play again.",
              "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
    }
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
    try {
      model.pass();
      cardIdxSelected = -1;
      cellSelected = null;
    } catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null, currentPlayer + ": " + e.getMessage()
                      + "Please play again.", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
    }

    if (model.isGameOver()) {
      processGameOver();
      return;
    }
    model.drawNextCard();
    view.refresh();
  }

  @Override
  public void setCardIdx(int cardIdx) {
    cardIdxSelected = cardIdx;
    addTranscript("Player " + model.getCurrentPlayerID() + " selected card " + cardIdx);
  }

  @Override
  public void setSelectedCell(int row, int col) {
    cellSelected = new Point(row, col);
    addTranscript("Cell (" + row + "," + col + ") selected");
  }

  @Override
  public boolean isViewEnabled() {
    return viewEnabled;
  }

  private void processGameOver() {
    if (model.isGameOver()) {
      JFrame gameOverFrame = new GameOverFrame(model);
      gameOverFrame.setVisible(true);
      gameOverFrame.setFocusable(true);
    }
  }

  @Override
  public void itsYourTurn() {
    if (player.isHumanPlayer()) {
      viewEnabled = true;
      String currentPlayer = "Player RED";
      if (model.getCurrentPlayer().getColor().equals(Color.blue)) {
        currentPlayer = "Player BLUE";
      }
      JOptionPane.showMessageDialog(null, currentPlayer + ": It's your turn!",
              "It's Your Turn!", JOptionPane.INFORMATION_MESSAGE);
    }
    player.chooseMove();
  }
}

