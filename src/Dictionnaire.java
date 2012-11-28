import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe du dictionnaire de mots du jeu de scrabble
 * @author guillaume
 * @version 1.0
 */
public class Dictionnaire {

	/** La variable qui contient le pointeur vers l'arbre modélisant le dictionnaire */
	private Tree liste;
	/** Variables constantes qui contiennent le chemin relatif vers les dictionnaires */ 
	final String dicoFR = "dico_francais.txt";
	final String dicoEN = "english_dict.txt";

	/** Variable qui contient la langue utilisée */
	public Lang actuelle;

	/** 
	 * Enum qui permet de savoir quelle est la langue utilisée
	 * @author guillaume
	 * @version 1.0
	 */
	public enum Lang { FR, EN };

	/** 
	 * Constructeur de la classe 
	 * @param used La langue voulue
	 */
	public Dictionnaire(Lang used) {
		// TODO Auto-generated constructor stub
		liste = new Tree();
		actuelle=used;
		init();
	}

	/** Fonction qui initialise le dictionnaire */
	private void init() {
		// TODO Auto-generated method stub
		String ToOpen = null;
		Scanner Opened =null;
		switch (actuelle)
		{
		case FR : ToOpen=dicoFR;break;
		case EN : ToOpen=dicoEN;break;
		}
		try {
			Opened=new Scanner(new File(ToOpen));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReadDico(Opened);

		Opened.close();
	}

	/**
	 * Lit le dictionnaire et range les mots dans l'arbre 
	 * @param opened Le flux vers le dictionnaire
	 */
	private void ReadDico(Scanner opened) {
		// TODO Auto-generated method stub
		String ligne = null;
		while (opened.hasNextLine())
		{
			ligne=opened.nextLine();
			if (ligne != null) {  
				liste.addMot(ligne.toLowerCase());
			}
		}
	}

	public void searchMot(String [] s, Boolean [] b)
	{
		Thread [] t = new Thread [s.length];
		for (int i=0;i<s.length;i++)
		{
			t[i] = new Thread (new SearchMot(liste,s[i],b[i]));
			t[i].start();
		}
		try {
			for (int i=0;i<t.length;i++)
				t[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionnaire d = new Dictionnaire(Lang.FR);
		Dictionnaire d2 = new Dictionnaire(Lang.EN);
		Boolean [] b = {new Boolean(false),new Boolean(false)};
		String tab [] = {"abaisser","zimbabwe"};
		d.searchMot(tab,b);
		b.toString();
	}

}
