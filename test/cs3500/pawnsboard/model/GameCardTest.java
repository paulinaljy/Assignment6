package cs3500.pawnsboard.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Represents examples and tests of the package private methods of GameCard.
 */
public class GameCardTest {
  private ArrayList<Position> securityInfluenceGrid;
  private GameCard security;

  @Before
  public void setUp() {
    Position leftSecurity = new Position(0, -1); // (2,1)
    Position rightSecurity = new Position(0, 1); // (2,3)
    Position topSecurity = new Position(-1, 0); // (1,2)
    Position bottomSecurity = new Position(1, 0); // (3,2)
    securityInfluenceGrid = new ArrayList<Position>(Arrays.asList(topSecurity, leftSecurity,
            rightSecurity, bottomSecurity));
    security = new GameCard("Security", GameCard.Cost.ONE, 1,
            securityInfluenceGrid);
  }

  /*@Test
  public void testGameCardSetColor() {
    assertEquals(Color.white, security.getColor());
    security.setColor(Color.red);
    assertEquals(Color.red, security.getColor());
  }*/

  @Test
  public void testGameCardGetPositions() {
    assertEquals(securityInfluenceGrid, security.getPositions());
  }

  @Test
  public void testValueToCost() {
    assertEquals(GameCard.Cost.ONE, security.valueToCost(1));
    assertEquals(GameCard.Cost.TWO, security.valueToCost(2));
    assertEquals(GameCard.Cost.THREE, security.valueToCost(3));
  }
}
