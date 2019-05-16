import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class CiudadesTemp {

	private String ciudad;
	private int temperatura;
	private String mes; 
	

	public static void main (String[] args) {
		List<CiudadesTemp> ct = new ArrayList<CiudadesTemp>();
		try {
      		BufferedReader br = new BufferedReader(FileReader fr = new FileReader("ciudadesTemp.txt"));
      		String l;
        	//List<String> lista = new ArrayList<String>();
      		while((l = br.readLine()) != null){
      			String[] campos = l.split(":");
            	ct.add(new CiudadesTemp(campos[0],Integer.valueOf(campos[1]),campos[]));

      		}
      	}
      	//.map(p -> p.getTemperatura())//es equivalente
      	//int temperatura = ct.stream().filter(p -> p.getCiudad().equals("Sevilla")).map(p -> p.getTemperatura)
      	int temperatura = ct.stream().filter(p -> p.getCiudad().equals("Sevilla")).map(ciudadesTemp::getTemperatura).reduce(0,(a,b) -> a+b);
      	System.out.println(temperatura);
        

        //apartado d
        List<String> lista = ct.stream().map(CiudadesTemp::getCiudad).distinct().sorted().skip(1).collect(Collectors.tolist());

        for(String s : lista){
        	System.out.println(s);
        }

        //presentar la info de otra manera
        String l1 = ct.stream().map(CiudadesTemp::getCiudad).distinct().sorted().skip(1).reduce("",(a,b) -> a+", "+b);
        System.out.println(l1);


        //apartadoc

        ct.stream().filter(p -> p.getMes().equals("febrero")).map(CiudadesTemp::getTemperatura).mapToDouble(p -> p).average().ifPresent(System.out::println);

        //ver la documentacion de stream 
        //distinct y sorted devuelven un stram  operaciones intermedias.
        //al final me intereso por el tipo de retorno quie espera.
    	catch(Exception e) {
      		System.out.println("Excepcion leyendo fichero : " + e);
    	}


	}

	public String getCiudad(){
		return this.ciudad;
	}
	public String getMes(){
		return this.mes;
	}
	public String getTemperatura(){
		return this.temperatura;
	}
	public void setCiudad(String ciudad){
		this.ciudad=ciudad;
	}
	public void setMes(String mes){
		this.mes=mes;
	}
	public void setTemperatura(String temperatura){
		this.temperatura=temperatura;
	}
