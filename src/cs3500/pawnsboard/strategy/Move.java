package cs3500.pawnsboard.strategy;

import java.util.Objects;

import cs3500.pawnsboard.model.Player;

public class Move {
  private int cardIdx;
  private int col;
  private int row;
  private boolean pass;
  public Move(int cardIdx, int row, int col, boolean pass) {
    this.cardIdx = cardIdx;
    this.col = col;
    this.row = row;

    if (pass) {
      this.cardIdx = -1;
      this.col = -1;
      this.row = -1;
      this.pass = pass;
    }
  }

  public boolean isPass() {
    return this.pass;
  }

  public int getCardIdx() {
    return this.cardIdx;
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Move)) {
      return false;
    }
    Move that = (Move) other;
    return this.cardIdx == that.cardIdx && this.row == that.row
            && this.col == that.col && this.pass == that.pass;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row);
  }

  @Override
  public String toString() {
    return "Card " + cardIdx + " in (" + row + "," + col + ")";
  }
}
