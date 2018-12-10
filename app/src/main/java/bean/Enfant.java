package bean;

public class Enfant {
    private String prenom;
    private String sexe;
    private int naissance;
    private boolean lettre_recue;
    private boolean sage;
    private boolean cadeaux_livres;

    public Enfant (String prenom, String sexe, int naissance, boolean sage){
        this.prenom = prenom;
        this.sexe = sexe;
        this.naissance = naissance;
        this.lettre_recue = false;
        this.sage = sage;
        this.cadeaux_livres = false;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getNaissance() {
        return naissance;
    }

    public void setNaissance(int naissance) {
        this.naissance = naissance;
    }

    public boolean isLettre_recue() {
        return lettre_recue;
    }

    public void setLettre_recue(boolean lettre_recue) {
        this.lettre_recue = lettre_recue;
    }

    public boolean isSage() {
        return sage;
    }

    public void setSage(boolean sage) {
        this.sage = sage;
    }

    public boolean isCadeaux_livres() {
        return cadeaux_livres;
    }

    public void setCadeaux_livres(boolean cadeaux_livres) {
        this.cadeaux_livres = cadeaux_livres;
    }
}
