package cs3500.pawnsboard.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.strategy.Strategy;
import cs3500.pawnsboard.view.ViewActions;

public class MachinePlayer implements GamePlayer {
  private int playerID;
  private ReadonlyPawnsBoardModel model;
  private ViewActions observer; // interface
  private Strategy strategy;

  public MachinePlayer(ReadonlyPawnsBoardModel model, Strategy strategy, int playerID) {
    this.model = model;
    this.strategy = strategy;
    this.playerID = playerID;
  }

  @Override
  public void chooseMove() {
    Player currentPlayer = model.getCurrentPlayer();
    Move move = strategy.chooseMove(model, currentPlayer);
    if (move.isPass()) {
      observer.pass();
    } else {
      observer.setCardIdx(move.getCardIdx());
      observer.setSelectedCell(move.getRow(), move.getCol());
      observer.placeCard();
    }
  }

  @Override
  public boolean isHumanPlayer() {
    return false;
  }

  @Override
  public int getPlayerID() {
    return playerID;
  }

  @Override
  public void subscribe(ViewActions observer) {
    this.observer = observer;
  }
}
