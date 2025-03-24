package cs3500.pawnsboard.strategy;

public class Move {
  private int cardIdx;
  private int col;
  private int row;
  private boolean pass;
  public Move(int cardIdx, int col, int row, boolean pass) {
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
}
