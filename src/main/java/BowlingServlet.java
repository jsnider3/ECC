package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Calculate the score for a game of bowling.
 */
public class BowlingServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        int score = getScore(arr.getJsonArray(count));
        resp.getWriter().print(score);
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      doGet(req, resp);
    }

    public int getScore(JsonArray game) {
      int frame = 1;
      int framescore = 0;
      int score = 0;
      int roll = 1;
      int special = 0;
      for (int ind = 0; ind < game.size(); ind++) {
        framescore += game.getInt(ind);
        if (special == 1) {
          score += 10 + framescore;
          special = 0;
        }
        if (special == 2 && roll == 2) {
          score += 10 + framescore;
          special = 0;
        }
        if (framescore == 10 || roll == 2) {
          roll = 1;
          frame++;
          if (special == 0) {
            score += framescore;
          }
          special = 0;
          framescore = 0;
        }
        else {
          roll++;
        }
      }
      return score;
    }
}

