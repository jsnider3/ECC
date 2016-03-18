package com.joshuasnider.ecc.bowl;

import java.util.ArrayList;
import java.util.List;

/**
 * A game of bowling.
 */
public class Game {

  private List<Frame> frames;

  public Game(List<Frame> frames) {
    this.frames = frames;
  }

  /**
   * Create a game from a list of rolls.
   */
  public static Game fromRolls(List<Integer> rolls) {
    List<Frame> frames = new ArrayList<>();
    List<Integer> frameRolls = new ArrayList<>();
    int frame = 1;
    for (Integer roll : rolls) {
      frameRolls.add(roll);
      int frameSum = 0;
      for (Integer frameRoll : frameRolls) {
        frameSum += frameRoll;
      }
      if (frame < 10 && (frameSum >= 10 || frameRolls.size() == 2)) {
        frames.add(new Frame(frameRolls));
        frameRolls = new ArrayList<>();
        frame++;
      }
    }
    frames.add(new Frame(frameRolls));
    return new Game(frames);
  }

  public int getBonus(int rolls, int start) {
    int bonus = 0;
    if (start >= 0 && start <= 9) {
      List<Integer> frame = frames.get(start).getRolls();
      for (int frameRoll : frame) {
        bonus += frameRoll;
        rolls--;
        if (rolls == 0) {
          break;
        }
      }
      if (rolls > 0) {
        bonus += getBonus(rolls, start + 1);
      }
    }
    return bonus;
  }

  public List<Frame> getFrames() {
    return frames;
  }

  /**
   * The number of points scored in this game.
   */
  public int getScore() {
    int score = 0;
    for (int ind = 0; ind < frames.size(); ind++) {
      Frame frame = frames.get(ind);
      score += frame.getScore();
      if (frame.isSplit()) {
        score += getBonus(1, ind + 1);
      } else if (frame.isStrike()) {
        score += getBonus(2, ind + 1);
      }
    }
    return score;
  }

}
