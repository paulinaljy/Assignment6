package cs3500.pawnsboard.controller;

import java.awt.event.KeyEvent;

import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.GameCardButton;
import cs3500.pawnsboard.view.PawnsBoardView;
import cs3500.pawnsboard.view.ViewActions;

public class PawnsBoardGUIController implements PawnsBoardController, ViewActions {

  private PawnsBoardModel model;
  private PawnsBoardView view;
  private GameCardButton gameCard;

  public PawnsBoardGUIController(PawnsBoardModel model, PawnsBoardView view) {
    if (view == null) {
      throw new IllegalArgumentException("Bad view");
    }
    if (model == null) {
      throw new IllegalArgumentException("Bad model");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void playGame() {
    this.view.subscribe(this);
    this.view.makeVisible();
  }

  @Override
  public void placeCard(int row, int col) {
    //model.placeCardInPosition(row, col);
    view.refresh();
  }

  @Override
  public void quit() {
    System.exit(0);
  }

  /**
   * Stores the selected card from hand to use when needed.
   *
   * @param card card selected
   */
  public void cardSelect(GameCardButton card) {
    this.gameCard = card;
  }

  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_Q: // Quit game
        System.out.println("Game Quit");
        quit();
        break;

      case KeyEvent.VK_ENTER: // Confirm move
        if (gameCard != null && gameCard != null) {
          // need to get the position model.placeCardInPosition(x, y);
          gameCard = null;
          view.refresh();
        } else {
          System.out.println("Cannot confirm: No card selected or no board cell selected.");
        }
        break;

      case KeyEvent.VK_SPACE: // Pass move
        System.out.println("Turn passed");
        model.pass();
        break;

      default:
        System.out.println("Key not valid");
        break;
    }
  }

}
