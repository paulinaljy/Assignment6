package cs3500.pawnsboard.model;

import java.util.Objects;

/**
 * Represents a Position in an influence grid of a GameCard. Each Position has a row delta and col
 * delta, which represents the distance from the center card position (2,2).
 */
public class Position {
  private final int rowDelta;
  private final int colDelta;

  /**
   * Initializes a Position with a rowDelta and colDelta.
   * @param rowDelta the horizontal distance from position (2,2)
   * @param colDelta the vertical distance from position (2,2)
   */
  public Position(int rowDelta, int colDelta) {
    this.rowDelta = rowDelta;
    this.colDelta = colDelta;
  }

  public int getRowDelta() {
    return this.rowDelta;
  }

  public int getColDelta() {
    return this.colDelta;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Position)) {
      return false;
    }
    Position that = (Position) other;
    return this.rowDelta == that.rowDelta && this.colDelta == that.colDelta;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowDelta);
  }
}
