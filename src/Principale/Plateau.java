package Principale;

import java.util.ArrayList;
import java.util.LinkedList;

import Dico.Dictionnaire;
import Dico.Dictionnaire.Lang;
import Principale.Case;
import Principale.Case.Bonus;

/**
 * @author Cid's computer
 *
 * Comme son nom l'indique, cette classe
 * constitue les jetons de jeu
 */
public class Plateau {

	/**
	 * 
	 * @author Cid's computer
	 *
	 *	Direction permet de comprendre facilement
	 *	si le joueur veut placer sont mot verticalement
	 *	ou horizontalement 
	 *
	 */
	public enum Direction{right,bottom};
	
	/**
	 * 
	 * @author Cid's computer
	 *
	 *	Cette class Coord permet simplement de passer
	 *	un couple d'entier plut�t que de passer
	 *	deux entiers en param�tre
	 *
	 */
	public static class Coord{
		public int a;
		public int b;
		public Coord(int a, int b){
			this.a=a;
			this.b=b;
		}
	}
	
	private final int taillePlateau=15;
	private Case[][] plateau=null;
	private Sac s = null;
	private Dictionnaire d = null;
	
	/**
	 * Seul le constructeur sans param�tre est
	 * n�cessaire donc disponible 
	 */
	public Plateau(Lang lang){
		plateau=new Case[taillePlateau][taillePlateau];
		
		Dictionnaire d = new Dictionnaire (lang);
		s = new Sac(d);
		
		
		// On doit tout dabord allouer toutes les cases
		// Initialement, elles ne sont pas sp�cial
		// n'ont pas de bonus
		for(int i=0; i<taillePlateau; ++i){
			for(int j=0; j<taillePlateau; ++j)
				plateau[i][j]=new Case(Bonus.none);
		}
		
		// On initialise toutes les cases sp�ciales
		// Il y a 5 types de cases.
		// On fait les 4 sp�ciales.
		// Il aurait �t� plus efficasse d'effectuer le new 
		// � ce moment-ci mais cela emp�chait le
		// for(){for() ...}} et prendrai bien plus de ligne de
		// code.
		
		// case sp� motcomptetriple
		plateau[0][0].reset(Bonus.motcomptetriple);
		plateau[14][14].reset(Bonus.motcomptetriple);
		plateau[14][0].reset(Bonus.motcomptetriple);
		plateau[0][14].reset(Bonus.motcomptetriple);
		plateau[0][7].reset(Bonus.motcomptetriple);
		plateau[7][0].reset(Bonus.motcomptetriple);
		plateau[14][7].reset(Bonus.motcomptetriple);
		plateau[7][14].reset(Bonus.motcomptetriple);
		// case motcomptedouble
		plateau[1][1].reset(Bonus.motcomptedouble);
		plateau[2][2].reset(Bonus.motcomptedouble);
		plateau[3][3].reset(Bonus.motcomptedouble);
		plateau[4][4].reset(Bonus.motcomptedouble);
		plateau[10][10].reset(Bonus.motcomptedouble);
		plateau[11][11].reset(Bonus.motcomptedouble);
		plateau[12][12].reset(Bonus.motcomptedouble);
		plateau[13][13].reset(Bonus.motcomptedouble);
		plateau[13][1].reset(Bonus.motcomptedouble);
		plateau[12][2].reset(Bonus.motcomptedouble);
		plateau[11][3].reset(Bonus.motcomptedouble);
		plateau[10][4].reset(Bonus.motcomptedouble);
		plateau[4][10].reset(Bonus.motcomptedouble);
		plateau[3][11].reset(Bonus.motcomptedouble);
		plateau[2][12].reset(Bonus.motcomptedouble);
		plateau[1][13].reset(Bonus.motcomptedouble);
		// case lettrecomptedouble
		plateau[3][0].reset(Bonus.lettrecomptedouble);
		plateau[11][7].reset(Bonus.lettrecomptedouble);
		plateau[7][11].reset(Bonus.lettrecomptedouble);
		plateau[6][12].reset(Bonus.lettrecomptedouble);
		plateau[8][12].reset(Bonus.lettrecomptedouble);
		plateau[12][8].reset(Bonus.lettrecomptedouble);
		plateau[12][6].reset(Bonus.lettrecomptedouble);
		plateau[2][6].reset(Bonus.lettrecomptedouble);
		plateau[2][8].reset(Bonus.lettrecomptedouble);
		plateau[3][7].reset(Bonus.lettrecomptedouble);
		plateau[7][4].reset(Bonus.lettrecomptedouble);
		plateau[8][3].reset(Bonus.lettrecomptedouble);
		plateau[6][3].reset(Bonus.lettrecomptedouble);
		plateau[14][11].reset(Bonus.lettrecomptedouble);
		plateau[11][14].reset(Bonus.lettrecomptedouble);
		plateau[14][3].reset(Bonus.lettrecomptedouble);
		plateau[3][14].reset(Bonus.lettrecomptedouble);
		plateau[0][11].reset(Bonus.lettrecomptedouble);
		plateau[11][0].reset(Bonus.lettrecomptedouble);
		plateau[6][6].reset(Bonus.lettrecomptedouble);
		plateau[6][8].reset(Bonus.lettrecomptedouble);
		plateau[8][6].reset(Bonus.lettrecomptedouble);
		plateau[8][8].reset(Bonus.lettrecomptedouble);
		plateau[0][3].reset(Bonus.lettrecomptedouble);
		// case lettrecomptetriple
		plateau[5][1].reset(Bonus.lettrecomptetriple);
		plateau[13][9].reset(Bonus.lettrecomptetriple);
		plateau[9][13].reset(Bonus.lettrecomptetriple);
		plateau[5][13].reset(Bonus.lettrecomptetriple);
		plateau[13][5].reset(Bonus.lettrecomptetriple);
		plateau[9][9].reset(Bonus.lettrecomptetriple);
		plateau[5][5].reset(Bonus.lettrecomptetriple);
		plateau[5][9].reset(Bonus.lettrecomptetriple);
		plateau[9][5].reset(Bonus.lettrecomptetriple);
		plateau[9][1].reset(Bonus.lettrecomptetriple);
		plateau[1][9].reset(Bonus.lettrecomptetriple);
		plateau[1][5].reset(Bonus.lettrecomptetriple);		
	}
	
