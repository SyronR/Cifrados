package kernel;

import java.io.File;

import addons.Acerca;
import gui.Gestor_Ventanas;

public class Encriptador_Main {
/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */
	
	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////
	
	private Gestor_Ventanas gestor;
	private Encriptador encriptador;
	private Acerca acerca;
	
	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////
	
	public Encriptador_Main() {
		acerca = new Acerca(this);
		encriptador = new Encriptador(this);
		gestor = new Gestor_Ventanas(this);
	}
	
	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////
	
	public static void main(String[] args) {
		new Encriptador_Main();
	}
	
	public boolean cifrarArchivoNormal(File entrada, File salida) {
		return encriptador.cifrarArchivoNormal(entrada, salida);
	}
	
	public boolean descifrarArchivoNormal(File entrada, File salida) {
		return encriptador.descifrarArchivoNormal(entrada, salida);
	}
	
	public boolean cifrarArchivoEnBinario(File entrada, File salida) {
		return encriptador.cifrarArchivoEnBinario(entrada, salida);
	}
	
	public boolean descifrarArchivoEnBinario(File entrada, File salida) {
		return encriptador.descifrarArchivoEnBinario(entrada, salida);
	}
	
	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////
	
	public Gestor_Ventanas getGestor() {
		return gestor;
	}

	public Encriptador getEncriptador() {
		return encriptador;
	}

	public Acerca getAcerca() {
		return acerca;
	}
	
}
