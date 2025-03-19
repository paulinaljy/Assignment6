package cs3500.pawnsboard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import cs3500.pawnsboard.controller.PawnsBoardGUIController;
import cs3500.pawnsboard.model.DeckConfiguration;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.PawnsBoardDeckConfig;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.view.PawnsBoardFrame;

/**
 * Represents a PawnsBoard game.
 */
public class PawnsBoard {
  /**
   * Runs the PawnsBoardGame given the arguments.Initialize a model with a board size of 3 rows by
   * 5 columns. Reads a deck configuration file and creates a deck of card for player 1 and player
   * 2. Starts the game with the two decks of card and hand size of 5.
   * @param args an array of string with the arguments entered by the user
   */
  public static void main(String[] args) throws IOException {
    DeckConfiguration deckConfig = new PawnsBoardDeckConfig();
    PawnsBoardModel model = new PawnsBoardModel(5, 3, new Random(), deckConfig);
    String path = "docs" + File.separator + "deck.config";
    File config = new File(path);
    List<GameCard> p1Deck = deckConfig.loadDeckConfig(new FileReader(config));
    List<GameCard> p2Deck = deckConfig.loadDeckConfig(new FileReader(config));
    model.startGame(p1Deck, p2Deck, 5, false);

    PawnsBoardFrame view = new PawnsBoardFrame(model);
    PawnsBoardGUIController controller = new PawnsBoardGUIController(model, view);
    controller.playGame();
  }
}
