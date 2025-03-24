package cs3500.pawnsboard.model.strategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.EmptyCell;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;
import cs3500.pawnsboard.model.GameCard;

public class MaxRowScore implements Strategy {
  private List<ArrayList<Cell>> board;
  private Player player;
  EmptyCell e = new EmptyCell();

  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    this.board = model.getBoard();
    this.player = player;

    int id = 0;
    if (player.getColor().equals(Color.RED)) {
      id = 1;
    } else {
      id = 2;
    }

    int value = -1;

    //sort hand by order of card value - highest to lowest
    model.getHand(id).sort(Comparator.comparingInt(GameCard::getValue).reversed());

    for (int h = 0; h < model.getHand(id).size(); h++) {
      for (int r = 0; r < board.size(); r ++) {
        for (int c = 0; c < board.get(r).size(); c++) {
          if (model.getBoard().get(r).get(c).isCardPlaceable()) {
            return new Move(h, c, r, false);
          }
        }
      }
    }

    return new Move(-1, -1, -1, true);
  }
}
