package cs3500.pawnsboard;

import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;
import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.strategy.Strategy;

/**
 * New Strategy that always passes.
 */
public class MockPassStrategy implements Strategy {
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    return new Move(-1, -1, -1, true);
  }
}
