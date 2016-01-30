package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.Json;
import javax.servlet.http.*;

/**
 * Shared servlet code.
 */
public abstract class CommonServlet extends HttpServlet {

  public JsonArray getUnnamedArgs(HttpServletRequest req) {
    try {
      String line = null;
      StringBuffer data = new StringBuffer();
      BufferedReader reader = req.getReader();
      while ((line = reader.readLine()) != null) {
        data.append(line);
      }
      return Json.createReader(new StringReader(data.toString())).readArray();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}

