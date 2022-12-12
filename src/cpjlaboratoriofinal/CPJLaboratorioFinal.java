package cpjlaboratoriofinal;

import java.util.Scanner;
import ar.com.gm.peliculas.negocio.*;

import ar.com.gm.peliculas.domain.Pelicula;

public class CPJLaboratorioFinal {
	public static void main(String[] args) {
		int opcion = -1;
		String pelicula;
		Scanner consola = new Scanner(System.in);
		CatalogoPeliculasImpl catalogoPeliculas = new CatalogoPeliculasImpl();
		while(opcion != 0) {
			System.out.println("Elige una opcion:\n"
					+ "  1 - Inciciar catalogo de peliculas\n"
					+ "  2 - Agregar una pelicula\n"
					+ "  3 - Listar peliculas\n"
					+ "  4 - Buscar una pelicula\n"
					+ "  0 - Salir");
			opcion = Integer.parseInt(consola.nextLine());
		
			switch(opcion) {
				case 1:
					catalogoPeliculas.iniciarArchivo();			
					break;
				case 2:
					System.out.println("Introduce el nombre de una pelicula a agregar");
					pelicula = consola.nextLine();
					catalogoPeliculas.agregarPelicula(pelicula);
					break;
				case 3:
					catalogoPeliculas.listarPeliculas();
					break;
					
				case 4:
					System.out.println("Introduce el nombre de la pelicula que deseas buscar");
					pelicula = consola.nextLine();
					catalogoPeliculas.buscarPelicula(pelicula);
					break;
					
				case 0: 
					System.out.println("Saliendo");
					break;
					
				default:
					System.out.println("Ingresa una opcion correcta");
					break;
			}
			System.out.println("--------------------------");
		}		
	
	}

}
