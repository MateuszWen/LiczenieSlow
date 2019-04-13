package liczenieSlow2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PoliczSlowa {
	
	public static int iloscSlow = 0; // licznik ogólnej ilości słów

//---------------------------------------------------------------------wybieranie pliku do sprawdzenia	- POCZĄTEK
	public static Map<String, Integer> FunkcjaLiczacaSlowa() {

		JFileChooser chooser = new JFileChooser(".");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("pliki Textowe", "txt"));
		int coSieStalo = chooser.showOpenDialog(null);

		Map<String, Integer> mapa = new LinkedHashMap<>();

//---------------------------------------------------------------------wybieranie pliku do sprawdzenia -- KONIEC	
		
//---------------------------------------------------------------------wczytywanie pliku - POCZĄTEK
		BufferedReader odczyt = null;
		try {
			odczyt = new BufferedReader(
					(new InputStreamReader(new FileInputStream(chooser.getSelectedFile()), "UTF8")));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//---------------------------------------------------------------- wczytywanie pliku  -- KONIEC	
		
//----------------------------------------------------------------operacja zczytywania wyrazów - POCZĄTEK
		
		if (coSieStalo == JFileChooser.APPROVE_OPTION) {

			Scanner scn = new Scanner(odczyt);
			//sc.useDelimiter("\\\\s+|,+|»+|«+|\\\\*+|;+|:+|!+|'+|\\\\.+|\\\\?+|\\\\)+|\\\\(+|\\\\\\\\|\\\\\\\"+|\\\\„+|\\\\”+"); // <-- 
			//                   pierwotnie korzystałem z tego zapisu, ale znaleziony został lepszy 
			scn.useDelimiter("[^A-Za-z0-9żźćńółęąśŻŹĆĄŚĘŁÓŃ]+"); //  <-- alternatywa do tego co wyżej 	

			
			String linia;
			while (scn.hasNext()) {
				 linia = scn.next().toLowerCase();
				//System.out.println(linia + "\n"); // <-- linia pomocnicza
				if (mapa.containsKey(linia)) {
					int ileBylo = mapa.get(linia);
					mapa.put(linia, ileBylo + 1);
				} else {
					mapa.put(linia, 1);
				}
			}			
			
			scn.close();
		
			FunkcjeDoPoliczSlowa.ObliczIloscSlow(odczyt);

			try {
				odczyt.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			JOptionPane.showConfirmDialog(null, "Nie wybrano pliku");
		}
		
        return mapa;

	}
	//----------------------------------------------------------------operacja zczytywania wyrazów  --  KONIEC 
	
	
	

	public static void WypiszMape(Map<String, Integer> mapa) {

		int iteratorStron = 0;
		for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
			System.out.printf(++iteratorStron + ".  %15s ----->  %4d \n", entry.getKey(), entry.getValue());			
		}

		System.out.printf("\nZawiera %8d różnych wyrazów", mapa.size());
		System.out.printf("\nŁącznie zawiera %8d OSOBNYCH słów", iloscSlow);

	}
	
	
	
	

	public static void ZapiszWyniki(Map<String, Integer> mapa) {

		try {
			String nazwaPlikuZapis = JOptionPane.showInputDialog("Podaj jak ma się nazywać zapisywany plik ");
			nazwaPlikuZapis = nazwaPlikuZapis + ".txt";

			File plikZapis = new File(nazwaPlikuZapis);
			plikZapis.createNewFile();

			Scanner sc = new Scanner(nazwaPlikuZapis);
			PrintWriter out = new PrintWriter(plikZapis, "UTF-8");

			int iteratorStron = 0;
			
				for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
					out.printf(++iteratorStron + ".  %15s ----->  %4d \n", entry.getKey(), entry.getValue());
	
				}
				
			out.printf("\nŁącznie zawiera: " + mapa.size() + " różnych wyrazów\n");
			out.printf("\nŁącznie zawiera " + iloscSlow + " osobnych słów\n");

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			out.println("\nData i czas zapisu: " + dtf.format(now));

			sc.close();
			out.close();

		} catch (Exception e) {
			System.out.println("Coś poszło nie tak. Kończe pracę funkcji \"ZapiszWyniki\"");
		}

	}
	
	
}

