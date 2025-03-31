package cs3500.pawnsboard.view;

/**
 * A view for PawnsBoard: display the game board and provide visual interface for users.
 */
public interface PawnsBoardView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Subscribes to the observer who should be notified about events.
   * @param observer the ViewActions observer that should be notified
   */
  void subscribe(ViewActions observer);

  /**
   * Resets the selected cells on the GUI view game board.
   */
  void reset();
}

