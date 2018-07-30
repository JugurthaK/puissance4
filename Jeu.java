import java.util.Scanner;

class Jeu {
	public static void main(String[] args) {

		// Init attributs
		Plateau p = new Plateau();
		InterfaceGraphique Ig = new InterfaceGraphique(p.getNbCol(), (p.getNbLigne() - 1));
		Ig.ajoutTexte();
		int nbCoups = 0;
		int maxCoups = p.getNbCol() * (p.getNbLigne() - 1);

		// Début du gameplay
		while (nbCoups < maxCoups && p.getVictoire() == false) {
			int numJoueur;

			if (nbCoups % 2 == 0) {
				numJoueur = 1;
				Ig.setTexte(numJoueur, p.getVictoire());
			} else {
				numJoueur = 2;
				Ig.setTexte(numJoueur, p.getVictoire());
			}

			int numColonne = 0;

			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}

			numColonne = Ig.getPositionClique();

			if (numColonne < p.getNbCol() && numColonne != -1) {
				if (p.ajoutPion(numColonne, numJoueur)) {
					Ig.ajoutPion(numColonne, p.getDernierPion(), numJoueur);
				}
				Ig.setTexte(numJoueur, p.getVictoire());
				System.out.println(p);
				nbCoups++;
			}

			if (nbCoups == maxCoups) {
				System.err.println("Le plateau est plein");
			} else {
				Ig.setTexte(numJoueur, p.getVictoire());
			}

		}
	}
}