package de.jwiegratz.unique_numbers;
import java.util.ArrayList;
import java.util.HashSet;

public class QueenSingularity {

	private int base;
	private ArrayList<String> numbers;
	
	public QueenSingularity(int base){
		this.base = base;
		this.numbers = new ArrayList<String>();
	}

	public String[] calculate() {
		this.numbers.clear();
		this.base_count(base, 0, new String());
		
		String[] ret = new String[this.numbers.size()];
		ret = this.numbers.toArray(ret);
		
		return ret;
	}

	private void base_count(int base, int depth, String progress) {
		
		//Speichere fertige Zahl, wenn in der untersten Baum-Ebene
		if (depth == base) {
			if(this.filterCombo(progress) == true){
				this.numbers.add(progress);
			}
		} else if (depth < base) {
			for (int i = 1; i <= base; i++) {
				//Rekursiver Aufruf, Anzahl so groß wie die Basis (Basis 3 => 3 rek. Aufrufe)
				base_count(base, depth + 1, progress.concat(new Integer(i).toString() + " "));
			}
		}
	}
	
	private boolean filterCombo(String progress) {

		HashSet<String> unicache = new HashSet<String>();
		
		//Zerlege Zahl in "Ziffern"
		String[] tnt = progress.split(" ");
		
		for(int i=0; i<tnt.length; i++){
			String t = tnt[i];
			
			//Prüfe, ob Ziffer schon vorgekommen ist
			if(unicache.contains(t)){
				//Ziffern haben sich wiederholt, Ergebnis negativ
				return false;
			}else{
				for(int j=0; j<tnt.length; j++){
					if(j != i){
						int s2 = Integer.parseInt(tnt[i]);
						int s1 = Integer.parseInt(tnt[j]);
						
						int diff = j - i;
						if(diff < 0) diff = diff / (-1);
						
						if(s1 == s2 + diff || s1 == s2 - diff){
							return false;
						}
					}
				}
			}
			//Füge Ziffer hinzu, wenn diese noch nicht im Set ist
			unicache.add(t);
		}
		
		return true;
	}

	public static void main(String[] args){

		int base = 5;
		if (args.length > 0) {
		    try {
		        base = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + " must be an integer");
		        System.exit(1);
		    }
		}

		QueenSingularity sing = new QueenSingularity(base);
		String[] result = sing.calculate();
		
		for(String num : result) System.out.println(num);
		System.out.println("Finished: " + result.length + " Results");
	}
	
}
