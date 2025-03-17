package cs3500.pawnsboard.model;

import java.awt.*;

public interface ReadOnlyGameCard {
  int getCost();

  Color getOwnedColor();

  Color getCellColor();

  int getValue();
  String getName();
  boolean isCellInfluencedAt(int row, int col);
}