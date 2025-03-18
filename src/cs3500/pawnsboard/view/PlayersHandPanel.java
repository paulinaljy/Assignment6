package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PlayersHandPanel extends JPanel {

  private GameCardButton selectedCard = null;
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
      GameCardButton cardButton = new GameCardButton(pawnsBoardModel, card);
      cardButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (selectedCard == cardButton) {
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10);
            selectedCard = null;
          } else {
            if (selectedCard != null) {
              selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10);
            }

            selectedCard = cardButton;
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() - 10);
          }
        }
      });
      this.add(cardButton);
    }

    revalidate();
  }
}
