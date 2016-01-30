package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * FizzBuzz for the cloud.
 */
public class FizzBuzzServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        resp.getWriter().print(getFizzBuzz(arr.getInt(count)));
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    public String getFizzBuzz(int index) {
      if (index % 2 == 0 && index % 3 == 0) {
        return "\"FizzBuzz\"";
      }
      else if (index % 3 == 0) {
        return "\"Buzz\"";
      }
      else if (index % 2 == 0) {
        return "\"Fizz\"";
      }
      else {
        return "\"" + Integer.toString(index) + "\"";
      }
    }
}
