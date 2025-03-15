package cs3500.pawnsboard.model;

import java.util.List;

/**
 * Represents an interface with behaviors to load a deck.
 */
public interface DeckConfiguration {
  /**
   * Loads and returns a deck of game cards based on the given readable.
   * @param readable the readable
   * @return a deck of game cards
   */
  public List<GameCard> loadDeckConfig(Readable readable);
}