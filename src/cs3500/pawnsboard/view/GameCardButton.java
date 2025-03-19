package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import cs3500.pawnsboard.model.ReadOnlyGameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class GameCardButton extends JButton {
  private final ReadonlyPawnsBoardModel pawnsBoardModel;
  private GameCardPanel cardPanel;
  private int cardIdx;

  public GameCardButton(ReadonlyPawnsBoardModel pawnsBoardModel, int cardIdx) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.pawnsBoardModel = pawnsBoardModel;
    this.cardIdx = cardIdx;

    setFocusPainted(false);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setOpaque(true);
    setBackground(pawnsBoardModel.getCurrentPlayer().getColor());
    setPreferredSize(new Dimension(120, 200));

    cardPanel = new GameCardPanel(pawnsBoardModel, cardIdx);
    cardPanel.setOpaque(false);
    add(cardPanel);
  }

  public void updateCard() {
    cardPanel.updateCard();
  }

  public int getIndexID() {
    return cardIdx;
  }
}
