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
      for (int row = 0; row < board.size(); row++) {
        for (int col = 0; col < board.get(row).size(); col++) {
          int newCol = col;
          if (player.getColor().equals(Color.blue)) {
            newCol = model.getWidth() - 1 - col;
          }
          ReadOnlyCell cell = model.getCellAt(row, newCol);
          if (cell.isCardPlaceable() && cell.getOwnedColor().equals(player.getColor())
                  && cell.getValue() >= player.getHand().get(h).getCost()) {
            return new Move(h, row, newCol, false);
          }
        }
      }
    }

    return new Move(-1, -1, -1, true);
  }
}
