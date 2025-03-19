package cs3500.pawnsboard.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PawnsBoardFrame extends JFrame implements PawnsBoardView {
  private PawnsBoardPanel boardPanel;
  private PlayersHandPanel playersHandPanel;

  public PawnsBoardFrame(ReadonlyPawnsBoardModel pawnsBoardModel) {
    super();
    setSize((pawnsBoardModel.getWidth() + 2) * 100, (pawnsBoardModel.getHeight() + 2) * 100); // physical display dimensions
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    boardPanel = new PawnsBoardPanel(pawnsBoardModel);
    playersHandPanel = new PlayersHandPanel(pawnsBoardModel);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(boardPanel);
    panel.add(playersHandPanel);
    this.add(panel);

    setFocusable(true);
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
    this.playersHandPanel.subscribe(observer);
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_Q: // quit game
            observer.quit();
            break;

          case KeyEvent.VK_ENTER: // confirm move
            Point selectedCell = boardPanel.getSelectedBoardCell();
            int cardIdx = playersHandPanel.getSelectedCard().getIndexID();
            observer.placeCard(cardIdx, (int)selectedCell.getY(), (int)selectedCell.getX());
            break;

          case KeyEvent.VK_SPACE: // pass move
            observer.pass();
            break;

          default:
            break;
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

      @Override
      public void keyTyped(KeyEvent e) {
      }
    });
  }
}

