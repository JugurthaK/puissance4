class Plateau {
    
    private Ligne[] tabLignes;
    private int nbColonne; 
    int dernierPion;
    boolean gagne;

    public Plateau() {
        // This fait référence à l'objet lui-même et initialise les 2 attributs avec les paramètres
        this(7, 6);
    }

    public Plateau(int nbColonne, int nbLigne) {
        this.nbColonne = nbColonne;
        tabLignes = new Ligne[nbLigne];
        for (int i = 0; i < nbLigne; i++) {
            tabLignes[i] = new Ligne(nbColonne);
        }
    }

    public int getXY(int numColonne, int numLigne) {
        return tabLignes[numLigne].getValeur(numColonne);
    }

    private void ajoutPion(int numColonne, int numLigne, int numJoueur) {

        // si la ligne en dessous est occupée au même endroit ou que c'est la dernière ligne
        if (numLigne == 0 || tabLignes[numLigne - 1].estVide(numColonne) == false) {
            // dans ce cas on pose le pion
            tabLignes[numLigne].setValeur(numColonne, numJoueur);
            setDernierPion(numLigne);
            
            if (gagne(numLigne, numColonne)) {
                setVictoire(true);
            }

        } else {
            // sinon, on recommence sur la ligne du dessous
            ajoutPion(numColonne, numLigne - 1, numJoueur);
        }
    }

    // Création d'une méthode qui refait appel à ajout pion, mais qui cette fois ci ne prend que 2 paramètres
    public boolean ajoutPion(int numColonne, int numJoueur) {

        try {
            // Défition de la valeur maximale de la ligne sur laquelle on se situe
            int ligneMax = tabLignes[numColonne].getTaille() - 1;
            ajoutPion(numColonne, ligneMax, numJoueur);
            return true;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("La colonne est pleine");
            return false;
        }
    }

    private int compteDirection(int numLigne, int numColonne, int decalageLigne, int decalageColonne) {
        int contenu = tabLignes[numLigne].tabCases[numColonne].getContenu();
        int contenuDecale;
       
        try {
            contenuDecale = tabLignes[numLigne + decalageLigne].tabCases[numColonne + decalageColonne].getContenu();
        } catch (ArrayIndexOutOfBoundsException e) {
            contenuDecale = 0;
        }

        if (contenu == contenuDecale) {
            return 1 + compteDirection(numLigne + decalageLigne, numColonne + decalageColonne, decalageLigne,
                    decalageColonne);
        }
        return 1;
    }

    private boolean gagne(int numLigne, int numColonne) {

        // Ensemble des tests pour le système de victoire
        if (compteDirection(numLigne, numColonne, 1, 0) + compteDirection(numLigne, numColonne, -1, 0) - 1 >= 4) {
            return true;
        }
        if (compteDirection(numLigne, numColonne, 1, 1) + compteDirection(numLigne, numColonne, -1, 1) - 1 >= 4) {
            return true;
        }
        if (compteDirection(numLigne, numColonne, 1, -1) + compteDirection(numLigne, numColonne, -1, 1) - 1 >= 4) {
            return true;
        }
        if (compteDirection(numLigne, numColonne, 0, -1) >= 4) {
            return true;
        }
        return false;
    }

    public boolean getVictoire(){
        return gagne;
    }

    public void setVictoire(boolean bool){
        this.gagne = bool;
    }

    public int getNbCol(){
        return nbColonne;
    }
    
    public int getNbLigne(){
        return tabLignes[0].getNbLigne();
    }
    /* Cette méthode permet de récupérer la ligne du dernier pion ajouté,
    elle permet en gros la communication entre la class Jeu et la class Plateau
    Ainsi, je n'ai pas besoin de relire tout le tableau à chaque fois */
    public int getDernierPion(){
        return this.dernierPion;
    }

    public void setDernierPion(int x){
        this.dernierPion = x;
    }
    
    public String toString() {
        String finalString = new String();
        for (int i = tabLignes.length - 1; i >= 0; i--) {
            finalString += tabLignes[i].toString() + "\n";
        }
        return finalString;
    }
}