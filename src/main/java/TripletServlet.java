package com.joshuasnider.ecc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.servlet.http.*;

/**
 * Show the pythagorean triplets.
 */
public class TripletServlet extends CommonServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      resp.getWriter().print("{ \"data\": [");
      List<List<Integer>> triplets = getTriplets(getUnnamedArgs(req).getInt(0));
      for (int count = 0; count < triplets.size(); count++) {
        resp.getWriter().print("[");
        for (int index = 0; index < triplets.get(count).size(); index++) {
          resp.getWriter().print(triplets.get(count).get(index));
          if (index != triplets.get(count).size() - 1) {
            resp.getWriter().print(", ");
          }
        }
        resp.getWriter().print("]");
        if (count != triplets.size() - 1) {
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

    public List<List<Integer>> getTriplets(int max) {
      List<List<Integer>> triplets = new ArrayList<>();

      for (int adj = 1; adj <= max; adj++) {
        for (int opp = adj + 1; opp <= max; opp++) {
          for (int hyp = opp + 1; hyp <= max; hyp++) {
            if ((int)(Math.pow(adj, 2) + Math.pow(opp, 2)) == (int)Math.pow(hyp, 2)) {
              if (hyp <= max) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(adj);
                triplet.add(opp);
                triplet.add(hyp);
                triplets.add(triplet);
              }
            }
          }
        }
      }
      return triplets;
    }


}

