package cs3500.pawnsboard.controller;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view;

  public PawnsBoardGUIController(PawnsBoardView view) {
    if (view == null) {
      throw new IllegalArgumentException("Bad view");
    }
    this.view = view;
  }

  @Override
  public void playGame(PawnsBoardModel m) {
    this.model = m;
    this.view.subscribe(this);
    this.view.makeVisible();
  }

  @Override
  public void placeCard(int row, int col) {
    //model.placeCardInPosition(row, col);
    view.refresh();
  }

  @Override
  public void quit() {
    System.exit(0);
  }
}
