package cs3500.pawnsboard.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PlayersHandPanel extends JPanel {

  private final ReadonlyPawnsBoardModel pawnsBoardModel;

  public PlayersHandPanel(ReadonlyPawnsBoardModel pawnsBoardModel) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.pawnsBoardModel = pawnsBoardModel;

    setLayout(new FlowLayout(FlowLayout.LEFT));
    drawHand();
  }

  private void drawHand() {
    ArrayList<GameCard> playersHand = new ArrayList<GameCard>(pawnsBoardModel.getHand());
    for (GameCard card : playersHand) {
      this.add(new GameCardButton(pawnsBoardModel, card));
    }

    revalidate();
  }
}
