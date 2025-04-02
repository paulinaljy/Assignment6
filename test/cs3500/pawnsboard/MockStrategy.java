package cs3500.pawnsboard;

import java.util.Iterator;
import java.util.List;

import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;
import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.strategy.Strategy;

public class MockStrategy implements Strategy {
  private Iterator<Move> moveSequence;

  public MockStrategy(List<Move> moves) {
    this.moveSequence = moves.iterator();
  }
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    if (moveSequence.hasNext()) {
      return moveSequence.next();
    } else {
      return new Move(-1, -1, -1, true);
    }
  }
}
