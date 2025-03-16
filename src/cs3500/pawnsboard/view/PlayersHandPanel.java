package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PlayersHandPanel extends JPanel {

  private final ReadonlyPawnsBoardModel model;

  public PlayersHandPanel(ReadonlyPawnsBoardModel model) {
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

    int width = model.getWidth();
    int handSize = model.getHand().size();

    g2d.transform(getTransformForLogicalToPhysical());

    g2d.setColor(Color.BLACK);
    // draws vertical lines
    for (int col = 1; col < handSize; col++) {
      drawLine(g2d, 0, col, width, col);
    }
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
}
