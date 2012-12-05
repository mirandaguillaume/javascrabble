package Principale;

/**
 * @author Cid's computer 
 */

/**
 * 
 * ident:
 * 	-1 : l'erreur contient une erreur d�celable que par un
 * 		programme,
 * 	0 : tentative de placer un jeton sur une case d�j�
 * 		occup�, utilis� dans Case::placer(:Jeton),
 *	1 : dans Plateau::jouer(:Jeton,:Direction,:Coord)
 *		correspond � une sortie du membre plateau:Case[][],
 */

@SuppressWarnings("serial")
public class GameException extends RuntimeException{

	private int ident=-1;
	
	/** 
	 * Ces constructeurs sont n�cessaires pour, par exemple, pouvoir
	 * �crire : throw new GameExeption("ERREUR !",new GameException("RAISON"));
	 */
	
	public GameException(String message, Throwable cause){
		super(message, cause);
	}
	public GameException(String message){
		super(message);
	}
	public GameException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Si une exeption � besoin d'�tre trait� par la machine
	 * et non pas un programmeur, l'identifiant � �t� la solution
	 * que j'ai choisie
	 */
	public GameException(String message, Throwable cause, int i){
		super(message, cause);
		ident=i;
	}
	
	// Permet de faciliter l'affichage des GameExecption
	/**
	 * 
	 * @return l'identifiant de l'erreur
	 * s'il vaut -1, l'erreur est un affichage
	 * s'il vaut 0, l'erreur peut �tre oubli�
	 * dans un autre cas, le programme doit trait�
	 * automatiquement le cas de l'apparition de cet
	 * identifiant.
	 */
	public int display(){
		if(ident==-1){
			System.out.println(this.getMessage());
			System.out.println(this.getCause().getMessage());
			return -1;
		}
		else return ident;
	}
}
