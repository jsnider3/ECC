package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Get the square sum.
 */
public class SquareSumServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        resp.getWriter().print(getSquareSum(arr.getInt(count)));
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    public int getSquareSum(int num) {
      int sum = 0;
      for (char c : Integer.toString(num).toString().toCharArray()) {
        int value = Character.getNumericValue(c);
        sum += value * value;
      }
      return sum;
    }
}
