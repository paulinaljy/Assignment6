package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PlayersHandPanel extends JPanel {

  private GameCardButton selectedCard = null;
  private List<GameCardButton> playersHand;
  private final ReadonlyPawnsBoardModel pawnsBoardModel;

  public PlayersHandPanel(ReadonlyPawnsBoardModel pawnsBoardModel) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.pawnsBoardModel = pawnsBoardModel;

    //setLayout(new GridLayout(1, 1));
    setLayout(new FlowLayout(FlowLayout.LEFT));
    createHand();
  }

  private void createHand() {
    this.playersHand = new ArrayList<GameCardButton>();
    ArrayList<GameCard> playersHand = new ArrayList<GameCard>(pawnsBoardModel.getHand());
    for (int i = 0; i < playersHand.size(); i++) {
      GameCardButton cardButton = new GameCardButton(pawnsBoardModel, i);
      cardButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (selectedCard == cardButton) { // if selected card that is already selected
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10); // moves card down
            selectedCard = null; // deselect card
          } else {
            if (selectedCard != null) { // if a card is already selected + select different card
              selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10); // move originally selected card down
            }

            selectedCard = cardButton; // set selected card to current card
            // move current card up
            selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() - 10);
          }

          getTopLevelAncestor().requestFocus();
        }
      });
      this.playersHand.add(cardButton);
      this.add(cardButton);
    }

    revalidate();
  }

  public void reset() {
    selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10);
    selectedCard = null;
  }

  public void updateCard() {
    for (int i = 0; i < playersHand.size(); i++) {
      playersHand.get(i).updateCard();
    }
  }

  public GameCardButton getSelectedCard() {
    return selectedCard;
  }

  public void subscribe(ViewActions observer) {
  }
}
