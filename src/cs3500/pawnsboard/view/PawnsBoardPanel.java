package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

import cs3500.pawnsboard.model.Cell;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PawnsBoardPanel extends JPanel {

  private final ReadonlyPawnsBoardModel model;

  public PawnsBoardPanel(ReadonlyPawnsBoardModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g.create();

    g2d.setColor(Color.BLACK);
    int height = model.getHeight();
    int width = model.getWidth();

    g2d.transform(getTransformForLogicalToPhysical());

    // draws vertical lines
    for (int col = 0; col < width; col++) {
      drawLine(g2d, 0, col, width + 1, col);
    }
    drawLine(g2d, 1, 0, 1, 3);
    drawLine(g2d, 2, 0, 2, 3);
    drawLine(g2d, 0, 1, 3, 1);
    drawLine(g2d, 0, 2, 3, 2);

    // draws horizontal lines
    for (int row = 1; row < height; row++) {
      drawLine(g2d, row, 0, row, height + 1);
    }

    drawBoard(g2d);
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
    return new Dimension(model.getWidth(), model.getHeight());
  }

  private AffineTransform getTransformForModelToLogical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(getLogicalDimensions().getWidth() / 10,
            getLogicalDimensions().getHeight() / 10);
    return transform;
  }

  private AffineTransform getTransformForLogicalToPhysical() {
    AffineTransform transform = new AffineTransform();
    transform.scale(this.getWidth() / getLogicalDimensions().getWidth(),
            this.getHeight() / getLogicalDimensions().getHeight());
    return transform;
  }

  private void drawBoard(Graphics2D g2d) {
    for(int row = 0; row < model.getHeight(); row++) {
      for(int col = 0; col < model.getWidth(); col++) {

        Cell cell = model.getCellAt(row, col);
      }
    }
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
      Point2D physical = evt.getPoint();

      try {
        AffineTransform physicalToLogical = getTransformForLogicalToPhysical();
        physicalToLogical.invert();

        AffineTransform logicalToModel = getTransformForModelToLogical();
        logicalToModel.invert();

        Point2D logical = physicalToLogical.transform(physical, null);
        Point2D model = logicalToModel.transform(logical, null);
        observer.placeCard((int) model.getY(), (int) model.getX());
      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }
  }
}
