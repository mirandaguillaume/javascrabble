package Principale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Dico.Dictionnaire;
import Dico.Dictionnaire.Lang;
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
		int mot;
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

	public ArrayList<ArrayList<Jeton>> compose (ArrayList<Jeton> main) {
		ArrayList<ArrayList<Jeton>> listMots = new ArrayList<ArrayList<Jeton>> ();
		Jeton [] tmpTab = new Jeton[main.size()]; 
		tmpTab = main.toArray(tmpTab);
		for (int i=0;i<main.size();i++) {
			ArrayList<Jeton> tmpMain = new ArrayList<Jeton>();
			for (int j=0;j<main.size();j++)
				if (i!=j)
					tmpMain.add(tmpTab[i]);
			listMots.addAll(compose(tmpMain));
		}
		return listMots;
	}

	public int simulate(Plateau p) { return 0;}

	public HashMap<Coord,Jeton> parcours(Plateau p) {
		HashMap<Coord,Jeton> list = new HashMap<Coord,Jeton>();
		for (int i=0;i<p.getTaillePlateau();i++)
			for (int j=0;j<p.getTaillePlateau();j++) {
				Coord coord = new Coord(i,j);
				char c = p.getCase(coord).getJeton().get_lettre();
				if (c!='\0') 
					list.put(coord,p.getCase(coord).getJeton());
			}
				return list;
	}
}


