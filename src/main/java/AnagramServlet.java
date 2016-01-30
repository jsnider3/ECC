package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Shared servlet code.
 */
public class AnagramServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        JsonArray cur = arr.getJsonArray(count);
        if (isAnagram(cur.getString(0), cur.getString(1))) {
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

    public boolean isAnagram(String first, String second) {
      for (char c : (first + second).toCharArray()) {
        if (count(first, c) != count(second, c))
          return false;
      }
      return true;
    }

    public int count(String str, char c) {
      int count = 0;
      for (char iter : str.toCharArray()) {
        if (c == iter) {
          count++;
        }
      }
      return count;
    }

}
