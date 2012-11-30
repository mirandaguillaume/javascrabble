package Principale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;



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

	private Pattern p;
	private Matcher m;

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
		int deb;
		while (opened.hasNextLine())
		{
			ligne=opened.nextLine();
			p = Pattern.compile("[^a-z]");
			m=p.matcher(ligne.toLowerCase());
			if (m.find())
			{
				String modifiee = null;
				char exception = m.group(0).charAt(0);
				int exception_int = (int) exception;
				deb = m.start(0);
				modifiee=ligne.substring(0,deb);
				modifiee+=normalise(exception_int);
				if (deb+1!=ligne.length())
					modifiee+=ligne.substring(deb+1);
				ligne=modifiee;
			}
			if (ligne != null) {
				liste.addMot(ligne.toLowerCase());
			}
		}
	}

	private String normalise(int exception_int) {
		// TODO Auto-generated method stub
		String mot = Character.toString((char)exception_int);
		Pattern p=Pattern.compile("[à-ÿ]");
		Matcher m=p.matcher(mot);
		String s="";
		if (m.find())
		{
			if ((exception_int>=(int)'à') && (exception_int<=(int)'å'))
				s="a";
			else if ((exception_int>=(int)'è') && (exception_int<=(int)'ë'))
				s="e";
			else if (exception_int==(int)'ç')
				s="c";
			else if (exception_int==(int)'æ')
				s="ae";
			else if (exception_int==156)
				s="oe";
			else if ((exception_int<=(int)'ì') && (exception_int<=(int)'ï'))
				s="i";
			else if ((exception_int<=(int)'ò') && (exception_int<=(int)'ö'))
				s="o";
			else if (exception_int==(int)'ñ')
				s="n";
			else if ((exception_int<=(int)'ù') && (exception_int<=(int)'ü'))
				s="u";
			else if ((exception_int==(int)'ý') && (exception_int==(int)'ÿ'))
				s="y";
		}
		return s;

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

	public void calcVal (Sac s) throws InterruptedException
	{
		CalcVal t = new CalcVal(liste.getRoot(),s,0);
		t.run();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionnaire d = new Dictionnaire(Lang.FR);
		Boolean [] b = {new Boolean(false),new Boolean(false)};
		String tab [] = {"assez","zimbabwe"};
		d.searchMot(tab,b);
		//d2.searchMot(tab, b2);
		Sac s=new Sac();
		try {
			d.calcVal(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.toString();
	}

}
