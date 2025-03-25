package cs3500.pawnsboard.controller;

import java.io.IOException;

import javax.swing.*;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.GameOverFrame;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view1;
  private PawnsBoardView view2;
  private int cardIdx;
  private int row;
  private int col;
  private Appendable transcript;

  public PawnsBoardGUIController(PawnsBoardModel model, PawnsBoardView view1, PawnsBoardView view2) {
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
  public void placeCard(int cardIdx, int row, int col) {
    addTranscript(model.getCurrentPlayer() + " placed card");
    model.placeCardInPosition(cardIdx, row, col);
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
    addTranscript(model.getCurrentPlayer() + " passed");
    model.pass();
    model.drawNextCard();
    view1.refresh();
    view2.refresh();
    if (model.isGameOver()) {
      processGameOver();
    }
  }

  @Override
  public void setCardIdx(int cardIdx) {
    this.cardIdx = cardIdx;
    addTranscript("Player " + model.getCurrentPlayerID() + " selected card " + cardIdx);
  }

  @Override
  public void setSelectedCell(int row, int col) {
    this.row = row;
    this.col = col;
    addTranscript("Cell (" + row + "," + col + ") selected");
  }

  private void processGameOver() {
    if (model.isGameOver()) {
      JFrame gameOverFrame = new GameOverFrame();
      gameOverFrame.setVisible(true);
      gameOverFrame.setFocusable(true);
    }
  }
}
