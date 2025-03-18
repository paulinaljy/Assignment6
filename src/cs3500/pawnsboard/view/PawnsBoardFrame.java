package cs3500.pawnsboard.view;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.pawnsboard.controller.PawnsBoardGUIController;
import cs3500.pawnsboard.model.ReadonlyPawnsBoardModel;

public class PawnsBoardFrame extends JFrame implements PawnsBoardView {
  private PawnsBoardPanel boardPanel;
  private PlayersHandPanel playersHandPanel;
  private PawnsBoardGUIController controller;

  public PawnsBoardFrame(ReadonlyPawnsBoardModel pawnsBoardModel) {
    super();
    setSize((pawnsBoardModel.getWidth() + 2) * 100, (pawnsBoardModel.getHeight() + 2) * 100); // physical display dimensions
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    boardPanel = new PawnsBoardPanel(pawnsBoardModel, controller);
    playersHandPanel = new PlayersHandPanel(pawnsBoardModel, controller);
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1));
    panel.add(boardPanel);
    panel.add(playersHandPanel);
    this.add(panel);

    setFocusable(true);
  }

  public void setController(PawnsBoardGUIController controller) {
    this.controller = controller;
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
    //this.boardPanel.subscribe(observer);
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {
        controller.keyPressed(e);
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });
  }
}

