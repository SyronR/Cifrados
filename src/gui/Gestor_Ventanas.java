package gui;

import kernel.Encriptador_Main;

public class Gestor_Ventanas {
/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */
	
	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////
	
	private Encriptador_Main main;
	private Ventana_Principal ventanaPrincipal;
	private Ventana_Registro ventanaRegistro;
	private Ventana_Acerca ventanaAcerca;
	private Gestor_Archivos gestorArchivos;
	
	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////
	
	public Gestor_Ventanas(Encriptador_Main main) {
		this.main = main;
		gestorArchivos = new Gestor_Archivos(this);
		ventanaPrincipal = new Ventana_Principal(this);
		ventanaRegistro = new Ventana_Registro(this);
		ventanaAcerca = new Ventana_Acerca(this);
		
		ventanaPrincipal.setVisible(true);
	}
	
	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////
	
	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////
	
	public Encriptador_Main getMain() {
		return main;
	}

	public Ventana_Principal getVentanaPrincipal() {
		return ventanaPrincipal;
	}
	
	public Ventana_Registro getVentanaRegistro() {
		return ventanaRegistro;
	}
	
	public Ventana_Acerca getVentanaAcerca() {
		return ventanaAcerca;
	}
	
	public Gestor_Archivos getGestorArchivos() {
		return gestorArchivos;
	}
	
}
