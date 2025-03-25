package cs3500.pawnsboard.strategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.Position;
import cs3500.pawnsboard.model.ReadOnlyCell;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class ControlBoard implements Strategy {
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    int maxCount = 0;
    Point maxCellPosition = null;
    int maxCardIdx = -1;

    List<GameCard> hand = player.getHand();
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        int newCol = col;
        if (player.getColor().equals(Color.blue)) {
          newCol = model.getWidth() - 1 - col;
        }
        ReadOnlyCell cell = model.getCellAt(row, newCol);
        if (!cell.isCardPlaceable() || !(cell.getOwnedColor().equals(player.getColor()))) {
          continue; // if card is not pawns, move to next cell
        }

        for (int h = 0; h < hand.size(); h++) {
          GameCard card = hand.get(h);
          int cardCost = card.getCost(); // gets cost of card
          if (cell.getValue() < cardCost) {
            continue; // move on to next card in hand
          }

          List<Position> influencedCells = card.getPositions();
          int netCount = getNetCount(model, influencedCells, row, newCol, player);
          if (netCount > maxCount) {
            maxCount = netCount; // replaces max net count with current net count
            maxCardIdx = h;
            maxCellPosition = new Point(row, newCol);
          }
        }
      }
    }
    if (maxCount > 0) {
      return new Move(maxCardIdx, (int)maxCellPosition.getX(), (int)maxCellPosition.getY(), false);
    } else {
      return new Move(-1, -1, -1, true);
    }
  }

  private int getNetCount(ReadonlyPawnsBoardModel model, List<Position> influencedCells, int row, int col, Player currentPlayer) {
    int netCount = 0;
    for (int i = 0; i < influencedCells.size(); i++) {
      int rowPosition = influencedCells.get(i).getRowDelta() + row;
      int colPosition = influencedCells.get(i).getColDelta() + col;
      if (rowPosition >= 0 && rowPosition < model.getHeight()
              && colPosition >= 0 && colPosition < model.getWidth()) {
        ReadOnlyCell cell = model.getBoard().get(rowPosition).get(colPosition);
        if (cell.isCardPlaceable()) { // is pawns
          if (!(cell.getOwnedColor().equals(currentPlayer.getColor()))) {
            netCount += 1; // take ownership of other player's pawns
          }
        } else if (!cell.isGameCard()) { // is empty
          netCount += 1; // add to net count
        }
      }
    }
    return netCount;
  }
}
