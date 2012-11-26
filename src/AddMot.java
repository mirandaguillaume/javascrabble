/**
 * Classe servant à faire l'ajout threadé de mot dans l'arbre à partir du dictionnaire
 * @author guillaume
 *
 */
public class AddMot implements Runnable {

	/** Le mot à ajouter */
	private String mot;
	/** L'arbre dans lequel ajouter le mot */
	private Tree a;

	/**
	 * Constructeur de la classe 
	 * @param a L'arbre dans lequel ajouter le mot
	 * @param mot Le mot à ajouter
	 */
	public AddMot(Tree a,String mot) {
		// TODO Auto-generated method stub
		this.a=a;
		this.mot=mot;
	}

	/**
	 * Override de la fonction run de l'interface runnable
	 * @see Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		a.addMot(mot);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree t = new Tree();
		Thread t1 = new Thread ( new AddMot(t,"a") );
		Thread t2 = new Thread ( new AddMot(t,"b") );
		t1.start();
		t2.start();
	}

}
