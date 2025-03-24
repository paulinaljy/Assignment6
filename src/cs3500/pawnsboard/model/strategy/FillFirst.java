package cs3500.pawnsboard.model.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.EmptyCell;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class FillFirst implements Strategy {

  private List<ArrayList<Cell>> board;
  private Player player;
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    this.board = model.getBoard();
    this.player = player;

    for (int h = 0; h < player.getHandSize(); h++) {
      for (int r = 0; r < board.size(); r++) {
        for (int c = 0; c < board.get(0).size(); c++) {
          if (model.getBoard().get(r).get(c).isCardPlaceable()) {
            return new Move(h, c, r, false);
          }
        }
      }
    }

    return new Move(0, 0, 0, true);
  }
}
