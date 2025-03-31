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
   * Adds the observer to any listeners so actions on the view are
   * delegated to the observer.
   * @param observer observer
   */
  void subscribe(ViewActions observer);

  void reset();
}

