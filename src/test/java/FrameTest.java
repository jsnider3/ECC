package com.joshuasnider.ecc.bowl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FrameTest {

  /**
   * Test score.
   */
  @Test
  public void testScore() {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(0);
    rolls.add(0);
    assertEquals(0, new Frame(rolls).getScore());
    rolls = new ArrayList<>();
    rolls.add(5);
    rolls.add(5);
    assertEquals(10, new Frame(rolls).getScore());
    rolls = new ArrayList<>();
    rolls.add(10);
    assertEquals(10, new Frame(rolls).getScore());
  }

  /**
   * Test is strike.
   */
  @Test
  public void testIsStrike() {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(0);
    rolls.add(0);
    assertFalse(new Frame(rolls).isStrike());
    rolls = new ArrayList<>();
    rolls.add(5);
    rolls.add(5);
    assertFalse(new Frame(rolls).isStrike());
    rolls = new ArrayList<>();
    rolls.add(10);
    assertTrue(new Frame(rolls).isStrike());
  }

  /**
   * Test score for a bunch of strikes.
   */
  @Test
  public void testIsSplit() {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(0);
    rolls.add(0);
    assertFalse(new Frame(rolls).isSplit());
    rolls = new ArrayList<>();
    rolls.add(5);
    rolls.add(5);
    assertTrue(new Frame(rolls).isSplit());
    rolls = new ArrayList<>();
    rolls.add(10);
    assertFalse(new Frame(rolls).isSplit());
  }
}