	/**
	 * Accesseurs
	 */
	
	public Case get_plateau(Coord coord) {
		return plateau[coord.a][coord.b];
	}

	public Jeton get_disponible(int i) {
		return disponible[i];
	}
	
	public Case getCase (Coord c) {
		return plateau[c.a][c.b];
	}
	
	public int getTaillePlateau() {
		return taillePlateau;
	}
	
	public int getNbLettres() {
		return nbLettres;
	}
	
	private int bonusSurMot(int points, LinkedList<Bonus> bonus){
		while(bonus.size()>0){
			switch(bonus.removeFirst()){
			case motcomptetriple:
				points*=3;
				break;
			case motcomptedouble:
				points*=2;
				break;
			default:
				// Il n'y a pas d'autre cas car ils ont �t� test�
				// De plus, les lettrecompte... ont d�j� �t� prix en compte
				// @see Case::placer(:Jeton)
				continue;
			}
		}
		return points;
	}
	
	
	
	/**
	 * 
	 * @param coord coordonn�e du croisement d'un mot.
	 * @param direction si la m�thode descendait, alors
	 * nous parcourerons � l'horizontal et inversement.
	 * @return le nombre de point qu'apporte ce mot.
	 */
	private int get_mot(Coord coord, Direction direction, LinkedList<Bonus> bonus) {
		int points=0, tmp=0;
		switch(direction){
		case right:	// nous parcourrons la partie gauche
			tmp=coord.a;
			while(plateau[coord.a][coord.b].get_lettre()!='\0'){
				points+=plateau[coord.a][coord.b].get_pt();
				if(plateau[coord.a][coord.b].get_bonus()!=Bonus.none)bonus.add(plateau[coord.a][coord.b].get_bonus());
				--coord.a;
			}
			// on reprend de la o� on est partie
			// et on repart dans la partie droite.
			coord.a=tmp;
			while(plateau[coord.a][coord.b].get_lettre()!='\0'){
				points+=plateau[coord.a][coord.b].get_pt();
				if(plateau[coord.a][coord.b].get_bonus()!=Bonus.none)bonus.add(plateau[coord.a][coord.b].get_bonus());
				++coord.a;
			}
			break;
		case bottom:
			tmp=coord.b;
			// on prend la partie basse
			while(plateau[coord.a][coord.b].get_lettre()!='\0'){
				points+=plateau[coord.a][coord.b].get_pt();
				if(plateau[coord.a][coord.b].get_bonus()!=Bonus.none)bonus.add(plateau[coord.a][coord.b].get_bonus());
				--coord.b;
			}
			coord.a=tmp;
			// puis la partie haute
			while(plateau[coord.a][coord.b].get_lettre()!='\0'){
				points+=plateau[coord.a][coord.b].get_pt();
				if(plateau[coord.a][coord.b].get_bonus()!=Bonus.none)bonus.add(plateau[coord.a][coord.b].get_bonus());
				++coord.b;
			}
			break;
		}
		return bonusSurMot(points, bonus);
	}
	
