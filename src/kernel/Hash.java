package kernel;

import java.util.ArrayList;
import java.util.List;

public class Hash {
	/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */

	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////

	private List<Character> alfabeto;
	private List<Character> cifrado;

	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////

	public Hash() {
		alfabeto = new ArrayList<>();
		cifrado = new ArrayList<>();
	}

	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////

	public void limpiarListas() {
		alfabeto.clear();
		cifrado.clear();
	}
	
	public boolean estanVacias() {
		if (alfabeto.isEmpty() || cifrado.isEmpty())
			return true;
		else
			return false;
		
	}
	
	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public List<Character> getAlfabeto() {
		return alfabeto;
	}

	public boolean setAlfabeto(List<Character> alfabeto) {
		return this.alfabeto.addAll(alfabeto);
	}

	public List<Character> getCifrado() {
		return cifrado;
	}

	public boolean setCifrado(List<Character> cifrado) {
		return this.cifrado.addAll(cifrado);
	}

}
