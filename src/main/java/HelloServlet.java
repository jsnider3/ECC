package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.*;

/**
 * Prints hello world repeated.
 */
public class HelloServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      int reps = getUnnamedArgs(req).getInt(0);
      String hello = "\"Hello World\"";
      for (int count = 0; count < reps; count++) {
        resp.getWriter().print(hello);
        if (count != reps - 1) {
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

}
