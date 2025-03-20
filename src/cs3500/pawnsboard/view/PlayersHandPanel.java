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
  private int playerID;

  public PlayersHandPanel(ReadonlyPawnsBoardModel pawnsBoardModel, int playerID) {
    super();
    this.playerID = playerID;
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.pawnsBoardModel = pawnsBoardModel;

    setLayout(new GridLayout(1, 1));
    setBackground(Color.white);
    //setLayout(new FlowLayout(FlowLayout.LEFT));
    createHand();
  }

  private void createHand() {
    this.playersHand = new ArrayList<GameCardButton>();
    ArrayList<GameCard> playersHand = new ArrayList<GameCard>(pawnsBoardModel.getHand(playerID));
    for (int i = 0; i < playersHand.size(); i++) {
      createNewCard(i);
      //GameCardButton cardButton = new GameCardButton(pawnsBoardModel, i, playerID);
      /*cardButton.addMouseListener(new MouseAdapter() {
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
      this.add(cardButton);*/
    }

    revalidate();
  }

  private void createNewCard(int cardIdx) {
    GameCardButton cardButton = new GameCardButton(pawnsBoardModel, cardIdx, playerID);
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

  public void reset() {
    //selectedCard.setLocation(selectedCard.getX(), selectedCard.getY() + 10);
    playersHand.remove(selectedCard); // remove from players hand
    this.remove(selectedCard); // remove from display
    selectedCard = null;
  }

  public void updateCard() {
    List<GameCard> gamePlayersHand = pawnsBoardModel.getHand(playerID);
    for (int i = 0; i < gamePlayersHand.size(); i++) {
      if (i >= playersHand.size()) {
        createNewCard(i);
      }
      playersHand.get(i).updateCard();
    }
  }

  public GameCardButton getSelectedCard() {
    return selectedCard;
  }

  public void subscribe(ViewActions observer) {
  }
}
