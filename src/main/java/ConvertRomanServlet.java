package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Convert roman numerals to arabic.
 */
public class ConvertRomanServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      JsonArray arr = getUnnamedArgs(req);
      for (int count = 0; count < arr.size(); count++) {
        resp.getWriter().print(convertRoman(arr.getString(count)));
        if (count != arr.size() - 1) {
          resp.getWriter().print(", ");
        }
      }
      resp.getWriter().println("] }");
    }

    /**
     * Convert a Roman numeral to an Arabic or "normal" number.
     */
    public int convertRoman(String roman) {
      if (roman.startsWith("CM")) {
        return 900 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("CD")) {
        return 400 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("XC")) {
        return 90 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("XL")) {
        return 40 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("IX")) {
        return 9 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("IV")) {
        return 4 + convertRoman(roman.substring(2));
      }
      else if (roman.startsWith("M")) {
        return 1000 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("D")) {
        return 500 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("C")) {
        return 100 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("L")) {
        return 50 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("X")) {
        return 10 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("V")) {
        return 5 + convertRoman(roman.substring(1));
      }
      else if (roman.startsWith("I")) {
        return 1 + convertRoman(roman.substring(1));
      }
      return 0;
    }
}

