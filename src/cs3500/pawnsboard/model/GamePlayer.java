package cs3500.pawnsboard.model;

import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.view.ViewActions;

/**
 * Interface that defines the actions available to all players (Human and AI).
 */
public interface GamePlayer {
  void chooseMove();

  boolean isHumanPlayer();

  int getPlayerID();

  void subscribe(ViewActions observer);
}
