package principale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Dico.Dictionnaire;
import Dico.Dictionnaire.Lang;
import Dico.Node;
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
	
	public compteTrou
	
	public ArrayList<ArrayList<Jeton>> compose (ArrayList<Jeton> main,ArrayList<Jeton> courante) {
		ArrayList<ArrayList<Jeton>> mots = new ArrayList<ArrayList<Jeton>> ();
		for (int i = 0; i<main.size(); i++) {
			ArrayList<Jeton> tmpMot = new ArrayList<Jeton> ();
			tmpMot.addAll(courante);
			tmpMot.add(main.get(i));
			mots.add(tmpMot);
			ArrayList<Jeton> tmpMain = new ArrayList<Jeton> ();
			for (int j=0; j<main.size(); j++)
				if (j!=i) 
					tmpMain.add(main.get(j));
			mots.addAll(compose(tmpMain,tmpMot));
		}
		return mots;
	}
	
	public int simulate(Plateau p) { return 0;}

	public ArrayList<Coord> parcours(Plateau p) {
		ArrayList<Coord> list = new ArrayList<Coord>();
		for (int i=0;i<p.getTaillePlateau();i++)
			for (int j=0;j<p.getTaillePlateau();j++) {
				Coord coord = new Coord(i,j);
				char c = p.getCase(coord).getJeton().get_lettre();
				if (c!='\0') 
					list.addAll(testVoisins(p,coord));
			}
		return list;
	}

	public ArrayList<Coord> testVoisins(Plateau p,Coord c) {
			ArrayList<Coord> voisins = new ArrayList<Coord>();
			Coord [] tmpCoord = new Coord [4];
			tmpCoord[0]= new Coord(c.a+1,c.b);
			tmpCoord[1]= new Coord(c.a-1,c.b);
			tmpCoord[2]= new Coord(c.a,c.b+1);
			tmpCoord[3]= new Coord(c.a,c.b-1);
			for (int i=0;i<tmpCoord.length;i++)
			if (p.getCase(tmpCoord[i]).getJeton()==null)
				voisins.add(tmpCoord[i]);
			return voisins;
	}
	
	public static void main (String [] args) {
		Plateau p = new Plateau(Lang.FR);
		ArrayList<Jeton> main = p.piocherColl(7);
		IA ia = new IA();
		ArrayList<ArrayList<Jeton>> test = ia.compose(new ArrayList<Jeton>(main),new ArrayList<Jeton>());
		test.toString();
	}
}


