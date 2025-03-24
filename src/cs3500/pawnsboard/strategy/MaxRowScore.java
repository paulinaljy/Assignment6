package cs3500.pawnsboard.strategy;

import java.awt.Color;
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
    int otherId = 0;
    if (player.getColor().equals(Color.RED)) {
      id = 1;
      otherId = 2;
    } else {
      id = 2;
      otherId = 1;
    }

    List<GameCard> hand = new ArrayList<>(model.getHand(id));

    //sort hand by order of card value - highest to lowest
    hand.sort(Comparator.comparingInt(GameCard::getValue).reversed());


    for (int row = 0; row < board.size(); row++) {
      int score = (id == 1) ? model.getP1RowScore(row) : model.getP2RowScore(row);
      int otherScore = (id == 1) ? model.getP2RowScore(row) : model.getP1RowScore(row);

      if (score > otherScore) {
        continue;
      }

      for (int col = 0; col < board.get(row).size(); col++) {
        if (!board.get(row).get(col).isCardPlaceable()) {
          continue;
        }

        for (int h = 0; h < hand.size(); h++) {
          GameCard card = hand.get(h);
          int cardValue = card.getValue();

          if ((score + cardValue) > otherScore) {
            int realHandIndex = model.getHand(id).indexOf(card);
            return new Move(realHandIndex, col, row, false);
          }
        }
      }
    }

    return new Move(-1, -1, -1, true);
  }
}
