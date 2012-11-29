package Principale;
/**
 * Implémente le sac de jetons
 * @author guillaume
 * @version 1.0
 */
public class Sac {

	private Jeton []Sac; 

	public Sac() {
		Sac = new Jeton [26];
		// TODO Auto-generated constructor stub
		for (int i=0;i<26;i++)
			Sac[i]=new Jeton((char)(97+i),0,0);
	}

	public void add(Sac other)
	{
		for (int i=0;i<26;i++)
			Sac[i].add(other.Sac[i]);
	}
	
	public void add(char c,int occurrence)
	{
		Sac[((int)c)-97].add(occurrence);
	}
}