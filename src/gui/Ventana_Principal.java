package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana_Principal extends JFrame {
	/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */

	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////

	private static final long serialVersionUID = 1L;

	private Gestor_Ventanas gestor;

	private Gestor_Archivos gestorArchivos;
	private FileNameExtensionFilter filtroTxt;
	private FileNameExtensionFilter filtroCfn;
	private FileNameExtensionFilter filtroCfb;
	private FileNameExtensionFilter filtroHash;

	private JPanel panelPrincipal;
	private JPanel panelAcciones;
	private JPanel panelHash;
	private JButton botonCargarHash;
	private JButton botonGenerarHash;
	private JButton botonCifrarNormal;
	private JButton botonDescifrarNormal;
	private JPanel panelOpciones;
	private JButton botonCifradoBinario;
	private JButton botonDescifradoBinario;
	private JButton botonMostrarRegistro;
	private JButton botonMostrarAcerca;

	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////

	public Ventana_Principal(Gestor_Ventanas gestor) {
		this.gestor = gestor;

		gestorArchivos = this.gestor.getGestorArchivos();
		filtroTxt = new FileNameExtensionFilter("Fichero de texto", "txt");
		filtroCfn = new FileNameExtensionFilter("Fichero de Cifrado Normal", "cfn");
		filtroCfb = new FileNameExtensionFilter("Fichero de Cifrado Binario", "cfb");
		filtroHash = new FileNameExtensionFilter("Fichero de codigo de cifrado", "hash");

		gestorArchivos.addChoosableFileFilter(filtroTxt);
		gestorArchivos.addChoosableFileFilter(filtroCfn);
		gestorArchivos.addChoosableFileFilter(filtroCfb);
		gestorArchivos.addChoosableFileFilter(filtroHash);

		/* Cierre en Cascada */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Look & Feel */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			gestor.getVentanaRegistro().escribirEnElRegistro("ERROR: No se ha podido establecer el Look & Feel (Ventana Principal)");
		}
		
		setResizable(false);
		setTitle("Cifrados (Cifra2)");
		setSize(new Dimension(335, 240));

		this.panelPrincipal = new JPanel();
		getContentPane().add(this.panelPrincipal, BorderLayout.CENTER);

		this.panelAcciones = new JPanel();
		this.panelAcciones.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Opciones de Cifrado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.panelHash = new JPanel();
		this.panelHash.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Codigo de cifrado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.panelOpciones = new JPanel();
		this.panelOpciones.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mostrar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_panelPrincipal = new GroupLayout(this.panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrincipal.createSequentialGroup().addContainerGap().addGroup(gl_panelPrincipal
						.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelAcciones, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addGroup(gl_panelPrincipal.createSequentialGroup()
								.addComponent(this.panelHash, GroupLayout.PREFERRED_SIZE, 148,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.panelOpciones, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelPrincipal.setVerticalGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup().addContainerGap()
						.addComponent(this.panelAcciones, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.panelOpciones, GroupLayout.PREFERRED_SIZE, 83,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.panelHash, GroupLayout.PREFERRED_SIZE, 83,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		this.botonMostrarRegistro = new JButton("Mostrar Registro");
		this.botonMostrarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRegistro();
			}
		});

		this.botonMostrarAcerca = new JButton("Mostar Acerca");
		this.botonMostrarAcerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAcerca();
			}
		});
		GroupLayout gl_panelOpciones = new GroupLayout(this.panelOpciones);
		gl_panelOpciones.setHorizontalGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelOpciones.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.botonMostrarAcerca, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 111,
										Short.MAX_VALUE)
								.addComponent(this.botonMostrarRegistro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										102, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelOpciones
				.setVerticalGroup(gl_panelOpciones.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_panelOpciones.createSequentialGroup().addGap(6).addComponent(this.botonMostrarRegistro)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.botonMostrarAcerca)
								.addContainerGap(17, Short.MAX_VALUE)));
		this.panelOpciones.setLayout(gl_panelOpciones);

		this.botonCargarHash = new JButton("Cargar Hash");
		this.botonCargarHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarHash();
			}
		});

		this.botonGenerarHash = new JButton("Generar Hash");
		this.botonGenerarHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarHash();
			}
		});
		GroupLayout gl_panelHash = new GroupLayout(this.panelHash);
		gl_panelHash.setHorizontalGroup(
			gl_panelHash.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHash.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHash.createParallelGroup(Alignment.TRAILING)
						.addComponent(botonCargarHash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
						.addComponent(botonGenerarHash, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 116, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelHash.setVerticalGroup(
			gl_panelHash.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHash.createSequentialGroup()
					.addGap(6)
					.addComponent(botonCargarHash)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(botonGenerarHash)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		this.panelHash.setLayout(gl_panelHash);

		this.botonCifrarNormal = new JButton("Cifrado Normal");
		this.botonCifrarNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cifrarArchivo();
			}
		});

		this.botonDescifrarNormal = new JButton("Descifrado Normal");
		this.botonDescifrarNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descifrarArchivo();
			}
		});

		this.botonCifradoBinario = new JButton("Cifrado Binario");
		this.botonCifradoBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cifrarArchivoEnBinario();
			}
		});

		this.botonDescifradoBinario = new JButton("Descifado Binario");
		this.botonDescifradoBinario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descifrarArchivoEnBinario();
			}
		});

		GroupLayout gl_panelAcciones = new GroupLayout(this.panelAcciones);
		gl_panelAcciones.setHorizontalGroup(gl_panelAcciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAcciones.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelAcciones.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(this.botonDescifrarNormal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.botonCifrarNormal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126,
										Short.MAX_VALUE))
						.addGap(25).addGroup(
								gl_panelAcciones.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panelAcciones.createSequentialGroup()
												.addComponent(this.botonCifradoBinario, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(10))
										.addGroup(gl_panelAcciones
												.createSequentialGroup().addComponent(this.botonDescifradoBinario,
														GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
												.addContainerGap()))));
		gl_panelAcciones.setVerticalGroup(gl_panelAcciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAcciones.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelAcciones.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.botonCifrarNormal).addComponent(this.botonCifradoBinario))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelAcciones.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.botonDescifrarNormal).addComponent(this.botonDescifradoBinario))
						.addContainerGap(86, Short.MAX_VALUE)));
		this.panelAcciones.setLayout(gl_panelAcciones);
		this.panelPrincipal.setLayout(gl_panelPrincipal);

	}

	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////

	protected void mensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void advertencia(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
	}

	protected void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void cifrarArchivo() {
		File entrada;
		File salida;
		int valor;

		if (comprobarCodigosDeCifrado() == false) {
			gestorArchivos.setFileFilter(filtroTxt);
			valor = gestorArchivos.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".txt")) {
				entrada = gestorArchivos.getSelectedFile();

				gestorArchivos.setFileFilter(filtroCfn);
				valor = gestorArchivos.showSaveDialog(null);

				if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".cfn")) {
					salida = gestorArchivos.getSelectedFile();

					gestor.getVentanaRegistro().escribirEnElRegistro("CIFRANDO ARCHIVO NORMAL:");
					if (gestor.getMain().cifrarArchivoNormal(entrada, salida))
						mensaje("Archivo cifrado con exito");

					else
						error("Ha ocurrido un error inesperado al cifrar el archivo");

				} else if (valor != JFileChooser.CANCEL_OPTION)
					error("El archivo debe tener una extension tipo cifrado normal (.cfn)");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo fichero de texto (.txt)");
			
		} else {
			advertencia("Los codigos de cifrado no estan cargados");
		}

	}

	private void descifrarArchivo() {
		File entrada;
		File salida;
		int valor;

		if (comprobarCodigosDeCifrado() == false) {
			gestorArchivos.setFileFilter(filtroCfn);
			valor = gestorArchivos.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".cfn")) {
				entrada = gestorArchivos.getSelectedFile();

				gestorArchivos.setFileFilter(filtroTxt);
				valor = gestorArchivos.showSaveDialog(null);

				if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".txt")) {
					salida = gestorArchivos.getSelectedFile();

					gestor.getVentanaRegistro().escribirEnElRegistro("DESCIFRANDO ARCHIVO NORMAL:");
					if (gestor.getMain().descifrarArchivoNormal(entrada, salida))
						mensaje("Archivo descifrado con exito");

					else
						error("Ha ocurrido un error inesperado al descifrar el archivo");

				} else if (valor != JFileChooser.CANCEL_OPTION)
					error("El archivo debe tener una extension tipo fichero de texto (.txt)");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo cifrado normal (.cfn)");
			
		} else {
			advertencia("Los codigos de cifrado no estan cargados");
		}
	}

	private void cifrarArchivoEnBinario() {
		File entrada;
		File salida;
		int valor;

		if (comprobarCodigosDeCifrado() == false) {
			gestorArchivos.setFileFilter(filtroTxt);
			valor = gestorArchivos.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".txt")) {
				entrada = gestorArchivos.getSelectedFile();

				gestorArchivos.setFileFilter(filtroCfb);
				valor = gestorArchivos.showOpenDialog(null);

				if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".cfb")) {
					salida = gestorArchivos.getSelectedFile();

					gestor.getVentanaRegistro().escribirEnElRegistro("CIFRANDO ARCHIVO BINARIO:");
					if (gestor.getMain().cifrarArchivoEnBinario(entrada, salida))
						mensaje("Archivo cifrado con exito");

					else
						error("Ha ocurrido un error inesperado al cifrar el archivo");

				} else if (valor != JFileChooser.CANCEL_OPTION)
					error("El archivo debe tener una extension tipo cifrado normal (.cfb)");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo fichero de texto (.txt)");
			
		} else {
			advertencia("Los codigos de cifrado no estan cargados");
		}
	}

	private void descifrarArchivoEnBinario() {
		File entrada;
		File salida;
		int valor;

		if (comprobarCodigosDeCifrado() == false) {
			gestorArchivos.setFileFilter(filtroCfb);
			valor = gestorArchivos.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".cfb")) {
				entrada = gestorArchivos.getSelectedFile();

				gestorArchivos.setFileFilter(filtroTxt);
				valor = gestorArchivos.showOpenDialog(null);

				if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".txt")) {
					salida = gestorArchivos.getSelectedFile();

					gestor.getVentanaRegistro().escribirEnElRegistro("DESCRIFRANDO ARCHIVO BINARIO:");
					if (gestor.getMain().descifrarArchivoEnBinario(entrada, salida))
						mensaje("Archivo descifrado con exito");

					else
						error("Ha ocurrido un error inesperado al descifrar el archivo");

				} else if (valor != JFileChooser.CANCEL_OPTION)
					error("El archivo debe tener una extension tipo cifrado normal (.txt)");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo fichero de texto (.cfb)");
			
		} else {
			advertencia("Los codigos de cifrado no estan cargados");
		}
	}

	private void cargarHash() {
		StringTokenizer palabras;
		ArrayList<Character> lista = new ArrayList<>();
		String palabra;
		String linea;
		Character caracter = null;
		boolean hayFallo = false;
		
		File entrada;
		int valor;
		
		gestorArchivos.setFileFilter(filtroHash);
		valor = gestorArchivos.showOpenDialog(null);
		
		if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".hash")) {
			entrada = gestorArchivos.getSelectedFile();
			
			try (BufferedReader lectura = new BufferedReader(new FileReader(entrada))) {
				
				/* Lectura del no cifrado */
				linea = lectura.readLine();
				palabras = new StringTokenizer(linea, "^");

				while (palabras.hasMoreTokens()) {
					palabra = palabras.nextToken();

					if (palabra.charAt(0) == '\\') {
						if (palabra.charAt(1) == 'n') {
							caracter = '\n';

						} else if (palabra.charAt(1) == '\'') {
							caracter = '\'';

						} else if (palabra.charAt(1) == '\"') {
							caracter = '\"';

						}
						
					} else {
						caracter = palabra.charAt(0);
					}

					lista.add(caracter);
				}

				/* COMPRUEBO SI HAY FALLO AL INSERTAR EL ARRAY DEL ALFABETO */
				if (gestor.getMain().getEncriptador().getHash().setAlfabeto(lista)) {
					lista.clear();
					
					/* Lectura del cifrado */
					linea = lectura.readLine();
					palabras = new StringTokenizer(linea, "^");

					while (palabras.hasMoreTokens()) {
						palabra = palabras.nextToken();

						if (palabra.charAt(0) == '\\') {
							if (palabra.charAt(1) == 'n') {
								caracter = '\n';

							} else if (palabra.charAt(1) == '\'') {
								caracter = '\'';

							} else if (palabra.charAt(1) == '\"') {
								caracter = '\"';

							}
							
						} else {
							caracter = palabra.charAt(0);
						}

						lista.add(caracter);
					}
					
					/* COMPRUEBO SI HAY FALLO AL INSERTAR EL ARRAY DEL CIFRADO */
					if (gestor.getMain().getEncriptador().getHash().setCifrado(lista) == false)
						hayFallo = true;
					
				} else {
					hayFallo = true;
				}

				
			} catch (Exception e) {
				gestor.getVentanaRegistro().escribirEnElRegistro(e.getMessage());
				hayFallo = true;
			}
			
			if (hayFallo) {
				gestor.getVentanaRegistro().escribirEnElRegistro("Se ha producido un error al escribir los codigos de cifrado");
				error("ERROR: No se han podido escribir los codigos de cifrado");
				
				gestor.getMain().getEncriptador().getHash().limpiarListas();
			} else {
				gestor.getVentanaRegistro().escribirEnElRegistro("Codigos de cifrado cargados correctamente");
				mensaje("Codigos de cifrado cargados correctamente");
			}
			
		} else if (valor != JFileChooser.CANCEL_OPTION) {
			error("El archivo debe tener una extension de tipo HASH (.hash)");
		}
	}

	private void generarHash() {
		advertencia("Disponible en la version 3.0 (Frutos) [Versión Final]");
	}

	private void mostrarRegistro() {
		gestor.getVentanaRegistro().setVisible(true);
	}

	private void mostrarAcerca() {
		gestor.getVentanaAcerca().setVisible(true);
	}
	
	private boolean comprobarCodigosDeCifrado() {
		return gestor.getMain().getEncriptador().getHash().estanVacias();		
	}

	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public Gestor_Ventanas getGestor() {
		return gestor;
	}
}
