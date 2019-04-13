//	Autor: Mateusz W
//
//	Opis programu:
// 	Program zajmuje się zliczaniem ilości danych słów w podanym przez użytkownika tekście. 
// 	Wyniki pracy programu, użytkownik może zapisac w formie pliku .txt 
// 	Plik txt będzie zawierał słowo, ilość jego wystąpień, oraz ogólną ilość słów i datę utworzenia pliku. 
//
//	UWAGA!
//	Podany przez użytkownika tekst również musi być w formacie .txt

package liczenieSlow2;

import java.util.Map;

public class StartClass {
	
	public static void main(String[] args) {
		
				
		Map<String, Integer> mapa = PoliczSlowa.FunkcjaLiczacaSlowa();
				
		PoliczSlowa.WypiszMape(mapa);
		
		PoliczSlowa.ZapiszWyniki(mapa);
				
		System.out.println("\nWykonano program");
	}

}
