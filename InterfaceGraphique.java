import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;

class InterfaceGraphique {

    private Fenetre f;
    private Texte t;
    private Souris s;
    private int largeur;
    private int hauteur;
    private int positionClique;

    public InterfaceGraphique() {
        this(7, 6);
    }

    public InterfaceGraphique(int largeur, int hauteur) {

        /* Crée une fenêtre de la meme taille que le nombre de ligne
        Permet ainsi de mieux gérer le clique souris pour ajouter un pion */
        largeur = largeur * 100;
        hauteur = hauteur * 100;
        f = new Fenetre("Puissance 4", largeur, hauteur + 100);
        s = f.getSouris();

        Point origine = new Point();
        Rectangle r = new Rectangle(Couleur.BLEU, origine, largeur, hauteur, true);
        Rectangle info = new Rectangle(Couleur.GRIS, new Point(hauteur, 0), 0, 50, true);
        f.ajouter(r);
        f.ajouter(info);

        /* Boucle de création des pions, */
        for (int i = 0; i < (largeur) / 100; i++) {
            for (int j = 0; j < (hauteur) / 100; j++) {
                Point p = new Point((i * 100), (j * 100));
                Carre c = new Carre(p, 95);
                Cercle ce = new Cercle(Couleur.BLANC, c, true);
                f.ajouter(ce);
            }
        }
        f.rafraichir();
    }

    // Permet d'ajouter un pion
    public void ajoutPion(int numColonne, int numLigne, int numJoueur) {
        Point p = new Point((numColonne * 100), (numLigne * 100));
        Carre c = new Carre(p, 95);
        if (numJoueur == 1) {
            Cercle ce = new Cercle(Couleur.ROUGE, c, true);
            f.ajouter(ce);
        } else {
            Cercle ce = new Cercle(Couleur.VERT, c, true);
            f.ajouter(ce);
        }
        f.rafraichir();
    }

    // Permet d'ajouter le texte pour les joueurs
    public void ajoutTexte() {

        t = new Texte(Couleur.NOIR, "", new Font("Calibri", Font.TYPE1_FONT, 50), new Point(350, hauteur + 650));

        f.ajouter(t);
        f.rafraichir();
    }

    public void setTexte(int numJoueur, boolean a) {
        if (numJoueur == 1) {
            t.setCouleur(Couleur.ROUGE);
        } else {
            t.setCouleur(Couleur.VERT);
        }

        if (a) {
            t.setTexte("Joueur " + numJoueur + " a win !");
        } else {
            t.setTexte("Joueur " + numJoueur);

        }
    }

    public int getPositionClique() {
        while (s.getClicGauche() != true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }
        }
        return ((int) ((s.getPosition().getX()) / 100));
    }
}