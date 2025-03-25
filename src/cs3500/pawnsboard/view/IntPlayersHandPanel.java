package cs3500.pawnsboard.view;

/**
 * Interface for PlayersHandPanel.
 */
public interface IntPlayersHandPanel extends CommonView {
  void refreshHand();
  GameCardButton getSelectedCard();
}
