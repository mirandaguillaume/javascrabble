package Principale;

import Dico.Dictionnaire.Lang;
import java.util.Map;

/**
 * Implémente le sac de jetons
 * @author guillaume
 * @version 1.0
 */
public class Sac {

	private Jeton []Sac; 
	private Map<Jeton,Integer> SacMap;

	public Sac() {
		// TODO Auto-generated constructor stub
		for (int i=0;i<26;i++) {
			Sac[i]=new Jeton((char)(97+i),0);
			SacMap.put(new Jeton((char)(97+i),0), 0);
		}
	}

	public void add(int index,int occurrences)
	{
	int [] Sac = new int [nbLettres];
	
	}

	public void add(char c,int occurrence) throws NullPointerException
	{
		Sac[((int)c)-97].add(occurrence);
	}

	public void setQuantiteScore(){
		Jeton [] tab = new Jeton [Sac.length];
		for (int i=0;i<Sac.length;i++)
			tab[i]=Sac[i];
		trieTab(tab);
		
	}

	public void trieTab(Jeton t[])
	{
		for (int i=0 ;i<=(t.length-2);i++)
			for (int j=(t.length-1);i < j;j--)
				if (t[j].getQuantite() < t[j-1].getQuantite())
				{
					Jeton x=t[j-1];
					t[j-1]=t[j];
					t[j]=x;
				}
	}
}