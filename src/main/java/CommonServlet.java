package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.Json;
import javax.servlet.http.*;
import javax.servlet.*;

/**
 * Shared servlet code.
 */
public abstract class CommonServlet extends HttpServlet {

  public List<Integer> convertJsonIntArray(JsonArray arr) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < arr.size(); i++) {
      list.add(arr.getInt(i));
    }
    return list;
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws IOException {
    try {
      doGet(req, resp);
    } catch (ServletException e) {
      e.printStackTrace();
    }
  }

  public String getInput(HttpServletRequest req) {
    try {
      String line = null;
      StringBuffer data = new StringBuffer();
      BufferedReader reader = req.getReader();
      while ((line = reader.readLine()) != null) {
        data.append(line);
      }
      return data.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public JsonArray getUnnamedArgs(HttpServletRequest req) {
    try {
      return Json.createReader(new StringReader(getInput(req))).readArray();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

    /**
     * Check if something is a prime number.
     */
    public boolean isPrime(int num) {
      for (int x = 2; x < (int)Math.pow(num, .5) + 1; x++) {
        if (num % x == 0) {
          return false;
        }
      }
      return true;
    }

}

