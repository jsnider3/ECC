package com.joshuasnider.ecc;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;
import javax.json.*;
import javax.servlet.http.*;

/**
 * Get list of trains on the way to your destination.
 */
public class MetroServlet extends CommonServlet {

    private String api_key = "1a78658b3c674ccb8e180ffa195b05e2";
    private JsonArray allstations;
    private String stationcode;

    public MetroServlet() {
      getAllStations();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
      resp.setContentType("aplication/json");
      try {
        String input = getInput(req);
        Logger log = Logger.getLogger(MetroServlet.class.getName());
        log.info(input);
        JsonObject obj = Json.createReader(new StringReader(input)).readObject();
        JsonObject nearby = getNearbyEntrances(obj.getJsonNumber("latitude").toString(),
          obj.getJsonNumber("longitude").toString(),
          obj.getJsonNumber("radius").toString());
        String destination = null;
        if (obj.containsKey("destination")) {
          destination = obj.getString("destination");
        }
        JsonObjectBuilder best = getBestStation(nearby,
          destination);
        addDepartures(best, destination);
        writeOutput(resp, best.build());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void addDepartures(JsonObjectBuilder start, String destination) {
      JsonBuilderFactory factory = Json.createBuilderFactory(
        new HashMap<String, Object>());
      JsonArrayBuilder departures = factory.createArrayBuilder();
      try {
        URL all = new URL(
          "https://excellathon.herokuapp.com/wmata/StationPrediction.svc/json/GetPrediction/" + stationcode);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(all.openStream()));
        StringBuffer input = new StringBuffer();
        String line;
        while((line = in.readLine()) != null) {
          input.append(line);
        }
        System.out.println(input.toString());
        JsonObject response =
          Json.createReader(new StringReader(input.toString())).readObject();
        JsonArray leavings = response.getJsonArray("Trains");
        for (int ind = 0; ind < leavings.size(); ind++) {
          JsonObject outbound = leavings.getJsonObject(ind);
          if (!outbound.getString("DestinationName").equals("Train")) {
            JsonObjectBuilder copy = factory.createObjectBuilder();
            copy.add("destination", outbound.getString("DestinationName"));
            copy.add("line", outbound.getString("Line"));
            copy.add("minutes", outbound.getString("Min"));
            //TODO Filter direction.
            departures.add(copy.build());
          }
        }
      } catch(Exception e) {
        e.printStackTrace();
      }
      start.add("departures", departures.build());
    }

    /**
     * Cache a list of all the WMATA train stations.
     */
    public JsonArray getAllStations() {
      if (allstations == null) {
        try {
          URL all = new URL(
            "https://api.wmata.com/Rail.svc/json/jStations?api_key=" + api_key);
          BufferedReader in = new BufferedReader(
              new InputStreamReader(all.openStream()));
          StringBuffer input = new StringBuffer();
          String line;
          while((line = in.readLine()) != null) {
            input.append(line);
          }
          System.out.println(input.toString());
          JsonObject response =
            Json.createReader(new StringReader(input.toString())).readObject();
          allstations = response.getJsonArray("Stations");
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
      return allstations;
    }

    public JsonObjectBuilder getBestStation(JsonObject entrances, String destination) {
      Set<String> stations = new HashSet<String>();
      for (int i = 0; i < entrances.getJsonArray("Entrances").size(); i++) {
        JsonObject entrance = entrances.getJsonArray("Entrances").getJsonObject(i);
        stations.add(entrance.getString("StationCode1"));
        stations.add(entrance.getString("StationCode2"));
      }
      stations.remove("");
      for (int i = 0; i < getAllStations().size(); i++) {
        JsonObject entrance = getAllStations().getJsonObject(i);
        if (stations.contains(entrance.getString("Code"))) {
          System.out.println(stationcode);
          stationcode = entrance.getString("Code");
          JsonBuilderFactory factory = Json.createBuilderFactory(
            new HashMap<String, Object>());
          return factory.createObjectBuilder()
           .add("station", entrance.getString("Name"))
           .add("stationLat", entrance.getJsonNumber("Lat"))
           .add("stationLong", entrance.getJsonNumber("Lon"));
        }
      }
      return null;
    }

    /**
     * Get a train station within a given radius of a coordinate.
     */
    public JsonObject getNearbyEntrances(String latitude, String longitude,
        String radius) {
      System.out.println(latitude);
      System.out.println(longitude);
      System.out.println(radius);
      try {
        URL nearbyStations = new URL(
          "https://api.wmata.com/Rail.svc/json/jStationEntrances?Lat=" +
          latitude + "&Lon=" + longitude + "&Radius=" + radius + "&api_key=" +
          api_key);
        BufferedReader in = new BufferedReader(
              new InputStreamReader(nearbyStations.openStream()));
        StringBuffer input = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
          input.append(line);
        }
        System.out.println(input.toString());
        return Json.createReader(new StringReader(input.toString())).readObject();
      } catch(Exception e) {
        e.printStackTrace();
      }
      return null;
    }

    /**
     * Write our JSON response out to the client.
     */
    public void writeOutput(HttpServletResponse resp, JsonObject best) {
      try {
        if (best != null) {
          resp.getWriter().println(best.toString());
        }
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

}

