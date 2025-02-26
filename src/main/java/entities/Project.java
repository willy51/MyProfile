package entities;

public class Project {
    private int id;
    private String titre;
    private String description;
    private String lien;

    public Project(int id, String titre, String description, String lien) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.lien = lien;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public String getLien() { return lien; }

    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setLien(String lien) { this.lien = lien; }
}

