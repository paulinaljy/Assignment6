package cs3500.pawnsboard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.controller.PawnsBoardGUIController;
import cs3500.pawnsboard.controller.DeckConfiguration;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.controller.PawnsBoardDeckConfig;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardFrame;

/**
 * Represents a PawnsBoard game.
 */
public final class PawnsBoardGame {
  /**
   * Runs the PawnsBoardGame given the arguments.Initialize a model with a board size of 5 rows by
   * 7 columns. Reads a deck configuration file and creates a deck of card for player 1 and player
   * 2. Starts the game with the two decks of card and hand size of 5. Displays the GUI view of each
   * player, each with their respective hand. Player 1 window is displayed on the left and player 2
   * window is displayed on the right.
   * @param args an array of string with the arguments entered by the user
   */
  public static void main(String[] args) throws IOException {
    DeckConfiguration deckConfig = new PawnsBoardDeckConfig();
    PawnsBoardModel model = new PawnsBoardModel(5, 7, new Random(), deckConfig);
    String path = "docs" + File.separator + "gameDeck.config";
    File config = new File(path);
    List<GameCard> p1Deck = deckConfig.loadDeckConfig(new FileReader(config));
    List<GameCard> p2Deck = deckConfig.loadDeckConfig(new FileReader(config));
    model.startGame(p1Deck, p2Deck, 5, false);

    PawnsBoardFrame view1 = new PawnsBoardFrame(model, 1);
    PawnsBoardFrame view2 = new PawnsBoardFrame(model, 2);
    view2.setLocation(view1.getX() + view1.getWidth(), view1.getY());
    view1.setVisible(true);
    view2.setVisible(true);
    PawnsBoardGUIController controller = new PawnsBoardGUIController(model, view1, view2);
    controller.playGame();
  }
}
