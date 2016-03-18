package com.joshuasnider.ecc.bowl;

import com.joshuasnider.ecc.CommonServlet;
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
        List<Integer> rolls = convertJsonIntArray(arr.getJsonArray(count));
        int score = Game.fromRolls(rolls).getScore();
        resp.getWriter().print(score);
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

}

