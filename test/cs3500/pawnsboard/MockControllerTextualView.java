package cs3500.pawnsboard;

import java.awt.*;

import cs3500.pawnsboard.controller.PawnsBoardController;
import cs3500.pawnsboard.model.GamePlayer;
import cs3500.pawnsboard.model.ModelActions;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardTextualView;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class MockControllerTextualView implements PawnsBoardController, ViewActions, ModelActions {
  private PawnsBoardModel model;
  private PawnsBoardTextualView view;
  private GamePlayer player;
  private Appendable transcript;
  private int cardIdxSelected;
  private Point cellSelected;
  private boolean viewEnabled;

  /**
   * Constructs a textual controller for the game.
   *
   * @param model model
   * @param view player's textual view
   * @param player player
   * @throws IllegalArgumentException if model or view is null.
   */
  public MockControllerTextualView(PawnsBoardModel model, GamePlayer player, PawnsBoardTextualView view) {
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
    this.model.subscribe(this, player.getPlayerID()); // model subscribed to controller
    this.player.subscribe(this); // player (machine) subscribed to controller
  }

  @Override
  public void placeCard() {

  }

  @Override
  public void quit() {

  }

  @Override
  public void pass() {

  }

  @Override
  public void setCardIdx(int cardIdx) {

  }

  @Override
  public void setSelectedCell(int row, int col) {

  }

  @Override
  public boolean isViewEnabled() {
    return false;
  }

  @Override
  public void itsYourTurn() {

  }

  @Override
  public void refreshView() {

  }
}
