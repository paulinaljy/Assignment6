package cs3500.pawnsboard.model;

import java.util.List;

/**
 * Represents behaviors of a QueensBlood game.
 */
public interface QueensBlood {
  /**
   * Updates the player turn.
   *
   * @throws IllegalStateException if the game has not started
   */
  void setNextPlayer();

  /**
   * Returns the current player.
   *
   * @return the current player.
   * @throws IllegalStateException if the game has not started
   */
  Player getCurrentPlayer();

  /**
   * Returns true if the game is over.
   *
   * @return true if the game is over, false otherwise
   * @throws IllegalStateException if the game has not started
   */
  boolean isGameOver();

  /**
   * Places a card from the hand to a given position on the board and then draws a card from the
   * deck if able.
   *
   * @param cardIdx index of the card in hand to place (0-index based)
   * @param row     row to place the card in (0-index based)
   * @param col     column to place the card in (0-index based)
   * @throws IllegalArgumentException if cardIdx is out of bounds of the hand
   * @throws IllegalArgumentException if row or column do not indicate a position on the board
   * @throws IllegalStateException    if the game hasn't started
   * @throws IllegalStateException    if the given position does not contain pawns
   * @throws IllegalStateException    if the given card and position does not contain pawns the
   *                                  player owns
   * @throws IllegalStateException    if the player does not have enough pawns
   *                                  to cover the cost of the card
   */
  void placeCardInPosition(int cardIdx, int row, int col);

  /**
   * Current player's turn ends and switches to the other player.
   *
   * @throws IllegalStateException if the game hasn't started
   */
  void pass();

  /**
   * Current player draws the next card from the deck and adds it to their hand.
   *
   * @throws IllegalStateException if the game has not started
   */
  void drawNextCard();

  /**
   * Starts the game with the given deck and hand size. Players are dealt equal number of cards to
   * their hands randomly from their decks.
   *
   * @param p1Deck   player 1 list of cards to play the game with
   * @param p2Deck   player 2 list of cards to play the game with
   * @param handSize maximum hand size for the game
   * @param shuffle  whether the deck should be shuffled
   * @throws IllegalStateException    if the game has already been started
   * @throws IllegalArgumentException if either decks are null or contains a null object,
   *                                  or if the deck does not contain enough cards to fill the board
   * @throws IllegalArgumentException if handSize is not positive (i.e. 0 or less) or is greater
   *                                  than a third of the deck size
   */

  void startGame(List<GameCard> p1Deck, List<GameCard> p2Deck, int handSize, boolean shuffle);

  /**
   * Retrieve the number of cards that make up the width of the board.
   * (e.g. the number of columns in the widest row)
   *
   * @return the width of the board
   */
  int getWidth();

  /**
   * Retrieve the number of cards that make up the height of the board.
   * (e.g. the number of rows in the highest column)
   *
   * @return the height of the board
   */
  int getHeight();

  /**
   * Returns the type of cell in the indicated position on the board.
   *
   * @param row the row to access (0-index based)
   * @param col the column to access (0-index based)
   * @return the card in the valid position or null if the position has no card
   * @throws IllegalArgumentException if the row and column are not a valid location
   *                                  for a card in the board
   * @throws IllegalStateException    if the game has not started
   */
  Cell getCellAt(int row, int col);

  /**
   * Returns a copy of the player's current hand. If their hand is empty, then an empty
   * list is returned.
   *
   * @return a copy of the player's current hand
   * @throws IllegalStateException if the game has not started
   */
  List<GameCard> getHand();

  /**
   * Returns the winner of the game based on the player scores.
   *
   * @return the player with the highest score
   * @throws IllegalStateException if the game has not started
   */
  Player getWinner();

  /**
   * Returns the winning score of the game.
   *
   * @return the winning score of the game
   * @throws IllegalStateException if the game has not started
   */
  int getWinningScore();

  /**
   * Calculate the score of player 2 cells of the given row.
   *
   * @param row given row
   * @return the score of Player 2 based on the given row
   * @throws IllegalStateException if the game has not started
   */
  int getP2RowScore(int row);

  /**
   * Calculates the score of Player 1 based on the given row.
   *
   * @param row given row
   * @return the score of Player 1 of the given row
   * @throws IllegalStateException if the game has not started
   */
  int getP1RowScore(int row);
}
