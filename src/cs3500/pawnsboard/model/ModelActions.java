package cs3500.pawnsboard.model;

public interface ModelActions {

  void itsYourTurn();

  void refreshView();
  // holds state of game
  // something changed => notifies controller to refresh view
}