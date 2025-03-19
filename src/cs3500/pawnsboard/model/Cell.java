package cs3500.pawnsboard.model;

import java.awt.Color;

/**
 * Represents the behaviors of a cell in the board, either an empty cell, pawns, or game card.
 */
public interface Cell extends ReadOnlyCell {

  /**
   * Updates the cell based on the influence of the card and the given player.
   * @param currentPlayer the current player of the game
   * @return new cell that is updated
   */
  Cell influence(Player currentPlayer);
}