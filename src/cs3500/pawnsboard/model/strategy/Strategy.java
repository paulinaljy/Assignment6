package cs3500.pawnsboard.model.strategy;

import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public interface Strategy {

  Move chooseMove(ReadonlyPawnsBoardModel model, Player player);
}
