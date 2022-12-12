package ar.com.gm.peliculas.negocio;

public interface ICatalogoPeliculas {
	String NOMBRE_RECURSO = "peliculas";
	
	void agregarPelicula(String nombrePelicula);
	
	void listarPeliculas();
	
	void buscarPelicula(String buscar);
	
	void iniciarArchivo();
	
}
