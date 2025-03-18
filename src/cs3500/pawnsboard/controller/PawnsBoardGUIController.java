package cs3500.pawnsboard.controller;

import java.awt.event.KeyEvent;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.GameCardButton;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view;
  private GameCardButton gameCard;

  public PawnsBoardGUIController(PawnsBoardModel model, PawnsBoardView view) {
    if (view == null) {
      throw new IllegalArgumentException("Bad view");
    }
    if (model == null) {
      throw new IllegalArgumentException("Bad model");
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
  public void placeCard(int row, int col) {
    //model.placeCardInPosition(row, col);
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
