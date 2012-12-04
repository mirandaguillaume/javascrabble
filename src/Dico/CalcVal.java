package Dico;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Dico.Dictionnaire.Lang;

/**
 * Implémente le calcul des occurences en mode threadé
 * @author guillaume
 * @version 1.0
 */
public class CalcVal {

	private Node n;
	private int ind;
	/**
	 * Constructeur de la classe
	 * @param n Le noeud d'origine
	 * @param s Le sac dans lequel calculer
	 * @param ind L'indice du caractère
	 */
	public CalcVal(Node n,int ind) {
		// TODO Auto-generated constructor stub
		this.n=n;
		this.ind=ind;
	}

	public HashMap<Character, Integer> run() {
		// TODO Auto-generated method stub
		HashMap<Character,Integer> finalList = new HashMap<Character,Integer>();
		for (int i=0;i<n.getNbChildren();i++)
		{
			CalcVal c =new CalcVal(n.getChild(i),ind+1);
			HashMap<Character,Integer> tmp=c.run();
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
		HashSet<Character> notIntersection = new HashSet<Character>();
		HashMap<Character,Integer> fusionne = new HashMap<Character,Integer>();
		intersection.retainAll(setFirst);
		if (intersection.retainAll(setSecond)) {
			setFirst.removeAll(intersection);
			notIntersection.addAll(setFirst);
			setSecond.removeAll(intersection);
			notIntersection.addAll(setSecond);
			Character [] tmpTab = (Character[]) notIntersection.toArray();
			for (int i=0;i<tmpTab.length;i++) 
				fusionne.put(tmpTab[i],firstList.get(tmpTab[i]));
			tmpTab = (Character[]) intersection.toArray();
			for (int i=0;i<tmpTab.length;i++)
				fusionne.put(tmpTab[i],firstList.get(tmpTab[i])+secondList.get(tmpTab[i]));

		}
		return fusionne;
	}

	public static void main (String [] args) {
		Dictionnaire d = new Dictionnaire(Lang.FR);
		HashMap<Character,Integer> test = new HashMap<Character,Integer>();
		try {
			test=d.calcVal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.toString();
	}
}