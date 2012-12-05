package principale;

import exception.GameMasterException;

/**
 * @author Cid's computer
 *
 */
public class GameMaster{
	private Plateau plateau=new Plateau();
	private Joueur [] joueurs=new Joueur[10]; // Limite de 10 joueurs maximum par GameMaster
	
	public void add(String nom, boolean type)throws GameMasterException{
		if(joueurs[9]==null){
			int nb=0;
			Joueur pc=joueurs[0];
			for(int i=0; i<10 && pc!=null; ++i)
				pc=joueurs[nb=i];
			joueurs[nb]=new Joueur(nom,type);
		}
		else throw new GameMasterException("GameMaster saturé", new GameMasterException("Tentative d'insertion d'un joueur dans un GameMaster saturé"));
	}
	
	protected void jouer(Joueur j, String mot, Direction dir, Coord coord){
		plateau.jouer(mot,dir,coord);
		
	}
}