	/**
	 * @param jetons param�tre de jouer(:String,:Direction,:Coord);
	 * @param coord param�tre de jouer(:String,:Direction,:Coord);
	 * @param bonus permet de renvoyer la liste des bonus rencontr�s.
	 * @see jouer(:String,:Direction,:Coord);
	 * @return le nombre de point calcul�.
	 * 
	 * Cette m�thode permet d'al�ger la m�thode jouer(...);
	 * Elle est l�quivalent de jouer_droite sauf que jouer_bas(...)
	 * est faite pour jouer en vertical
	 */
	private int jouer_bas(String jetons, Coord coord){
		int points_mot=0, other_mot=0;
		LinkedList<Bonus> bonus=new LinkedList<Bonus>();
		if(jetons.length()+coord.b<taillePlateau){
			for(int i=0; i<jetons.length(); ++i){
				try{
					// cet appel ne peut emmettre que 2 identifiant d'erreur
					// Alors, on place dans le matrice .placer()
					// le jeton que l'on a dans disponible[]
					// � l'indice (la lettre de jetons moins
					// le 'a' en ASCII
					points_mot+=plateau[coord.a][coord.b+i].placer(disponible[(int)jetons.charAt(i)-97]);
				}catch(GameException e){
					if(e.display()==-1) // L'identifiant, ici
						throw e;		// ne peut �tre que -1
					else{				// ou 0
						other_mot+=get_mot(new Coord(coord.a,coord.b+i), Direction.right, bonus);
					}
				}
				if(plateau[coord.a][coord.b+i].get_bonus()!=Bonus.none){
					bonus.add(plateau[coord.a][coord.b+i].get_bonus());
				}
			}
		}
		else throw new GameException("Sortie de plateau",new GameException("D�bordement du plateau de Plateau dans la m�thode jouer"));
		return bonusSurMot(points_mot, bonus)+other_mot;
	}

	/**
	 * @param jetons param�tre de jouer(:String,:Direction,:Coord);
	 * @param coord param�tre de jouer(:String,:Direction,:Coord);
	 * @param bonus permet de renvoyer la liste des bonus rencontr�s.
	 * @see jouer(:String,:Direction,:Coord);
	 * @return le nombre de point calcul�.
	 * 
	 * Cette m�thode permet d'al�ger la m�thode jouer(...);
	 * Elle est l�quivalent de jouer_droite sauf que jouer_bas(...)
	 * est faite pour jouer en vertical
	 */
	private int jouer_droite(String jetons, Coord coord){
		int points_mot=0, other_mot=0;
		LinkedList<Bonus> bonus=new LinkedList<Bonus>();
		if(jetons.length()+coord.a<taillePlateau)
			for(int i=0; i<jetons.length(); ++i){
				try{
					// cet appel ne peut emmettre que 2 identifiant d'erreur 
					points_mot+=plateau[coord.a+i][coord.b].placer(disponible[(int)jetons.charAt(i)-97]);
					if(plateau[coord.a+i][coord.b].get_bonus()!=Bonus.none){
						bonus.add(plateau[coord.a+i][coord.b].get_bonus());
					}
				}catch(GameException e){
					if(e.display()==-1) // L'identifiant, ici
						throw e;		// ne peut �tre que -1
					else{				// ou 0
						other_mot+=get_mot(new Coord(coord.a+i,coord.b),Direction.bottom,bonus);
					}
				}
			}
		else throw new GameException("Sortie de plateau",new GameException("D�bordement du plateau de Plateau dans la m�thode jouer"),1);
		return bonusSurMot(points_mot, bonus)+other_mot;
	}
	
	/**
	 * 
	 * @param mot : Jeton[]
	 * @param direction : Direction
	 * @param coord : Coord
	 * 
	 * @throws GameException
	 * Si l'execption est un affichage programmeur, l'erreur est remont�
	 * sans traitement
	 * @see GameException
	 * @see Case.placer()
	 * 
	 * @return le nombre de point marqu�
	 * 	En entr�e, jetons:String apporte le mot � placer,
	 * en sortie jetons contient tout les mots cr�� avec ce jeux 
	 */
	public int jouer(String jetons, Direction direction, Coord coord)throws GameException{
		int points=0;
		switch(direction){
		case bottom:
			points=jouer_bas(jetons, coord);
			break;
		case right:
			points=jouer_droite(jetons, coord);
			break;
		}
		return points;
	}

	/**
	 * 
	 * @param i nombre de jeton que l'on souhaite
	 * @return un tableau de taille i ayant les 
	 *  jetons � jouer. 
	 */
	public Jeton [] piocher(int i){
		Jeton [] ret=new Jeton[i];
		if(i<=0) throw new GameException("appel de Plateau.piocher(i:int) avec i n�gatif");
		int lettre=0;
		while(i>0){ // d�cr�ment� 
			lettre=(int)Math.random()%nbLettres;
			try{
				disponible[lettre].prendre((int)Math.random()%3);
				// le d�cr�ment se fait ici !
				ret[--i]=new Jeton(disponible[lettre].get_lettre(), disponible[lettre].get_score(),1);
			}catch(GameException e){
				continue;
			}
		}
		return ret;
	}
	
	public Jeton[] piocherColl (int i) {
		return s.piocher(i);
	}
	
	public void defausser (Jeton [] defausse) {
		s.remettre(defausse);
	}
	
}