package cs3500.pawnsboard.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GameOverFrame extends JFrame implements PawnsBoardView {
  public GameOverFrame() {
    super("Game Over");
    setSize(100, 100);
    setLocation(1000, 200);
    JButton okButton = new JButton("OK");
    okButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.exit(0);
      }
    });
    this.getContentPane().add(okButton);
    setVisible(true);
    setFocusable(true);
  }
  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void subscribe(ViewActions observer) {
    observer.quit();
  }
}
