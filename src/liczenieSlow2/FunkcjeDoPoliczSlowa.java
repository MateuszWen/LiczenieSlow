package liczenieSlow2;

import java.io.BufferedReader;
import java.util.Scanner;

public class FunkcjeDoPoliczSlowa {
	
	
	public static void ObliczIloscSlow(BufferedReader odczyt) {
		
		try {
			Scanner sc = new Scanner(odczyt);
			
			//sc.useDelimiter("\\W+"); // <-- 
			//                   pierwotnie korzystałem z tego zapisu, ale znaleziony został lepszy
			sc.useDelimiter("\\s+|^•"); //  <-- alternatywa na to co wyżej 
			
			while(sc.hasNext()){
				String line = sc.next();
				//System.out.println( ++slowka + "hello!!!");
				PoliczSlowa.iloscSlow++;
			}
			
			sc.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Błąd przy odczyt2");
		}
				
	}

}
