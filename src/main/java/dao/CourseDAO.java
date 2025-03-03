package dao;

import entities.Course;
import entities.Project;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDAO {
    private static final String API_URL = "http://localhost:3000/parcours";

    public static List<Course> getParcours() {
        List<Course> parcoursList = new ArrayList<>();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String jsonResponse = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            JSONArray jsonArray = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonParcours = jsonArray.getJSONObject(i);
                List<Project> projets = new ArrayList<>();

                if (jsonParcours.has("projets")) {
                    JSONArray projetsArray = jsonParcours.getJSONArray("projets");
                    for (int j = 0; j < projetsArray.length(); j++) {
                        JSONObject jsonProjet = projetsArray.getJSONObject(j);
                        projets.add(new Project(
                                jsonProjet.getInt("id"),
                                jsonProjet.getString("titre"),
                                jsonProjet.getString("description"),
                                jsonProjet.optString("date_creation", "N/A"),
                                jsonProjet.optString("lien", "#"),
                                null // On ajoutera le parcours après
                        ));
                    }
                }

                Course parcours = new Course(
                        jsonParcours.getInt("id"),
                        jsonParcours.getString("titre"),
                        jsonParcours.getString("description"),
                        jsonParcours.optString("date_debut", "N/A"),
                        jsonParcours.optString("date_fin", "N/A"),
                        projets
                );

                // Associer le parcours aux projets
                for (Project projet : projets) {
                    projet.setCourse(parcours);
                }

                parcoursList.add(parcours);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parcoursList;
    }
}
