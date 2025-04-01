package cs3500.pawnsboard;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import cs3500.pawnsboard.controller.DeckConfiguration;
import cs3500.pawnsboard.controller.PawnsBoardDeckConfig;
import cs3500.pawnsboard.controller.PawnsBoardPlayerController;
import cs3500.pawnsboard.model.EmptyCell;
import cs3500.pawnsboard.model.GameCard;
import cs3500.pawnsboard.model.GamePlayer;
import cs3500.pawnsboard.model.HumanPlayer;
import cs3500.pawnsboard.model.Pawns;
import cs3500.pawnsboard.model.PawnsBoardModel;
import cs3500.pawnsboard.model.Player;
import cs3500.pawnsboard.model.Position;
import cs3500.pawnsboard.model.QueensBlood;
import cs3500.pawnsboard.view.PawnsBoardFrame;
import cs3500.pawnsboard.view.PawnsBoardTextualView;
import cs3500.pawnsboard.view.PawnsBoardView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ControllerTest {
  private Position leftSecurity;
  private Position rightSecurity;
  private Position topSecurity;
  private Position bottomSecurity;
  private ArrayList<Position> securityInfluenceGrid;
  private EmptyCell emptyCell;
  private Pawns redPawns;
  private GameCard security;
  private GameCard bee;
  private GameCard sweeper;
  private GameCard crab;
  private GameCard queen;
  private GameCard mandragora;
  private GameCard trooper;
  private GameCard cavestalker;
  private GameCard lobber;
  private QueensBlood game1;
  private ArrayList<GameCard> p1Deck;
  private ArrayList<GameCard> p2Deck;
  private Player player1;
  private Player player2;
  private DeckConfiguration deckConfig;

  @Before
  public void setup() {
    leftSecurity = new Position(0, -1); // (2,1)
    rightSecurity = new Position(0, 1); // (2,3)
    topSecurity = new Position(-1, 0); // (1,2)
    bottomSecurity = new Position(1, 0); // (3,2)
    securityInfluenceGrid = new ArrayList<Position>(Arrays.asList(topSecurity, leftSecurity,
            rightSecurity, bottomSecurity));

    Position topMandragora = new Position(-1, 0); // (1,2)
    Position right1Mandragora = new Position(0, 1); // (2,3)
    Position right2Mandragora = new Position(0, 2); // (2,4)
    ArrayList<Position> mandragoraInfluenceGrid = new ArrayList<Position>(Arrays.asList(
            topMandragora, right1Mandragora, right2Mandragora));

    Position topBee = new Position(-2, 0);
    Position bottomBee = new Position(2, 0);
    ArrayList<Position> beeInfluenceGrid = new ArrayList<Position>(Arrays.asList(
            topBee, bottomBee));

    Position top1Sweeper = new Position(-1, -1);
    Position top2Sweeper = new Position(-1, 0);
    Position bottom1Sweeper = new Position(1, -1);
    Position bottom2Sweeper = new Position(1, 0);
    ArrayList<Position> sweeperInfluenceGrid = new ArrayList<Position>(Arrays.asList(
            top1Sweeper, top2Sweeper, bottom1Sweeper, bottom2Sweeper));

    Position leftCrab = new Position(0, -1); // (2,1)
    Position rightCrab = new Position(0, 1); // (2,3)
    Position topCrab = new Position(-1, 0); // (1,2)
    ArrayList<Position> crabInfluenceGrid = new ArrayList<Position>(
            Arrays.asList(topCrab, leftCrab, rightCrab));

    Position topQueen = new Position(-2, 0); // (0,2)
    ArrayList<Position> queenInfluenceGrid = new ArrayList<Position>(Arrays.asList(topQueen));

    Position top1Trooper = new Position(-2, 0);
    Position top2Trooper = new Position(-1, 1);
    Position rightTrooper = new Position(0, 1);
    Position bottom1Trooper = new Position(1, 0);
    Position bottom2Trooper = new Position(2, 0);
    ArrayList<Position> trooperInfluenceGrid = new ArrayList<Position>(Arrays.asList(
            top1Trooper, top2Trooper, rightTrooper, bottom1Trooper, bottom2Trooper));

    Position topCaveStalker = new Position(-2, 0);
    Position right1CaveStalker = new Position(-1, 1);
    Position right2CaveStalker = new Position(0, 1);
    Position right3CaveStalker = new Position(1, 1);
    Position bottomCaveStalker = new Position(2, 0);
    ArrayList<Position> cavestalkerInfluenceGrid = new ArrayList<Position>(Arrays.asList(
            topCaveStalker, right1CaveStalker, right2CaveStalker,
            right3CaveStalker, bottomCaveStalker));

    Position rightLobber = new Position(0, 2);
    ArrayList<Position> lobberInfluenceGrid = new ArrayList<Position>(Arrays.asList(rightLobber));

    emptyCell = new EmptyCell();
    redPawns = new Pawns(Color.red);
    Pawns bluePawns = new Pawns(Color.blue);

    security = new GameCard("Security", GameCard.Cost.ONE, 1,
            securityInfluenceGrid);
    bee = new GameCard("Bee", GameCard.Cost.ONE, 1, beeInfluenceGrid);
    sweeper = new GameCard("Sweeper", GameCard.Cost.TWO, 2, sweeperInfluenceGrid);
    crab = new GameCard("Crab", GameCard.Cost.ONE, 1, crabInfluenceGrid);
    queen = new GameCard("Queen", GameCard.Cost.ONE, 1, queenInfluenceGrid);
    mandragora = new GameCard("Mandragora", GameCard.Cost.ONE, 1,
            mandragoraInfluenceGrid);
    trooper = new GameCard("Trooper", GameCard.Cost.TWO, 3, trooperInfluenceGrid);
    cavestalker = new GameCard("Cavestalker", GameCard.Cost.THREE, 4,
            cavestalkerInfluenceGrid);
    lobber = new GameCard("Lobber", GameCard.Cost.TWO, 1, lobberInfluenceGrid);

    p1Deck = new ArrayList<GameCard>(Arrays.asList(security, bee, sweeper, crab, mandragora, queen,
            trooper, cavestalker, lobber, security, bee, sweeper, crab, mandragora, queen, trooper,
            cavestalker, lobber));

    p2Deck = new ArrayList<GameCard>(Arrays.asList(security, bee, sweeper, crab, mandragora, queen,
            trooper, cavestalker, lobber, security, bee, sweeper, crab, mandragora, queen,
            trooper, cavestalker, lobber));

    deckConfig = new PawnsBoardDeckConfig();
  }


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

  @Test
  public void testControllerSelectValidCard() {
    StringBuilder log1 = new StringBuilder();
    QueensBlood model = new MockModelPlace(log1, 5, 3, new Random(6));
    model.startGame(p1Deck, p2Deck, 5, false);
    GamePlayer player1 = new HumanPlayer(model, 1);
    PawnsBoardView view = new PawnsBoardFrame(model, 1);

    PawnsBoardPlayerController controller = new PawnsBoardPlayerController(model, player1, view);
    controller.playGame();
    controller.setCardIdx(0);
    controller.setSelectedCell(1,0);
    controller.placeCard();
    assertEquals("0 1 0", log1.toString());
  }

  // unit test: controller taking too little inputs
  @Test(expected = IllegalStateException.class)
  public void testControllerPlaceNoCardIdx() { ///////////////// mock controller throw exception
    StringBuilder log1 = new StringBuilder();
    QueensBlood model = new MockModelPlace(log1, 5, 3, new Random(6));
    model.startGame(p1Deck, p2Deck, 5, false);
    GamePlayer player1 = new HumanPlayer(model, 1);
    PawnsBoardView view = new PawnsBoardFrame(model, 1);

    PawnsBoardPlayerController controller = new PawnsBoardPlayerController(model, player1, view);
    controller.playGame();
    controller.setSelectedCell(1,0);
    controller.placeCard();
  }

  @Test
  public void testControllerNoCellSelected() { ///////////////// mock controller throw exception

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

  // unit test: when model indicates move is invalid for placeCard
  @Test
  public void testControllerModelInvalidCardIdxPlace() { ///////////////// mock controller throw exception

  }

  @Test
  public void testControllerModelInvalidRowPlaceInput() { ///////////////// mock controller throw exception

  }

  @Test
  public void testControllerModelInvalidColumnPlaceInput() { ///////////////// mock controller throw exception

  }

  @Test
  public void testControllerModelPlaceCannotAddCardNoPawnsInput() { ///////////////// mock controller throw exception

  }

  @Test
  public void testControllerModelPlaceCannotAddCardNoPlayerPawnsOwnershipInput() { ///////////////// mock controller throw exception

  }

  @Test
  public void testControllerModelPlaceCannotAddCardCannotCoverCostInput() { ///////////////// mock controller throw exception

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
