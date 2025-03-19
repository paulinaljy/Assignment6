package cs3500.pawnsboard.controller;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view;

  public PawnsBoardGUIController(PawnsBoardModel model, PawnsBoardView view) {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame() {
    this.view.subscribe(this);
    this.view.makeVisible();
  }

  @Override
  public void placeCard(int cardIdx, int row, int col) {
    model.placeCardInPosition(cardIdx, row, col);
    view.refresh();
  }

  @Override
  public void quit() {
    System.exit(0);
  }

  @Override
  public void pass() {
    model.pass();
  }


}
