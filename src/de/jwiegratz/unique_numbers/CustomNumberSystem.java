package de.jwiegratz.unique_numbers;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CustomNumberSystem {

	private ArrayList<String> numbers;
	private int base;
	
	public static final String lim = " ";

	Timer timer = new Timer();

	public CustomNumberSystem(int base) {
		timer.scheduleAtFixedRate(new MemoryInfo(), 0, 1000);
		System.out.println("Max mem: " + Runtime.getRuntime().maxMemory()/1000 + "K");
		
		this.numbers = new ArrayList<String>();
		this.base = base;
	}

	public static long faculty(int n) {
		long fac = 1;
		for (int i = 1; i <= n; i++) {
			fac = fac * i;
		}
		return fac;
	}

	public String[] getAllPossibilities() {
		//Leere Zahlen-Array
		this.numbers.clear();
		
		//Starte rekursive Zahlensystem-Funktion
		this.base_count(base, 0, new String());
		
		//Gebe ArrayList als Array zurück
		String[] result = this.numbers.toArray(new String[this.numbers.size()]);
		return result;
	}

	private void base_count(int base, int depth, String progress) {
		
		//Speichere fertige Zahl, wenn in der untersten Baum-Ebene
		if (depth == base) {
			this.numbers.add(progress);
		} else if (depth <= base) {
			for (int i = 1; i <= base; i++) {
				//Rekursiver Aufruf, Anzahl so groß wie die Basis (Basis 3 => 3 rek. Aufrufe)
				base_count(base, depth + 1, progress.concat(new Integer(i).toString() + CustomNumberSystem.lim));
			}
		}
	}
	
	class MemoryInfo extends TimerTask {
		Runtime rt = Runtime.getRuntime();
	    public void run() {
	    	if(numbers != null){
	    		System.out.println("Used: " + ((rt.maxMemory() - rt.freeMemory())/ 1000)
	    				+ "K  Free: " + (rt.freeMemory() / 1000) + "K" + "  " + numbers.size() + " Results");
	    	}
	    }
	 }

}
