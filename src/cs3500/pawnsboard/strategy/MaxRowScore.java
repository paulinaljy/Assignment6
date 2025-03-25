package cs3500.pawnsboard.strategy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.EmptyCell;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadOnlyCell;
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

    int currentPlayerID = 1; // player 1
    int otherPlayerID = 2; // player 2
    if (player.getColor().equals(Color.BLUE)) {
      currentPlayerID = 2; // player 2
      otherPlayerID = 1; // player 1
    }

    List<GameCard> hand = new ArrayList<>(model.getHand(currentPlayerID));
    hand.sort(Comparator.comparingInt(GameCard::getValue).reversed()); // sort hand by order of card value - highest to lowest

    for (int row = 0; row < board.size(); row++) {
      int score = (currentPlayerID == 1) ? model.getP1RowScore(row) : model.getP2RowScore(row);
      int otherScore = (currentPlayerID == 1) ? model.getP2RowScore(row) : model.getP1RowScore(row);
      if (score > otherScore) { // if current player row score > other player's row score
        continue; // move to next row
      }

      for (int col = 0; col < board.get(row).size(); col++) {
        int newCol = col;
        if (currentPlayerID == 2) {
          newCol = model.getWidth() - 1 - col;
        }
        ReadOnlyCell cell = model.getCellAt(row, newCol);
        if (!cell.isCardPlaceable() || !(cell.getOwnedColor().equals(player.getColor()))) { // if cell not placeable
          continue; // move on to next cell
        }

        for (int h = 0; h < hand.size(); h++) {
          GameCard card = hand.get(h); // gets card in hand
          int cardValue = card.getValue(); // gets value of card
          int cardCost = card.getCost(); // gets cost of card
          if (cell.getValue() < cardCost) {
            continue; // move on to next card in hand
          }
          if ((score + cardValue) > otherScore) {
            int realHandIndex = model.getHand(currentPlayerID).indexOf(card);
            return new Move(realHandIndex, row, newCol, false);
          }
        }
      }
    }
    return new Move(-1, -1, -1, true);
  }
}
