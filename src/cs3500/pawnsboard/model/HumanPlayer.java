package cs3500.pawnsboard.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.controller.PawnsBoardPlayerController;
import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.view.ViewActions;

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
