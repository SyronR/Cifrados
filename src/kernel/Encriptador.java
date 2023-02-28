package kernel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class Encriptador {
	/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */

	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////

	private Hash hash;
	private Encriptador_Main main;

	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////

	public Encriptador(Encriptador_Main main) {
		hash = new Hash();
		this.main = main;
	}

	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////

	private String cifrarContenido(File entrada) {
		String textoCifrado = "";
		String linea = "";

		char caracter;
		int indice;

		try (BufferedReader lectura = new BufferedReader(new FileReader(entrada))) {

			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Cifrando contenido...");
			while ((linea = lectura.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					caracter = linea.charAt(i);

					indice = hash.getAlfabeto().indexOf(caracter);
					textoCifrado += hash.getCifrado().get(indice);
				}

				textoCifrado += "\n";
			}
			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Contenido Cifrado con exito");

		} catch (Exception e) {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			textoCifrado = "error";
		}

		return textoCifrado;
	}

	private String descifrarContenido(File entrada) {
		String textoDescifrado = "";
		String linea = "";

		char caracter;
		int indice;

		try (BufferedReader lectura = new BufferedReader(new FileReader(entrada))) {

			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Descifrando contenido...");
			while ((linea = lectura.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					caracter = linea.charAt(i);

					indice = hash.getCifrado().indexOf(caracter);
					textoDescifrado += hash.getAlfabeto().get(indice);
				}

				textoDescifrado += "\n";
			}
			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Contenido descifrado con exito");

		} catch (Exception e) {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			textoDescifrado = "error";
		}

		return textoDescifrado;
	}

	protected boolean cifrarArchivoNormal(File entrada, File salida) {
		boolean estado = false;
		String contenido;

		contenido = cifrarContenido(entrada);

		if (!contenido.equals("error")) {

			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escribiendo datos...");
			try (BufferedWriter escritura = new BufferedWriter(new FileWriter(salida, false))) {

				escritura.write(contenido);
				estado = true;
				main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escritura realizada correctamente");

			} catch (Exception e) {
				main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			}
		}

		return estado;
	}

	protected boolean descifrarArchivoNormal(File entrada, File salida) {
		boolean estado = false;
		String contenido;

		contenido = descifrarContenido(entrada);

		if (!contenido.equals("error")) {

			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escribiendo datos...");
			try (BufferedWriter escritura = new BufferedWriter(new FileWriter(salida, false))) {

				escritura.write(contenido);
				estado = true;
				main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escritura realizada correctamente");

			} catch (Exception e) {
				main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			}
		}

		return estado;
	}

	protected boolean cifrarArchivoEnBinario(File entrada, File salida) {
		boolean estado = false;
		String contenido;

		contenido = cifrarContenido(entrada);

		if (!contenido.equals("error")) {

			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escribiendo datos...");
			try (DataOutputStream escritura = new DataOutputStream(new FileOutputStream(salida, false))) {

				escritura.writeUTF(contenido);
				estado = true;
				main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escritura realizada correctamente");

			} catch (Exception e) {
				main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			}
		}

		return estado;
	}

	protected boolean descifrarArchivoEnBinario(File entrada, File salida) {
		boolean estado = false;
		String cifrado = "";
		String descifrado = "";
		File temporal = new File("temporal.txt");

		/* OBTENER CIFRADO */
		main.getGestor().getVentanaRegistro().escribirEnElRegistro("Leyendo datos...");
		try (DataInputStream lectura = new DataInputStream(new FileInputStream(entrada))) {

			while (true) {
				cifrado = lectura.readUTF();
			}

		} catch (EOFException e) {

		} catch (Exception e) {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
		}

		/* ESCRIBIR CIFRADO */
		main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escribiendo datos en archivo temporal...");
		try (BufferedWriter escritura = new BufferedWriter(new FileWriter(temporal))) {

			escritura.write(cifrado);

		} catch (Exception e) {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
		}

		/* OBTENER DESCIFRADO */
		descifrado = descifrarContenido(temporal);
		if (temporal.delete()) {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Archivo temporal eliminado correctamente");
		} else {
			main.getGestor().getVentanaRegistro().escribirEnElRegistro("ERROR: No se ha podido borrar el archivo temporal");
		}

		if (!descifrado.equals("error")) {
			/* ESCRIBIR DESCIFRADO */
			main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escribiendo datos...");
			try (BufferedWriter escritura = new BufferedWriter(new FileWriter(salida, false))) {

				escritura.write(descifrado);
				estado = true;
				main.getGestor().getVentanaRegistro().escribirEnElRegistro("Escritura realizada correctamente");

			} catch (Exception e) {
				main.getGestor().getVentanaRegistro().escribirEnElRegistro(e.getMessage());
			}
		}

		return estado;
	}

	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public Hash getHash() {
		return hash;
	}

	public Encriptador_Main getMain() {
		return main;
	}
}
