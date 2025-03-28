package cs3500.pawnsboard.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.controller.PawnsBoardPlayerController;
import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.view.ViewActions;

public class HumanPlayer implements GamePlayer {
  private ReadonlyPawnsBoardModel model;
  private int player;
  private ViewActions observer; // interface

  public HumanPlayer(ReadonlyPawnsBoardModel model, int player) {
    this.model = model;
    this.player = player;
  }
  @Override
  public void chooseMove() {
    return;
  }

  @Override
  public boolean isHumanPlayer() {
    return true;
  }
}
