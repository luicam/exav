import java.util.*;
import java.io.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class streamA{
	public static void main(String[] args) {
		try {
      		FileReader fr = new FileReader("ciudadesTemp.txt");
      		BufferedReader br = new BufferedReader(fr);
      		String linea;
          List<String> lista = new ArrayList<String>();
      		while((linea = br.readLine()) != null){
            lista.add(linea);
      		}
          int ii = lista.stream()
              .map(p -> p.split(":"))
              .filter(p -> p[0].matches(".*Sevilla.*"))
              .mapToInt(p -> Integer.valueOf(p[1]))
              .sum();
         System.out.println(ii);

      		fr.close();
    	}
    	catch(Exception e) {
      		System.out.println("Excepcion leyendo fichero : " + e);
    	}
	}
}