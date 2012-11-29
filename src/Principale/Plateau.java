package Principale;


import java.util.LinkedList;

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
	
	private Case[][] plateau=null;
	private Jeton [] disponible=null;
	
	/**
	 * Seul le constructeur sans param�tre est
	 * n�cessaire donc disponible 
	 */
	public Plateau(){
		plateau=new Case[15][15];
		disponible=new Jeton[26];
		
		// Initialisation des jetons
		for(int i=0; i<26; ++i)
			disponible[i]=new Jeton((char)((int)'a'+i),i,1);
		
		// On doit tout dabord allouer toutes les cases
		// Initialement, elles ne sont pas sp�cial
		// n'ont pas de bonus
		for(int i=0; i<15; ++i){
			for(int j=0; j<15; ++j)
				plateau[i][j]=new Case(Bonus.none);
		}
		
		// On initialise toutes les cases sp�ciales
		// Il y a 5 types de cases.
		// On fait les 4 sp�ciales
		
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
		plateau[3][6].reset(Bonus.lettrecomptedouble);
		plateau[3][8].reset(Bonus.lettrecomptedouble);
		plateau[4][7].reset(Bonus.lettrecomptedouble);
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
	
	/**
	 * 
	 * @param mot : Jeton[]
	 * @param direction : Direction
	 * @param coord : Coord
	 * 
	 * @throws GameException
	 * Si l'execption est un affichage programmeur, l'erreur est remont�
	 * sans traitement
	 * 
	 * @return le nombre de point marqu�
	 */
	public int jouer(String jetons, Direction direction, Coord coord)throws GameException{
		int points=0;
		LinkedList<Bonus> bonus=new LinkedList<Bonus>();
		switch(direction){
		case bottom:
			if(jetons.length()+coord.b<15)
				for(int i=0; i<jetons.length(); ++i){
					try{
						// cet appel ne peut emmettre que 2 identifiant d'erreur
						// Alors, on place dans le matrice .placer()
						// le jeton que l'on a dans disponible[]
						// � l'indice (la lettre de jetons moins
						// le 'a' en ASCII
						points+=plateau[coord.a][coord.b+i].placer(disponible[(int)jetons.charAt(i)-97]);
						if(plateau[coord.a][coord.b+i].get_bonus()!=Bonus.none){
							bonus.add(plateau[coord.a][coord.b+i].get_bonus());
						}
					}catch(GameException e){
						if(e.display()==-1) // L'identifiant, ici
							throw e;		// ne peut �tre que -1
						else{				// ou 0
							points+=plateau[coord.a][coord.b+i].get_pt();
						}
					}
				}
			else throw new GameException("Sortie de plateau",new GameException("D�bordement du plateau de Plateau dans la m�thode jouer"),1);
			break;
		case right:
			if(jetons.length()+coord.a<15)
				for(int i=0; i<jetons.length(); ++i){
					try{
						// cet appel ne peut emmettre que 2 identifiant d'erreur 
						points+=plateau[coord.a+1][coord.b].placer(disponible[(int)jetons.charAt(i)-97]);
						if(plateau[coord.a+i][coord.b].get_bonus()!=Bonus.none){
							bonus.add(plateau[coord.a+i][coord.b].get_bonus());
						}
					}catch(GameException e){
						if(e.display()==-1) // L'identifiant, ici
							throw e;		// ne peut �tre que -1
						else{				// ou 0
							points+=plateau[coord.a+i][coord.b].get_pt();
						}
					}
				}
			else throw new GameException("Sortie de plateau",new GameException("D�bordement du plateau de Plateau dans la m�thode jouer"),1);
			break;
		}
		while(bonus.size()>0){
			switch(bonus.removeFirst()){
			case motcomptetriple:
				points*=3;
				break;
			case motcomptedouble:
				points*=2;
			default:
				// Il n'y a pas d'autre cas car ils ont �t� test�
				// De plus, les lettrecompte... ont d�j� �t� prix en compte
				// @see Case::placer(:Jeton)
				continue;
			}
			
		}
		return points;
	}
}