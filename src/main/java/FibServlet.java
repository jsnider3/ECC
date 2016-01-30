
package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Get the nth fibonacci.
 */
public class FibServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        resp.getWriter().print(getFib(arr.getInt(count)));
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    public int getFib(int index) {
      int a = 1;
      int b = 2;
      int count = 2;
      while (count < index) {
        int temp = b;
        b = a + b;
        a = temp;
        count++;
      }
      return a;
    }
}
