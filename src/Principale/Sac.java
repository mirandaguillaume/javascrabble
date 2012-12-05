package Principale;

import Dico.Dictionnaire.Lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Implémente le sac de jetons
 * @author guillaume
 * @version 1.0
 */
public class Sac {

	private HashMap<Jeton,Integer> SacMap;

	public final int nbLettres = 26;
	
	public Sac() {
		// TODO Auto-generated constructor stub
		SacMap=new HashMap<Jeton,Integer>(26);
	}

	public void setQuantiteScore(HashMap<Character,Integer> list,int [][] tabByScore,int [][] tabByQuantite){
		Set<Character> listCarac =	list.keySet();
		ArrayList<Character> listJetonTrie = new ArrayList<Character>(listCarac);
		CompareQuantite c = new CompareQuantite(list);
		Collections.sort(listJetonTrie, c);
		Character [] tmpTab = new Character [nbLettres];
		tmpTab=listJetonTrie.toArray(tmpTab);
		int cptScore = 0, cptQuantite = 0,indScore=0,indQuantite=0;
		for (int i=0;i<listJetonTrie.size();i++) {
			if (cptScore>=tabByScore[indScore][1]) {
				cptScore=0;
				indScore++;
			}
			if (cptQuantite>=tabByQuantite[indQuantite][1]) {
				cptQuantite=0;
				indQuantite++;
			}
			cptScore++;
			cptQuantite++;
			SacMap.put(new Jeton(tmpTab[i], tabByScore[indScore][0]),tabByQuantite[indQuantite][0]);
		}
			listJetonTrie.toString();
	}
}