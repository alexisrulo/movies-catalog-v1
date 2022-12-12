package ar.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import ar.com.gm.peliculas.domain.Pelicula;
import ar.com.gm.peliculas.excepciones.*;

public class AccesoDatosImpl implements IAccesoDatos{

	@Override
	public boolean existe(String nombreRecurso){
		File archivo = new File(nombreRecurso + ".txt");		
		return archivo.exists();
	}

	@Override
	public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
		File archivo = new File(nombreRecurso + ".txt");
		List<Pelicula> peliculas = new ArrayList<>();
	    BufferedReader entrada;
		try {
			entrada = new BufferedReader(new FileReader(archivo));
			String lectura = entrada.readLine();
			while (lectura != null) {
				peliculas.add(new Pelicula(lectura));
				lectura = entrada.readLine();
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Error al listar peliculas: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Error al listar peliculas: " + e.getMessage());
		}
	
		return peliculas;
		
	}
	

	@Override
	public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx{
		File archivo = new File(nombreRecurso + ".txt");
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
			salida.println(pelicula.getNombre());
			salida.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new EscrituraDatosEx("Error al escribir pelicula: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new EscrituraDatosEx("Error al escribir pelicula: " + e.getMessage());
		}
	}

	@SuppressWarnings("resource")
	@Override
	public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx{
		File archivo = new File(nombreRecurso + ".txt");
		BufferedReader entrada;
		try {
			entrada = new BufferedReader(new FileReader(archivo));
			var lectura = entrada.readLine();
			int indice = 1;
			while (lectura != null) {
				if(buscar != null && lectura.equalsIgnoreCase(buscar)) {
					entrada.close();
					return ("Pelicula " + lectura + " encontrada en el catalogo con el indice: " + indice);
				}
				lectura = entrada.readLine();
				indice++;
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Error al leer peliculas: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("Error al leer peliculas: " + e.getMessage());
		}
		return ("No se encontro la pelicula " + buscar + " en el catalogo");
	} 

	@Override
	public void crear(String nombreRecurso) throws AccesoDatosEx{
		File archivo = new File(nombreRecurso + ".txt");
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo));
			salida.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new AccesoDatosEx("Error al crear el archivo: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccesoDatosEx("Error al crear el archivo: " + e.getMessage());
		}
	}	

	@Override
	public void borrar(String nombreRecurso){
		File archivo = new File(nombreRecurso + ".txt");
		if(archivo.exists()) {
			archivo.delete();
		}
	}

}
