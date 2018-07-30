class Ligne {
    
    Case[] tabCases;
    private int nbLigne;
    

    public Ligne(){
        this(7);
    }

    public Ligne(int n){
        tabCases = new Case[n];
        this.nbLigne = n;
        for (int i = 0; i < n; i++){
            tabCases [i] = new Case();
        }
    }
// Déclaration des getters & setters
    public int getValeur(int n){
        return tabCases[n].getContenu();
    }
    public int getNbLigne(){
        return nbLigne;
    }

    public void setValeur(int p, int v){
        if (v == 0 || v == 1 || v == 2){
            if (p < tabCases.length){
                tabCases[p].setContenu(v);
            } else {
                System.out.println("Vous dépassez la taille du tableau");
            }
        } else {
            System.out.println("Valeur invalide");
        }
    }

    public void setLigne(String str){
        String tabValeurs[] = str.split("");
        boolean isAllGood = true;
        
        // Vérification que tous les caractères sont des entiers compris entre 0 & 2
        int i = 0;
        while (i < tabValeurs.length || isAllGood == true){
            if (Integer.parseInt(tabValeurs[i],10) < 0 || Integer.parseInt(tabValeurs[i],10) > 2){
                isAllGood = false;
            }
            i++;
        }
        // Vérification longueur de chaine == taille du tableau
        if (tabValeurs.length != tabCases.length){
            isAllGood = false;
        }
        if (isAllGood){
            for (int e = 0; e < tabCases.length; e++){
                tabCases[e].setContenu(Integer.parseInt(tabValeurs[e],10));
            } 
        } else {
            System.out.println("Chaîne invalide");
        }
    }

    public int getTaille(){
        return tabCases.length;
    }
    
    public boolean estVide(int n){
        return (tabCases[n].estVide());
    }
    
    // Méthode toString
    public String toString(){
        String finalString = "";
        for (int i = 0; i < tabCases.length; i++){
            finalString += " "+tabCases[i].toString();
        }
        return finalString;
    }

    
}