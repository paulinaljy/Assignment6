package cs3500.pawnsboard.controller;

import cs3500.pawnsboard.model.PawnsBoardModel;

/**
 * Represents a Controller for PawnsBoard: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface PawnsBoardController {

  /**
   * Execute a single game of pawns board given a PawnsBoard Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null tic tac toe Model
   */
  void playGame(PawnsBoardModel m);

}
