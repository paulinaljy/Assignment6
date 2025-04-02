package cs3500.pawnsboard.player;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;
import cs3500.pawnsboard.view.ViewActions;

/**
 * Represents a HumanPlayer in the PawnsBoard game.
 */
public class HumanPlayer implements GamePlayer {
  private ReadonlyPawnsBoardModel model;
  private int playerID;

  public HumanPlayer(ReadonlyPawnsBoardModel model, int playerID) {
    this.model = model;
    this.playerID = playerID;
  }
  @Override
  public void chooseMove() {
    return;
  }

  @Override
  public boolean isHumanPlayer() {
    return true;
  }

  @Override
  public int getPlayerID() {
    return playerID;
  }

  @Override
  public void subscribe(ViewActions observer) {

  }

  @Override
  public String toString() {
    return "Player " + playerID;
  }
}
