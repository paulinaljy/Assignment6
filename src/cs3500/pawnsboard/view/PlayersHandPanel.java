package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.pawnsboard.controller.PawnsBoardGUIController;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PlayersHandPanel extends JPanel {

  private GameCardButton selectedCard = null;
  private final ReadonlyPawnsBoardModel pawnsBoardModel;
  private final PawnsBoardGUIController controller;

  public PlayersHandPanel(ReadonlyPawnsBoardModel pawnsBoardModel,
                          PawnsBoardGUIController controller) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }

    this.pawnsBoardModel = pawnsBoardModel;
    this.controller = controller;

    setLayout(new FlowLayout(FlowLayout.LEFT));
    createHand();
  }

  private void createHand() {
    ArrayList<GameCard> playersHand = new ArrayList<GameCard>(pawnsBoardModel.getHand());
    for (GameCard card : playersHand) {
      GameCardButton cardButton = new GameCardButton(pawnsBoardModel, card);
      cardButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (selectedCard == cardButton) { // if selected card that is already selected
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10); // moves card down
            selectedCard = null; // deselect card
            controller.cardSelect(selectedCard);
          } else {
            if (selectedCard != null) { // if a card is already selected + select different card
              selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10); // move originally selected card down
            }

            selectedCard = cardButton; // set selected card to current card
            // move current card up
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() - 10);
            controller.cardSelect(selectedCard);
          }
        }
      });
      this.add(cardButton);
    }

    revalidate();
  }
}
