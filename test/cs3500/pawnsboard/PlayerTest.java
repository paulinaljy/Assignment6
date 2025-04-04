package cs3500.pawnsboard;

import org.junit.Test;

import java.util.Random;

import cs3500.pawnsboard.player.HumanPlayer;
import cs3500.pawnsboard.player.MachinePlayer;
import cs3500.pawnsboard.strategy.Strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Players (Machine and Human).
 */
public class PlayerTest {
  @Test
  public void testHumanPlayerMethods() {
    StringBuilder log = new StringBuilder();
    MockPawnsBoardModel mockModel = new MockPawnsBoardModel(log, 5,
            3, new Random(6));
    HumanPlayer player = new HumanPlayer(mockModel, 1);

    assertTrue(player.isHumanPlayer());
    assertEquals(1, player.getPlayerID());
    assertEquals("Player 1", player.toString());
  }

  @Test
  public void testMachinePlayerPasses() {
    StringBuilder log = new StringBuilder();
    MockPawnsBoardModel mockModel = new MockPawnsBoardModel(log, 5,
            3, new Random(6));
    MockViewActions mockObserver = new MockViewActions();
    Strategy strategy = new MockPassStrategy();
    MachinePlayer player = new MachinePlayer(mockModel, strategy, 1);

    player.subscribe(mockObserver);

    player.chooseMove();

    assertTrue(mockObserver.passCalled);
  }
}
