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

    //this.addMouseListener(new PawnsBoardMouseListener());
  }

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

  private void drawLine(Graphics2D g2d, int row, int col, int endRow, int endCol) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null);
    Point2D dst = modelToLogical.transform(new Point(endCol, endRow), null);

    g2d.drawLine((int)src.getX(),
            (int)src.getY(),
            (int)dst.getX(),
            (int)dst.getY());
  }

  private Dimension getLogicalDimensions() {
    return new Dimension(200, 200);
  }

  private AffineTransform getTransformForModelToLogical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(getLogicalDimensions().getWidth() / (model.getWidth() + 2),
            getLogicalDimensions().getHeight() / model.getHeight());
    return transform;
    // scale ratio = logical dimension / model dimension
  }

  private AffineTransform getTransformForLogicalToPhysical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(this.getWidth() / getLogicalDimensions().getWidth(),
            this.getHeight() / getLogicalDimensions().getWidth());
    return transform;
    // scale ratio = physical dimension / logical dimension
  }

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

  private void drawRect(Graphics2D g2d, int row, int col, int width, int height, Color color) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null); // convert model to logical
    Point2D dst = modelToLogical.transform(new Point(width, height), null);

    g2d.setColor(color);
    g2d.fillRect((int)src.getX(),
            (int)src.getY(),
            (int)dst.getX(),
            (int)dst.getY());
  }

  private void drawCircle(Graphics2D g2d, int row, int col, int width, int height, Color color) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null); // convert model to logical
    Point2D dst = modelToLogical.transform(new Point(width, height), null);

    g2d.setColor(color);
    g2d.fillOval((int)src.getX() + 5,
            (int)src.getY() + 20,
            5,
            10);
  }

  private void drawValue(Graphics2D g2d, int row, int col, int value) {
    AffineTransform modelToLogical = getTransformForModelToLogical();
    Point2D src = modelToLogical.transform(new Point(col, row), null); // starting point
    Point2D dst = modelToLogical.transform(new Point(col + 1, row + 1), null); // starting point of next col
    int x = ((int)src.getX() + (int)dst.getX()) / 2; // in between starting point and next col
    int y = ((int)src.getY() + (int)dst.getY()) / 2;

    g2d.setColor(Color.black);
    g2d.drawString(Integer.toString(value), x - 4, y);
  }

  public Point getSelectedBoardCell() {
    return selectedBoardCell;
  }

  public void reset() {
    selectedBoardCell = null;
  }

  public void subscribe(ViewActions observer) {
    this.addMouseListener(new PawnsBoardMouseListener(observer));
  }

  class PawnsBoardMouseListener extends MouseAdapter {
    private ViewActions observer;
    public PawnsBoardMouseListener(ViewActions observer) {
      this.observer = observer;
    }

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
          selectedBoardCell = new Point((int)model.getX(), (int)model.getY());
        }
        observer.setSelectedCell((int)selectedBoardCell.getX(), (int)selectedBoardCell.getY());
        repaint();

      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }
  }
}
