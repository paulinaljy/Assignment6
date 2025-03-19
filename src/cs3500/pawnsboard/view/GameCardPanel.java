package cs3500.pawnsboard.view;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import cs3500.pawnsboard.model.ReadOnlyGameCard;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class GameCardPanel extends JPanel {
  private final ReadonlyPawnsBoardModel pawnsBoardModel;
  private JLabel name;
  private JLabel cost;
  private JLabel value;
  private JPanel influenceGrid;
  private int cardIdx;

  public GameCardPanel(ReadonlyPawnsBoardModel pawnsBoardModel, int cardIdx) {
    super();
    if (pawnsBoardModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.pawnsBoardModel = pawnsBoardModel;
    this.cardIdx = cardIdx;

    setBackground(pawnsBoardModel.getCurrentPlayer().getColor());
    setPreferredSize(new Dimension(120, 200));

    // text panel for name, cost value
    JPanel cardInfo = new JPanel();
    cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
    cardInfo.setOpaque(false);

    ReadOnlyGameCard card = pawnsBoardModel.getHand().get(cardIdx);
    name = new JLabel(card.getName());
    cost = new JLabel("Cost: " + card.getCost());
    value = new JLabel("Value: " + card.getValue());

    cardInfo.add(name);
    cardInfo.add(cost);
    cardInfo.add(value);

    // grid panel for influence grid
    influenceGrid = new JPanel(new GridLayout(5, 5));
    influenceGrid.setPreferredSize(new Dimension(100, 100));
    influenceGrid.setBackground(Color.DARK_GRAY);
    drawGrid();

    add(cardInfo, BorderLayout.NORTH);
    add(influenceGrid, BorderLayout.CENTER);
  }

  public void updateCard() {
    ReadOnlyGameCard card = pawnsBoardModel.getHand().get(cardIdx);
    setBackground(pawnsBoardModel.getCurrentPlayer().getColor());
    setOpaque(true);

    name.setText(card.getName());
    cost.setText("Cost: " + card.getCost());
    value.setText("Value: " + card.getValue());

    updateGrid();

    revalidate();
    repaint();
  }

  private void updateGrid() {
    ReadOnlyGameCard card = pawnsBoardModel.getHand().get(cardIdx);
    int gridIdx = 0;
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Component cell = this.influenceGrid.getComponent(gridIdx);
        if (row == 2 && col == 2) {
          cell.setBackground(Color.orange);
        } else if (card.isCellInfluencedAt(row, col)) {
          cell.setBackground(Color.CYAN);
        } else {
          cell.setBackground(Color.DARK_GRAY);
        }

        gridIdx++;
      }
    }
    influenceGrid.revalidate();
  }

  private void drawGrid() {
    ReadOnlyGameCard card = pawnsBoardModel.getHand().get(cardIdx);
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        JPanel cell = new JPanel();
        cell.setBorder(BorderFactory.createLineBorder(Color.black));
        if (row == 2 && col == 2) {
          cell.setBackground(Color.orange);
        } else if (card.isCellInfluencedAt(row, col)) {
          cell.setBackground(Color.CYAN);
        } else {
          cell.setBackground(Color.DARK_GRAY);
        }
        influenceGrid.add(cell);
      }
    }
    influenceGrid.revalidate();
  }
}
