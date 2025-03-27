package cs3500.pawnsboard.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.strategy.Move;

public class HumanPlayer extends Player implements PlayerActions {
  private Color color;
  private List<GameCard> deck;
  private int handSize;
  private Random rand;
  private boolean shuffle;

  public HumanPlayer(Color color, List<GameCard> deck, int handSize, Random rand, boolean shuffle) {
    super(color, deck, handSize, rand, shuffle);
  }
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    return null;
  }
}
