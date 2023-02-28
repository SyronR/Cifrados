package addons;

import kernel.Encriptador_Main;

public class Acerca {
/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */
	
	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////
		
	private String desarrollador;
	private String version;
	private String correo;
	private String cambios;
	
	private Encriptador_Main main;
	
	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////
	
	public Acerca(Encriptador_Main main) {
		desarrollador = "Alberto G�lvez / Syron Power";
		version = "2.1 (Flores), recompilado en Java 8";
		correo = "galvezssr@gmail.com / syronpower@gmail.com";
		cambios = "Incorporaci�n de c�digos de cifrado cargables externamente";
		
		this.main = main;
	}
	
	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////
	
	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////
	
	public String getDesarrollador() {
		return desarrollador;
	}

	public String getVersion() {
		return version;
	}

	public String getCorreo() {
		return correo;
	}
	
	public String getCambios() {
		return cambios;
	}
	
	public Encriptador_Main getMain() {
		return main;
	}
}
