package Principale;

import java.util.Map;
import java.util.Map.Entry;

import Principale.Plateau.Coord;

/**
 * Classe de l'IA du Scrabble
 * @author guillaume
 * @version 1.0
 */
public class IA extends Joueur {

	/** Constructeur de la classe */
	public IA() {
		// TODO Auto-generated constructor stub
		super();
		this.type=false;	
		}

	public void jouer(Plateau p) {
		boolean echange = false;
		boolean joue = false;
		Entry<Map<Jeton,Coord>,Integer> mot = new Mot();
		while (!joue) {
			mot = simulate(p);
			if ((mot.getMot()==null) && !echange) {
				echange(); //Fonction à ajouter dans le joueur
				echange=true;
			}
			else {
				p.poser(mot);
				joue = true;
			}
		}
	}
	
	public Entry<Map<Jeton,Coord>,Integer> simulate(Plateau p) {
		Entry<Map<Jeton,Coord>,Integer> motFinal = null;
		Map<Jeton,Coord> list = parcours(p);
		return motFinal;
}
	public Map<Jeton,Coord> parcours(Plateau p) {
		Coord coord;
		Map<Jeton,Coord> list;
		for (int i=0;i<p.getTaillePlateau();i++)
			for (int j=0;j<p.getTaillePlateau();j++) {
				coord.a=i;
				coord.b=j;
				char c = p.getCase(coord).getJeton().getLettre()
				if (c!='\0') {
					list.add(p.getCase(coord).getJeton,coord);
				}
	}
}



