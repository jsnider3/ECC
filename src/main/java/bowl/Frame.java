package com.joshuasnider.ecc.bowl;

import java.util.List;

/**
 * A frame in a bowling game.
 */
public class Frame {

  private List<Integer> rolls;

  public Frame(List<Integer> rolls) {
    this.rolls = rolls;
  }

  public String toString() {
    String str = "Frame:";
    for (int roll : rolls) {
      str += Integer.toString(roll) + " ";
    }
    return str;
  }

  public List<Integer> getRolls() {
    return rolls;
  }

  public int getScore() {
    int score = 0;
    for (int roll : rolls) {
      score += roll;
    }
    return score;
  }

  public boolean isSplit() {
    return rolls.size() == 2 && rolls.get(0) + rolls.get(1) == 10;
  }

  public boolean isStrike() {
    return rolls.size() == 1 && rolls.get(0) == 10;
  }

}
