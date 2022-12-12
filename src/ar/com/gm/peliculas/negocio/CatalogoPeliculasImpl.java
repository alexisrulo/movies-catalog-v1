package ar.com.gm.peliculas.negocio;

import java.util.List;

import ar.com.gm.peliculas.datos.*;
import ar.com.gm.peliculas.domain.Pelicula;
import ar.com.gm.peliculas.excepciones.AccesoDatosEx;
import ar.com.gm.peliculas.excepciones.EscrituraDatosEx;
import ar.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas{
	
	private final AccesoDatosImpl datos;
	
	public CatalogoPeliculasImpl() {
		this.datos = new AccesoDatosImpl();
	};
	
	
	@Override
	public void agregarPelicula(String nombrePelicula) {
		Pelicula pelicula = new Pelicula(nombrePelicula);
		try {
			boolean anexar = false;
			anexar = datos.existe(NOMBRE_RECURSO);
			datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
			System.out.println("Se ha escrito correctamente al archivo");
		} catch (EscrituraDatosEx e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listarPeliculas() {
		List<Pelicula> pelis = null;
		try {
			pelis = datos.listar(NOMBRE_RECURSO);
			pelis.forEach(peli -> {
				System.out.println("Pelicula: " + peli.getNombre());
			});
		} catch (AccesoDatosEx e) {
			e.printStackTrace(System.out);
		}

	}

	@Override
	public void buscarPelicula(String buscar) {
		try {
			System.out.println(datos.buscar(NOMBRE_RECURSO, buscar));
		} catch (AccesoDatosEx e) {
			e.printStackTrace(System.out);
		}
	}

	@Override
	public void iniciarArchivo() {
		try {
			if(this.datos.existe(NOMBRE_RECURSO)) {
				datos.borrar(NOMBRE_RECURSO);
				datos.crear(NOMBRE_RECURSO);
			} else {
				datos.crear(NOMBRE_RECURSO);
			}
			System.out.println("Archivo creado correctamente");		
		} catch (AccesoDatosEx e) {
			e.printStackTrace(System.out);
		}
	}

	
	
}
