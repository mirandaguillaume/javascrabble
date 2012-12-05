package Principale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import Dico.Dictionnaire;

/**
 * Implémente le sac de jetons
 * @author guillaume
 * @version 1.0
 */
public class Sac {

	/**
	 * Contient les jetons avec leur quantite associees
	 */
	private HashMap<Jeton,Integer> SacMap;

	/**
	 * Nb de lettres dans l'alphabet sans les caracteres speciaux
	 */
	public final int nbLettres = 26;

	/**
	 * Constructeur de la classe
	 */
	public Sac(Dictionnaire d) {
		// TODO Auto-generated constructor stub
		SacMap=new HashMap<Jeton,Integer>(26);
		setQuantiteScore(d.calcVal(),d.getTabByScore(),d.getTabByQuantite());
	}

	/**
	 * 
	 * @param list La liste des caracteres avec leur quantite dans le dictionnaire
	 * @param tabByScore le tableau contenant les scores
	 * @param tabByQuantite le tableau contenant les quantites
	 */
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
	
	public void remettre(Jeton [] defausse) {
		for (int i=0;i<defausse.length;i++) {
			Integer quantite = SacMap.get(defausse[i]);
			if (quantite!=null)
				SacMap.put(defausse[i], quantite+1);
		}
	}
	
	/**
	 * 
	 * @param i nombre de jeton que l'on souhaite
	 * @return un tableau de taille i ayant les 
	 *  jetons a jouer. 
	 */
	public Jeton [] piocher(int i){
		ArrayList<Jeton> main;
		if(i<=0) throw new GameException("appel de Plateau.piocher(i:int) avec i negatif");
		main = new ArrayList<Jeton> (i);
		Set<Jeton> tmpSetJeton = SacMap.keySet();
		ArrayList<Jeton> tmpListJeton = new ArrayList<Jeton>(tmpSetJeton);
		Jeton [] listJeton = new Jeton [tmpListJeton.size()];
		listJeton=tmpListJeton.toArray(listJeton);
		Random rand = new Random();
		while(i>0){ // d�cr�ment� 
			int ind = rand.nextInt(nbLettres);
			Jeton j=listJeton[ind];
			Integer quantite = SacMap.get(j);	
			quantite.toString();
			if ((quantite>0)&&(quantite!=null)) {
					main.add(j);
				// le d�cr�ment se fait ici !
				SacMap.put(j, quantite-1);
				i--;
			}
		}
		Jeton [] tabMain = new Jeton[main.size()];
		tabMain = main.toArray(tabMain);
		return tabMain;
	}
}