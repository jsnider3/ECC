package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * List sexy primes.
 */
public class SexyPrimeServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      List<Integer> primes = primesLessThan(arr.getInt(0));
      List<String> pairs = new ArrayList<String>();
      for (int prime : primes) {
        if (primes.contains(prime + 6)) {
          pairs.add("[" + prime + ", " + (prime + 6) + "]");
        }
      }
      for (int index = 0; index < pairs.size(); index++) {
        resp.getWriter().print(pairs.get(index));
        if (index != pairs.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    public List<Integer> primesLessThan(int max) {
      List<Integer> primes = new ArrayList<Integer>();
      for (int x = 2; x < max; x++) {
        if (isPrime(x)) {
          primes.add(x);
        }
      }
      return primes;
    }
}

