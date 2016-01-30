package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.*;

/**
 * Prints hello world repeated.
 */
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      int reps = 5;
      //
      try {
        String line = null;
        StringBuffer data = new StringBuffer();
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
          data.append(line);
        }
        String stringed = data.toString();
        reps = Integer.parseInt(
          stringed.substring(1, stringed.length() - 1));
      }
      catch (Exception e) {}
      //
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
