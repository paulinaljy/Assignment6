package cs3500.pawnsboard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ControllerTest {


  @Test
  public void testInvalidNullConstructor() {

  }

  @Test
  public void testValidControllerConstructor() {

  }

  @Test (expected = IllegalStateException.class)
  public void testGameNotStarted() {

  }

  // integration test: testing single valid move for place
  @Test
  public void testSingleValidMovePlaceHumanPlayer1() {

  }

  @Test
  public void testSingleValidMovePlaceHumanPlayer2() {

  }

  @Test
  public void testSingleValidMovePlaceMachinePlayer() {

  }

  // integration test: testing pass
  @Test
  public void testSingleValidMovePassHumanPlayer1() {

  }

  @Test
  public void testSingleValidMovePassHumanPlayer2() {

  }

  @Test
  public void testSingleValidMovePassMachinePlayer() {

  }

  // integration test: game played to completion with all valid inputs
  @Test
  public void testValidGamePlayHumanPlayers() {

  }

  @Test
  public void testValidGamePlayHumanAndMachinePlayer() {

  }

  @Test
  public void testValidGamePlayMachinePlayers() {

  }

  // integration test: game played to completion with invalid inputs
  @Test
  public void testInvalidGamePlayHumanPlayers() {

  }

  @Test
  public void testInvalidGamePlayHumanAndMachinePlayer() {

  }

  @Test
  public void testInvalidGamePlayMachinePlayers() {

  }

  // integration test: game quit at any point
  @Test
  public void testQuitGameHumanPlayer() {

  }

  @Test
  public void testQuitGameMachinePlayer() {

  }

  @Test
  public void testQuitGameAfterStartGame() {

  }

  // unit test: game quits anytime
  @Test
  public void testControllerQuitHumanPlayer() {

  }

  @Test
  public void testControllerQuitMachinePlayer() {

  }

  @Test
  public void testControllerQuitAfterStartGame() {

  }


  // integration test: controller taking in too little inputs
  @Test
  public void testIntegrationPlaceNoCardIdx() {

  }

  @Test
  public void testIntegrationNoCellSelected() {

  }

  // unit test: controller taking too little inputs
  @Test(expected = IllegalStateException.class)
  public void testControllerPlaceNoCardIdx() {

  }

  @Test
  public void testControllerNoCellSelected() {

  }

  // integration test: when model indicates move is invalid for placeCard
  @Test
  public void testModelInvalidCardIdxPlace() {

  }

  @Test
  public void testModelInvalidRowPlaceInput() {

  }

  @Test
  public void testModelInvalidColumnPlaceInput() {

  }

  @Test
  public void testModelPlaceCannotAddCardNoPawnsInput() {

  }

  @Test
  public void testModelPlaceCannotAddCardNoPlayerPawnsOwnershipInput() {

  }

  @Test
  public void testModelPlaceCannotAddCardCannotCoverCostInput() {

  }

  // integration test: when model indicates move is invalid for placeCard
  @Test
  public void testControllerModelInvalidCardIdxPlace() {

  }

  @Test
  public void testControllerModelInvalidRowPlaceInput() {

  }

  @Test
  public void testControllerModelInvalidColumnPlaceInput() {

  }

  @Test
  public void testControllerModelPlaceCannotAddCardNoPawnsInput() {

  }

  @Test
  public void testControllerModelPlaceCannotAddCardNoPlayerPawnsOwnershipInput() {

  }

  @Test
  public void testControllerModelPlaceCannotAddCardCannotCoverCostInput() {

  }

  // unit test: controller set card index correctly
  @Test
  public void testControllerSetCardIdxCorrectly() {

  }

  // unit test: controller set cell selected correctly
  @Test
  public void testControllerSetCellSelectedCorrectly() {

  }

  // test viewEnabled

  // test itsYourTurn

  // test viewRefreshed

  // test processGameOver

}
