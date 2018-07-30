public class Case {
    private int contenu;

// Début des constructeurs
    public Case (){
        this.contenu = 0;
    }

    public Case (int x){
        if (x == 1 || x == 2){
            this.contenu = x;
        } else {
            System.out.println("Erreur : La valeur doit être comprise entre 1 & 2");
        }
    }

    public Case (Case c){
        this.contenu = c.contenu;
    }
// Début des accesseurs
    public int getContenu(){
        return contenu;
    }
    public boolean estVide(){
        return (contenu == 0);
    }
    public void setContenu(int x){
        if (x == 1 || x == 2){
            this.contenu = x;
        } else {
            System.out.println("Erreur : La valeur doit être comprise entre 1 & 2");
        }
    }
// Méthodes qu'on sait pas comment ça s'appelle
    public boolean equals(Case c){
        return (this.contenu == c.contenu);
    }

    public String toString(){
        if (this.contenu == 0){
            return ("X");
        } else {
            return (Integer.toString(contenu));
        }
    }
}