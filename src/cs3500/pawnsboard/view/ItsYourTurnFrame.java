package cs3500.pawnsboard.view;

import javax.swing.*;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class ItsYourTurnFrame extends JFrame implements PawnsBoardView {
  private ReadonlyPawnsBoardModel model;
  public ItsYourTurnFrame(ReadonlyPawnsBoardModel model) {
    this.model = model;
  }
  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void subscribe(ViewActions observer) {

  }
}
