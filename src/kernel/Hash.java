package kernel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hash {
	/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */

	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////

	private List<Character> alfabeto = new ArrayList<>();
	private List<Character> cifrado = new ArrayList<>();

	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////

	public Hash() {
		Character[] arrayAlfabeto = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', '*', '#', '$', '€', '?', '¿', '!', '¡', '@', '%', '&', '=', '~', '<', '>', '-', '+',
				'-', '/', '|', '(', ')', '[', ']', '{', '}', 'ç', '.', ',', '_', '"', '\'', ':', ';', ' ', '\n' };
		Character[] arrayCifrado = { 'm', '?', 'I', '=', '#', '€', 't', '|', 'x', 'ç', 'p', '-', 'W', '+', 'G', 's',
				'_', 'F', 'h', 'R', ',', 'A', 'c', '6', '@', 'Z', '8', 'y', '.', 'a', 'E', '(', 'q', 'r', 'u', 'l', '5',
				'&', '¿', '¡', 'i', 'e', '<', '*', 'N', '7', '-', '>', 'J', '~', '1', '!', 'j', '[', '%', '/', 'o', '0',
				'}', ']', '{', '2', 'K', 'Y', 'B', 'P', 'D', 'Q', ')', 'V', 'T', 'C', 'L', 'O', '9', 'M', 'b', 'd', '3',
				'U', '4', 'w', 'v', 'S', '$', '"', 'X', '\'', 'H', ';', 'k', 'g', 'f', ':', 'z', 'n', ' ', '\n' };

		alfabeto = Arrays.asList(arrayAlfabeto);
		cifrado = Arrays.asList(arrayCifrado);
	}

	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////

	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public List<Character> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(List<Character> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public List<Character> getCifrado() {
		return cifrado;
	}

	public void setCifrado(List<Character> cifrado) {
		this.cifrado = cifrado;
	}

}
