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
  private ReadOnlyGameCard card;
  private int cardIdx;

  public GameCardButton(ReadonlyPawnsBoardModel pawnsBoardModel, ReadOnlyGameCard card) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.pawnsBoardModel = pawnsBoardModel;
    this.card = card;

    setFocusPainted(false);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setOpaque(true);
    setBackground(pawnsBoardModel.getCurrentPlayer().getColor());
    setPreferredSize(new Dimension(120, 200));

    cardPanel = new GameCardPanel(pawnsBoardModel, card);
    cardPanel.setOpaque(false);
    add(cardPanel);
  }

  public int getIndexID() {
    return cardIdx;
  }
}
