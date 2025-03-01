package dao;

import entities.Project;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectDAO {
    private static final String API_URL = "http://localhost:3000/projets";

    public static List<Project> getProjets() {
        List<Project> projets = new ArrayList<>();

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
                JSONObject jsonProjet = jsonArray.getJSONObject(i);
                Project projet = new Project(
                        jsonProjet.getInt("id"),
                        jsonProjet.getString("titre"),
                        jsonProjet.getString("description"),
                        jsonProjet.optString("date_creation", "N/A"),
                        jsonProjet.optString("lien", "#"),
                        null
                );
                projets.add(projet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projets;
    }
}
