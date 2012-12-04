package Principale;

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
		Mot mot = new Mot();
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
	
	public Mot simulate(Plateau p) {
		Mot motH,motV,motFinal;
		Thread t1 = new Thread ( new VerticalParcours(p,motV,main));
		Thread t2 = new Thread ( new HorizontalParcours(p,motH,main));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		if (motH.getScore()<motV.getScore()) 
			motFinal = motV; 
		else 
			motFinal = motH;
		return motFinal;
	}
}


