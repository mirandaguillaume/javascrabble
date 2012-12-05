package principale;

import java.util.Comparator;
import java.util.HashMap;

public class CompareQuantite implements Comparator<Character> {

	private HashMap<Character,Integer> list;
	
	public CompareQuantite(HashMap<Character,Integer> list) {
		this.list=list;
	}
	
	@Override
	public int compare(Character o1, Character o2) {
		// TODO Auto-generated method stub
		Integer compare1 = list.get(o1);
		Integer compare2 = list.get(o2);
		return compare2-compare1;
	}

}
