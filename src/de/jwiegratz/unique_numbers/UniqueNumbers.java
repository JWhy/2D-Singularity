package de.jwiegratz.unique_numbers;
import java.util.HashSet;

public class UniqueNumbers {
	
	public String[] getUniqueNumbers(int base){
		//Ermittle alle möglichen Nummern des Zahlensystems
		CustomNumberSystem cns = new CustomNumberSystem(base);
		String[] numbers = cns.getAllPossibilities();
		
		//Lege Array für einmalige Nummern an.
		String[] unumbers = new String[(int) CustomNumberSystem.faculty(base)];
		int pos = 0;
		
		for(String number : numbers){
			if(this.checkUnique(number)){
				//Wenn aktuelle Zahl "querziffer-rein", speichere in Array
				unumbers[pos] = number;
				pos++;
			}
		}
		return unumbers;
	}
	
	private boolean checkUnique(String number) {
		//HashSet erzeugen. Funktioniert wie ein dynamisches Array
		//Werte dürfen nur einmalig vorkommen
		HashSet<String> unicache = new HashSet<String>();
		
		//Zerlege Zahl in "Ziffern"
		String[] tnt = number.split(CustomNumberSystem.lim);
		
		for(String t : tnt){
			//Prüfe, ob Ziffer schon vorgekommen ist
			if(unicache.contains(t)){
				//Ziffern haben sich wiederholt, Ergebnis negativ
				return false;
			}
			//Füge Ziffer hinzu, wenn diese noch nicht im Set ist
			unicache.add(t);
		}
		
		//Ziffern haben sich nicht wiederholt, Ergebnis positiv
		return true;
	}

	public static void main(String[] args){
		int base = 4;
		
		if (args.length > 0) {
		    try {
		        base = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + " must be an integer");
		        System.exit(1);
		    }
		}
		
		UniqueNumbers un = new UniqueNumbers();
		for(String num : un.getUniqueNumbers(base)){
			System.out.println(num);
		}
	}
}
