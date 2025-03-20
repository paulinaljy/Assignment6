package cs3500.pawnsboard.view;

public interface ViewActions {

  void placeCard(int cardIdx, int row, int col);

  void quit();

  void pass();

  void setCardIdx(int cardIdx);

  void setSelectedCell(int row, int col);

}

