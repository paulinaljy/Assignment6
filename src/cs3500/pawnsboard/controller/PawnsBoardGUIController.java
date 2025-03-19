package cs3500.pawnsboard.controller;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view1;
  private PawnsBoardView view2;

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
  }

  @Override
  public void playGame() {
    this.view1.subscribe(this);
    this.view1.makeVisible();
    this.view2.subscribe(this);
    this.view2.makeVisible();
  }

  @Override
  public void placeCard(int cardIdx, int row, int col) {
    model.placeCardInPosition(cardIdx, row, col);
    model.drawNextCard();
    view1.refresh();
    view2.refresh();
  }

  @Override
  public void quit() {
    System.exit(0);
  }

  @Override
  public void pass() {
    model.pass();
    model.drawNextCard();
    view1.refresh();
    view2.refresh();
  }
}
