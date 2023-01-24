package fr.hamtec;

public class DVD {
    long id;
    String titre;
    int annee;
    String acteurs[];
    String resume;

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnnee() {
        return annee;
    }

    public String[] getActeurs() {
        return acteurs;
    }

    public String getResume() {
        return resume;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setActeurs(String[] acteurs) {
        this.acteurs = acteurs;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
