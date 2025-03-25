package cs3500.pawnsboard.strategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadOnlyCell;
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
        if (player.getColor().equals(Color.red)) {
          for (int c = 0; c < board.get(0).size(); c++) {
            ReadOnlyCell cell = model.getCellAt(r, c);
            if (cell.isCardPlaceable() && cell.getOwnedColor().equals(Color.red)
                    && cell.getValue() >= player.getHand().get(h).getCost()) {
              return new Move(h, r, c, false);
            }
          }
        } else {
          for (int c = model.getWidth() - 1; c >= 0; c--) {
            ReadOnlyCell cell = model.getCellAt(r, c);
            if (cell.isCardPlaceable() && cell.getOwnedColor().equals(Color.blue)
                    && cell.getValue() >= player.getHand().get(h).getCost()) {
              return new Move(h, r, c, false);
            }
          }
        }
      }
    }

    return new Move(-1, -1, -1, true);
  }
}
