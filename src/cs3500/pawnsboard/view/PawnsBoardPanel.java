package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.*;

import cs3500.pawnsboard.controller.PawnsBoardGUIController;
import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.Pawns;
import cs3500.pawnsboard.model.ReadOnlyCell;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PawnsBoardPanel extends JPanel {

  private final ReadonlyPawnsBoardModel model;
  private Point selectedBoardCell;

  public PawnsBoardPanel(ReadonlyPawnsBoardModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
    this.selectedBoardCell = null;
  }

  /**
   * Draws the game board, including the cells with lines and score on each side of the board.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g.create();

    int height = model.getHeight();
    int width = model.getWidth();

    g2d.transform(getTransformForLogicalToPhysical());

    drawBoard(g2d);

    g2d.setColor(Color.BLACK);
    // draws vertical lines
    for (int col = 1; col < width + 3; col++) {
      drawLine(g2d, 0, col, height, col);
    }

    // draws horizontal lines
    for (int row = 0; row < height + 1; row++) {
      drawLine(g2d, row, 1, row, width + 1);
    }

    drawScore(g2d);
  }

  /**
   * Returns a new logical dimension of 200 by 200 used to draw the game board.
   * @return logical dimension
   */
  private Dimension getLogicalDimensions() {
    return new Dimension(200, 200);
  }

  /**
   * Converts model coordinates to logical based on the logical dimension.
   * @return AffineTransforms with converted model to logical coordinates
   */
  private AffineTransform getTransformForModelToLogical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(getLogicalDimensions().getWidth() / (model.getWidth() + 2),
            getLogicalDimensions().getHeight() / model.getHeight());
    return transform;
  }

  /**
   * Converst logical coordinates to physical based on the logical dimension.
   * @return AffineTransforms with converted logical to physical coordinates
   */
  private AffineTransform getTransformForLogicalToPhysical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(this.getWidth() / getLogicalDimensions().getWidth(),
            this.getHeight() / getLogicalDimensions().getWidth());
    return transform;
  }

  /**
   * Draws a line given the Graphics2D object, row start and end, col start and end. Converts
   * the given model row and col coordinates into logical coordinates.
   * @param g2d Graphics2D object
   * @param row start of line row (0-index)
   * @param col start of line col (0-index)
   * @param endRow end of line row (0-index)
   * @param endCol end of line col (0-index)
   */
  private void drawLine(Graphics2D g2d, int row, int col, int endRow, int endCol) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null);
    Point2D dst = modelToLogical.transform(new Point(endCol, endRow), null);

    g2d.drawLine((int)src.getX(),
            (int)src.getY(),
            (int)dst.getX(),
            (int)dst.getY());
  }

  /**
   * Draws the game board based on the type of cells in the model. If the cell is an EmptyCell,
   * draws a cell with a gray background. If the cell is a Pawns, draws a cell with a gray
   * background and circles with corresponding count of pawns and color of player. If the cell is
   * a GameCard, draws a cell with the corresponding color of player and value of card.
   * @param g2d Graphics2D object
   */
  private void drawBoard(Graphics2D g2d) {
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        ReadOnlyCell cell = model.getCellAt(row, col);
        Color backgroundColor = cell.getCellColor();

        if (selectedBoardCell != null && row == selectedBoardCell.getY() && (col + 1) == selectedBoardCell.getX()) {
          backgroundColor = Color.cyan;
        }

        // empty or pawns cell = fill gray, game card = fill color
        drawRect(g2d, row, col + 1,1, 1, backgroundColor);

        // pawns cell
        Color ownedColor = cell.getOwnedColor();
        if (!cell.isGameCard() && (ownedColor.equals(Color.red) || ownedColor.equals(Color.blue))) {
          drawPawns(g2d, row, col + 1, cell.getValue(), ownedColor);
        }

        // game card
        if (cell.isGameCard()) {
          int value = cell.getValue();
          drawValue(g2d, row, col + 1, value);
        }
      }
    }
  }

  /**
   * Draws a cell on the game board based on the given Graphics2D object, row, col, width, height,
   * and color. Converts the given model coordinates to logical and then draws a rectangle based
   * on that converted coordinates and given color.
   * @param g2d Graphics2D object
   * @param row model row index of the cell to be drawn (0-index)
   * @param col model row index of the cell to be drawn (0-index)
   * @param width width of the cell to be drawn
   * @param height height of the cell to be drawn
   * @param color color of the cell to be drawn
   */
  private void drawRect(Graphics2D g2d, int row, int col, int width, int height, Color color) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null);
    Point2D dst = modelToLogical.transform(new Point(width, height), null);

    g2d.setColor(color);
    g2d.fillRect((int)src.getX(),
            (int)src.getY(),
            (int)dst.getX(),
            (int)dst.getY());
  }

  /**
   * Draws the pawns of the
   * @param g2d
   * @param row
   * @param col
   * @param value
   * @param color
   */
  private void drawPawns(Graphics2D g2d, int row, int col, int value, Color color) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null); // convert model to logical

    g2d.setColor(color);
    int xOffSet = 5;
    int yOffSet = 25 - (int)((model.getHeight() - 1) * 2.5);
    for (int i = 0; i < value; i++) {
      g2d.fillOval((int)src.getX() + xOffSet,(int)src.getY() + yOffSet,5,10);
      xOffSet += 6;
    }
  }

  /**
   * Draws the row score for each player next to each row on the board. Player 1's scores are drawn
   * to the left of the board, while Player 2's scores are drawn to the right of the board.
   * @param g2d Graphics2D object
   */
  private void drawScore(Graphics2D g2d) {
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth() + 2; col++) {
        if (col == 0) {
          drawValue(g2d, row, col, model.getP1RowScore(row));
        }
        if (col == model.getWidth() + 1) {
          drawValue(g2d, row, col, model.getP2RowScore(row));
        }
      }
    }
  }

  /**
   *
   * @param g2d
   * @param row
   * @param col
   * @param value
   */
  private void drawValue(Graphics2D g2d, int row, int col, int value) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null); // starting point
    Point2D dst = modelToLogical.transform(new Point(col + 1, row + 1), null); // starting point of next col
    int x = ((int)src.getX() + (int)dst.getX()) / 2; // in between starting point and next col
    int y = ((int)src.getY() + (int)dst.getY()) / 2;

    g2d.setColor(Color.black);
    g2d.drawString(Integer.toString(value), x - 4, y);
  }

  /**
   *
   * @return
   */
  public Point getSelectedBoardCell() {
    return selectedBoardCell;
  }

  /**
   * Resets the selected board cell to be null after a card is placed.
   */
  public void reset() {
    selectedBoardCell = null;
  }

  /**
   *
   * @param observer
   */
  public void subscribe(ViewActions observer) {
    this.addMouseListener(new PawnsBoardMouseListener(observer));
  }

  /**
   *
   */
  class PawnsBoardMouseListener extends MouseAdapter {
    private ViewActions observer;
    public PawnsBoardMouseListener(ViewActions observer) {
      this.observer = observer;
    }

    /**
     *
     * @param evt the event to be processed
     */
    public void mouseClicked(MouseEvent evt) {
      Point2D physical = evt.getPoint(); // coordinate of actual (x,y) of physical display in the panel

      if (model.isGameOver()) {
        return;
      }

      try {
        // create objects to convert
        AffineTransform physicalToLogical = getTransformForLogicalToPhysical(); // convert from physical to logical
        physicalToLogical.invert(); // invert physical to logical

        AffineTransform logicalToModel = getTransformForModelToLogical(); // convert from logical to model (cell row/col)
        logicalToModel.invert(); // invert logical to model

        // convert using objects to coordinates
        Point2D logical = physicalToLogical.transform(physical, null);
        Point2D model = logicalToModel.transform(logical, null);

        // observer takes coordinates and performs action
        if (selectedBoardCell != null && selectedBoardCell.getX() == (int)model.getX()
                && selectedBoardCell.getY() == (int)model.getY()) {
          selectedBoardCell = null;
        } else {
          if ((int)model.getX() > 0 && (int)model.getX() <= PawnsBoardPanel.this.model.getWidth() &&
                  (int)model.getY() >= 0 && (int)model.getY() < PawnsBoardPanel.this.model.getHeight()) {
            selectedBoardCell = new Point((int)model.getX(), (int)model.getY());
            observer.setSelectedCell((int)selectedBoardCell.getY(), (int)selectedBoardCell.getX() - 1);
          }
        }
        repaint();

      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }
  }
}
