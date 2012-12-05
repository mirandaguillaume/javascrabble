package dico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
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

	private final int tabFrByScore[][]={{1,10},{2,3},{3,3},{4,3},{8,2},{10,5}};
	private final int tabEnByScore[][]={{1,10},{2,2},{3,4},{4,5},{5,1},{8,2},{10,2}};
	private final int tabFrByQuantite[][]={{15,1},{9,1},{8,1},{6,6},{5,1},{3,2},{2,7},{1,7}};
	private final int tabEnByQuantite[][]={{12,1},{9,2},{8,1},{6,3},{4,4},{3,1},{2,9},{1,5}};

	private static int [][] tabByQuantite;
	private static int [][] tabByScore;
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

	public int [][] getTabByScore() {
		return tabByScore;
	}
	
	public int [][] getTabByQuantite() {
		return tabByQuantite;
	}
	public Tree getListe() {
		return liste;
	}

	/** Fonction qui initialise le dictionnaire */
	private void init() {
		// TODO Auto-generated method stub
		String ToOpen = null;
		Scanner Opened =null;
		switch (actuelle)
		{
		case FR : ToOpen=dicoFR;
		tabByScore=tabFrByScore;
		tabByQuantite=tabFrByQuantite;
		break;
		case EN : ToOpen=dicoEN;
		tabByScore=tabEnByScore;
		tabByQuantite=tabEnByQuantite;
		break;
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
			Pattern p = Pattern.compile("[^a-z]");
			Matcher m = p.matcher(ligne.toLowerCase());
			if (m.find())
				do 
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
					m=p.matcher(ligne.toLowerCase());
				} while (m.find());
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

	public boolean searchMot(String s) {
		return liste.searchMot(s);
	}

	public HashMap<Character,Integer> calcVal ()
	{
		return calcValRecurs(liste.getRoot(),-1);
	}

	private HashMap<Character, Integer> calcValRecurs(Node n, int ind) {
		// TODO Auto-generated method stub
		HashMap<Character,Integer> finalList = new HashMap<Character,Integer>();
		for (int i=0;i<n.getNbChildren();i++)
		{
			HashMap<Character,Integer> tmp=calcValRecurs(n.getChild(i),ind+1);
			if (finalList.size()!=0)
				finalList=fusion(finalList,tmp);
			else 
				finalList=tmp;
			finalList.toString();
		}
		int newQuantite = n.getNbChildren();
		if (n.getMot()!="\0") {
			try {
				char c = n.getMot().charAt(ind);
				try {
					newQuantite += finalList.get(c);
				} catch (NullPointerException e) {
					newQuantite = n.getNbChildren();
				}
				if (newQuantite==0)
					newQuantite++;
				finalList.put(c, newQuantite);
			} catch (StringIndexOutOfBoundsException  e) {}
		}
		return finalList;
	}

	public HashMap<Character,Integer> fusion(HashMap<Character,Integer> firstList,HashMap<Character,Integer> secondList) throws NullPointerException {
		Set<Character> setFirst= firstList.keySet();
		Set<Character> setSecond= secondList.keySet();
		HashSet<Character> intersection = new HashSet<Character>();
		Iterator<Character> itFirst = setFirst.iterator();
		Iterator<Character> itSecond = setSecond.iterator();
		HashMap<Character,Integer> fusionne = new HashMap<Character,Integer>();
		Character c = null;
		if (itFirst.hasNext()) {
			c=itFirst.next();
			while (c!=null) {
				if (setSecond.contains(c)) 
					intersection.add(c);
				if (itFirst.hasNext())
					c=itFirst.next();
				else break;
			}
		}
		if (itSecond.hasNext()) {
			c=itSecond.next();		
			while (c!=null) {
				if (setFirst.contains(c)) 
					intersection.add(c);
				if (itSecond.hasNext())
					c=itSecond.next();
				else break;
			}
		}
		Iterator<Character> itIntersection = intersection.iterator();
		if (itIntersection.hasNext()) {
			c=itIntersection.next();
			while (c!=null) {
				fusionne.put(c,firstList.get(c)+secondList.get(c));
				if (itIntersection.hasNext())
					c=itIntersection.next();
				else break;
			}
		}
		itFirst = setFirst.iterator();
		itSecond = setSecond.iterator();
		if (itFirst.hasNext())
			c=itFirst.next();
		while (c!=null) {
			if (!fusionne.containsKey(c))
				fusionne.put(c,firstList.get(c));
			if (itFirst.hasNext())
				c=itFirst.next();
			else break;
		}
		if (itSecond.hasNext())
			c=itSecond.next();		
		while (c!=null) {
			if (!fusionne.containsKey(c))
				fusionne.put(c,secondList.get(c));
			if (itSecond.hasNext())
				c=itSecond.next();
			else break;
		}
		return fusionne;
	}
}