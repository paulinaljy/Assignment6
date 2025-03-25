package cs3500.pawnsboard.view;

import java.awt.Point;

/**
 * Interface for the PawnsBoardPanel.
 */
public interface IntPawnsBoardPanel extends CommonView {
  Point getSelectedBoardCell();
  void reset();
}
