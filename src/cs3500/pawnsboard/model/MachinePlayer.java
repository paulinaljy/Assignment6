package cs3500.pawnsboard.model;

import java.awt.*;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.strategy.Move;
import cs3500.pawnsboard.strategy.Strategy;

public class MachinePlayer extends Player implements PlayerActions {
  private Color color;
  private List<GameCard> deck;
  private int handSize;
  private Random rand;
  private boolean shuffle;
  private Strategy strategy;

  public MachinePlayer(Color color, List<GameCard> deck,
                       int handSize, Random rand, boolean shuffle, Strategy strategy) {
    super(color, deck, handSize, rand, shuffle);
    this.strategy = strategy;
  }
  @Override
  public Move chooseMove(ReadonlyPawnsBoardModel model, Player player) {
    return null;
  }
}
