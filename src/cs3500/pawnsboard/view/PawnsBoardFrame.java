package cs3500.pawnsboard.view;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PawnsBoardFrame extends JFrame implements PawnsBoardView {

  private PawnsBoardPanel boardPanel;
  private PlayersHandPanel playersHandPanel;

  public PawnsBoardFrame(ReadonlyPawnsBoardModel model) {
    super();
    setSize((model.getWidth() + 2) * 100, model.getHeight() * 100); // physical display dimensions
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    boardPanel = new PawnsBoardPanel(model);
    this.add(boardPanel);
    //this.add(playersHandPanel);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void subscribe(ViewActions observer) {
    this.boardPanel.subscribe(observer);
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'q') {
          observer.quit();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
  }
}

