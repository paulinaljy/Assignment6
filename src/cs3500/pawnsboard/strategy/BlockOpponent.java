package cs3500.pawnsboard.strategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.Position;
import cs3500.pawnsboard.model.ReadOnlyCell;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class BlockOpponent implements Strategy {
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    Player opponent = model.getPlayerByColor(Color.red);
    if (player.getColor().equals(Color.red)) {
      opponent = model.getPlayerByColor(Color.blue);
    }

    ArrayList<Strategy> opponentStrategies = new ArrayList<Strategy>();
    opponentStrategies.add(new ControlBoard());
    opponentStrategies.add(new MaxRowScore());
    opponentStrategies.add(new FillFirst());

    Move maxBlockMove = null;
    for (Strategy strategy : opponentStrategies) {
      Move nextMove = strategy.chooseMove(model, opponent);
      if (!nextMove.isPass()) {
        maxBlockMove = findInfluenceableCell(model, nextMove.getRow(), nextMove.getCol(), player);
        if (maxBlockMove != null) {
          return maxBlockMove;
        }
      }
    }
    return new Move(-1, -1, -1, true);
  }

  private Move findInfluenceableCell(ReadonlyPawnsBoardModel model, int targetRow, int targetCol, Player player) {
    for (int h = 0; h < player.getHand().size(); h++) {
      GameCard card = player.getHand().get(h);
      List<Position> influencedCells = card.getPositions();
      for (int row = 0; row < model.getHeight(); row++) {
        for (int col = 0; col < model.getWidth(); col++) {
          int newCol = col;
          if (player.getColor().equals(Color.blue)) {
            newCol = model.getWidth() - 1 - col;
          }
          ReadOnlyCell cell = model.getCellAt(row, newCol);
          if (!cell.isCardPlaceable() || !(cell.getOwnedColor().equals(player.getColor()))
                  || cell.getValue() < player.getHand().get(h).getCost()) {
            continue;
          }
          for (Position pos : influencedCells) {
            int rowPosition = pos.getRowDelta() + row;
            int colPosition = pos.getColDelta() + newCol;
            if (rowPosition == targetRow && colPosition == targetCol) {
              return new Move(h, row, newCol, false);
            }
          }
        }
      }
    }
    return null;
  }
}

