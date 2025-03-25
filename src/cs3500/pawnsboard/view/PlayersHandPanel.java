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
  private ViewActions observer;

  public PlayersHandPanel(ReadonlyPawnsBoardModel pawnsBoardModel, int playerID) {
    super();
    this.playerID = playerID;
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.pawnsBoardModel = pawnsBoardModel;

    setLayout(new GridLayout(1, 1));
    setBackground(Color.white);
    createHand();
    highlightTurn();
  }

  /**
   *
   */
  private void createHand() {
    this.playersHand = new ArrayList<GameCardButton>();
    ArrayList<GameCard> playersHand = new ArrayList<GameCard>(pawnsBoardModel.getHand(playerID));
    for (int i = 0; i < playersHand.size(); i++) {
      createNewCard(i);
    }
    revalidate();
  }

  /**
   *
   * @param cardIdx
   */
  private void createNewCard(int cardIdx) {
    GameCardButton cardButton = new GameCardButton(pawnsBoardModel, cardIdx, playerID);
    this.add(cardButton);
    this.playersHand.add(cardButton);
  }

  /**
   *
   */
  private void highlightTurn() {
    if (pawnsBoardModel.getCurrentPlayerID() == this.playerID) {
      setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
    } else {
      setBorder(BorderFactory.createLineBorder(this.getBackground(), 0));
    }
  }

  /**
   *
   */
  public void refreshHand() {
    this.removeAll();
    this.playersHand.clear();
    this.createHand();
    this.highlightTurn();
    if (observer != null) {
      for (int i = 0; i < this.playersHand.size(); i++) {
        GameCardButton cardButton = this.playersHand.get(i);
        cardButton.addMouseListener(new PlayersHandMouseListener(observer, cardButton));
      }
    }
  }

  /**
   *
   * @return
   */
  public GameCardButton getSelectedCard() {
    return selectedCard;
  }

  /**
   *
   * @param observer
   */
  public void subscribe(ViewActions observer) {
    this.observer = observer;
    GameCardButton cardButton;
    for (int i = 0; i < this.playersHand.size(); i++) {
      cardButton = this.playersHand.get(i);
      cardButton.addMouseListener(new PlayersHandMouseListener(observer, cardButton));
    }
  }

  /**
   *
   */
  class PlayersHandMouseListener extends MouseAdapter {
    private ViewActions observer;
    private GameCardButton cardButton;

    public PlayersHandMouseListener(ViewActions observer, GameCardButton cardButton) {
      this.observer = observer;
      this.cardButton = cardButton;
    }

    public void mouseClicked(MouseEvent evt) {
      if (pawnsBoardModel.isGameOver()) {
        return;
      }
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

      if (selectedCard != null) {
        observer.setCardIdx(selectedCard.getIndexID());
      }
      getTopLevelAncestor().requestFocus();
    }
  }
}
