package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Convert arabic numerals to roman numerals.
 */
public class ConvertArabicServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        resp.getWriter().print("\"" + convertArabic(arr.getInt(count)) + "\"");
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

    public String convertArabic(int arabic) {
      if (arabic >= 1000) {
        return "M" + convertArabic(arabic - 1000);
      }
      else if (arabic >= 900) {
        return "CM" + convertArabic(arabic - 900);
      }
      else if (arabic >= 500) {
        return "D" + convertArabic(arabic - 500);
      }
      else if (arabic >= 400) {
        return "CD" + convertArabic(arabic - 400);
      }
      else if (arabic >= 100) {
        return "C" + convertArabic(arabic - 100);
      }
      else if (arabic >= 90) {
        return "XC" + convertArabic(arabic - 90);
      }
      else if (arabic >= 50) {
        return "L" + convertArabic(arabic - 50);
      }
      else if (arabic >= 40) {
        return "XL" + convertArabic(arabic - 40);
      }
      else if (arabic >= 10) {
        return "X" + convertArabic(arabic - 10);
      }
      else if (arabic >= 9) {
        return "IX" + convertArabic(arabic - 9);
      }
      else if (arabic >= 5) {
        return "V" + convertArabic(arabic - 5);
      }
      else if (arabic >= 4) {
        return "IV" + convertArabic(arabic - 5);
      }
      else if (arabic >= 1) {
        return "I" + convertArabic(arabic - 1);
      }
      return "";
    }
}

