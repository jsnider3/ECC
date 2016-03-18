package com.joshuasnider.ecc.bowl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BowlingTest {

  /**
   * Test score for a bunch of gutterballs.
   */
  @Test
  public void testGutterballs() {
    List<Integer> rolls = new ArrayList<>();
    for (int x = 0; x < 20; x++) {
      rolls.add(0);
    }
    Game game = Game.fromRolls(rolls);
    assertEquals(0, game.getScore());
    assertEquals(10, game.getFrames().size());
  }

  /**
   * Test score for a bunch of ones.
   */
  @Test
  public void testAllOnes() {
    List<Integer> rolls = new ArrayList<>();
    for (int x = 0; x < 20; x++) {
      rolls.add(1);
    }
    Game game = Game.fromRolls(rolls);
    assertEquals(20, game.getScore());
    assertEquals(10, game.getFrames().size());
  }

  /**
   * Test score for a bunch of strikes.
   */
  @Test
  public void testAllStrikes() {
    List<Integer> rolls = new ArrayList<>();
    for (int x = 0; x < 12; x++) {
      rolls.add(10);
    }
    Game game = Game.fromRolls(rolls);
    assertEquals(20, game.getBonus(2, 1));
    assertEquals(20, game.getBonus(2, 9));
    assertEquals(300, game.getScore());
    assertEquals(10, game.getFrames().size());
  }
}
