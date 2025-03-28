package cs3500.pawnsboard.view;

/**
 * Represents a ViewActions interface that make up the game play actions of the game, including
 * placing a card, quitting, passing, setting a card index, and setting a selected cell.
 */
public interface ViewActions {

  /**
   * Places the card on the game board given the card index, row, and column.
   */
  void placeCard();

  /**
   * Quits the game.
   */
  void quit();

  /**
   * Passes to the next player's turn.
   */
  void pass();

  /**
   * Sets the selected card index.
   * @param cardIdx card index of selected card in player's hand (0-index)
   */
  void setCardIdx(int cardIdx);

  /**
   * Sets the selected cell in the game board.
   * @param row row of the selected cell (0-index)
   * @param col col of the selected cell (0-index)
   */
  void setSelectedCell(int row, int col);

  boolean isViewEnabled();

}

