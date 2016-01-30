package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Factorize a number.
 */
public class FactorServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        List<Integer> factors = getFactors(arr.getInt(count));
        resp.getWriter().print("[");
        for (int index = 0; index < factors.size(); index++) {
          resp.getWriter().print(factors.get(index));
          if (index != factors.size() - 1) {
            resp.getWriter().print(", ");
          }
        }
        resp.getWriter().print("]");
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

    public List<Integer> getFactors(int num) {
      List<Integer> factors = new ArrayList<Integer>();
      if (num < 2) {

      } else if (isPrime(num)) {
        factors.add(num);
      } else {
        for (int count = 2; count < num; count++) {
          if (num % count == 0) {
            factors.addAll(getFactors(count));
            factors.addAll(getFactors(num / count));
            break;
          }
        }
      }
      return factors;
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

