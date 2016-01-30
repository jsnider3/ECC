package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Is something prime?
 */
public class PrimeServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        if (isPrime(arr.getInt(count))) {
          resp.getWriter().print("true");
        } else {
          resp.getWriter().print("false");
        }
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

    public boolean isPrime(int num) {
      for (int x = 2; x < num; x++) {
        if (num % x == 0) {
          return false;
        }
      }
      return true;
    }
}

