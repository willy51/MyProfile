package entities;

public class Project {
    private int id;
    private String titre;
    private String description;
    private String dateCreation;
    private String lien;
    private Course course; // Relation ManyToOne

    public Project(int id, String titre, String description, String dateCreation, String lien, Course course) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateCreation = dateCreation;
        this.lien = lien;
        this.course = course;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public String getDateCreation() { return dateCreation; }
    public String getLien() { return lien; }
    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }
}

