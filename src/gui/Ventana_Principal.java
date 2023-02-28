package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

	private Gestor_Archivos gestorFicheros;
	private FileNameExtensionFilter filtroTxt;
	private FileNameExtensionFilter filtroCfn;
	private FileNameExtensionFilter filtroCfb;

	private JPanel panelPrincipal;
	private JPanel panelAcciones;
	private JPanel panelHash;
	private JButton botonCargarHash;
	private JButton botonEliminarHash;
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

		gestorFicheros = this.gestor.getGestorArchivos();
		filtroTxt = new FileNameExtensionFilter("Fichero de texto", "txt");
		filtroCfn = new FileNameExtensionFilter("Fichero de Cifrado Normal", "cfn");
		filtroCfb = new FileNameExtensionFilter("Fichero de Cifrado Binario", "cfb");

		gestorFicheros.addChoosableFileFilter(filtroTxt);
		gestorFicheros.addChoosableFileFilter(filtroCfn);
		gestorFicheros.addChoosableFileFilter(filtroCfb);

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

		this.botonEliminarHash = new JButton("Eliminar Hash");
		this.botonEliminarHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarHash();
			}
		});
		GroupLayout gl_panelHash = new GroupLayout(this.panelHash);
		gl_panelHash.setHorizontalGroup(gl_panelHash.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHash.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelHash.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.botonEliminarHash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95,
										Short.MAX_VALUE)
								.addComponent(this.botonCargarHash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelHash.setVerticalGroup(gl_panelHash.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHash.createSequentialGroup().addGap(6).addComponent(this.botonCargarHash)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.botonEliminarHash)
						.addContainerGap(35, Short.MAX_VALUE)));
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

		gestorFicheros.setFileFilter(filtroTxt);
		valor = gestorFicheros.showOpenDialog(null);

		if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".txt")) {
			entrada = gestorFicheros.getSelectedFile();

			gestorFicheros.setFileFilter(filtroCfn);
			valor = gestorFicheros.showSaveDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".cfn")) {
				salida = gestorFicheros.getSelectedFile();

				gestor.getVentanaRegistro().escribirEnElRegistro("CIFRANDO ARCHIVO NORMAL:");
				if (gestor.getMain().cifrarArchivoNormal(entrada, salida))
					mensaje("Archivo cifrado con exito");

				else
					error("Ha ocurrido un error inesperado al cifrar el archivo");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo cifrado normal (.cfn)");

		} else if (valor != JFileChooser.CANCEL_OPTION)
			error("El archivo debe tener una extension tipo fichero de texto (.txt)");

	}

	private void descifrarArchivo() {
		File entrada;
		File salida;
		int valor;

		gestorFicheros.setFileFilter(filtroCfn);
		valor = gestorFicheros.showOpenDialog(null);

		if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".cfn")) {
			entrada = gestorFicheros.getSelectedFile();

			gestorFicheros.setFileFilter(filtroTxt);
			valor = gestorFicheros.showSaveDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".txt")) {
				salida = gestorFicheros.getSelectedFile();

				gestor.getVentanaRegistro().escribirEnElRegistro("DESCIFRANDO ARCHIVO NORMAL:");
				if (gestor.getMain().descifrarArchivoNormal(entrada, salida))
					mensaje("Archivo descifrado con exito");

				else
					error("Ha ocurrido un error inesperado al descifrar el archivo");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo fichero de texto (.txt)");

		} else if (valor != JFileChooser.CANCEL_OPTION)
			error("El archivo debe tener una extension tipo cifrado normal (.cfn)");
	}

	private void cifrarArchivoEnBinario() {
		File entrada;
		File salida;
		int valor;

		gestorFicheros.setFileFilter(filtroTxt);
		valor = gestorFicheros.showOpenDialog(null);

		if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".txt")) {
			entrada = gestorFicheros.getSelectedFile();

			gestorFicheros.setFileFilter(filtroCfb);
			valor = gestorFicheros.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".cfb")) {
				salida = gestorFicheros.getSelectedFile();

				gestor.getVentanaRegistro().escribirEnElRegistro("CIFRANDO ARCHIVO BINARIO:");
				if (gestor.getMain().cifrarArchivoEnBinario(entrada, salida))
					mensaje("Archivo cifrado con exito");

				else
					error("Ha ocurrido un error inesperado al cifrar el archivo");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo cifrado normal (.cfb)");

		} else if (valor != JFileChooser.CANCEL_OPTION)
			error("El archivo debe tener una extension tipo fichero de texto (.txt)");
	}

	private void descifrarArchivoEnBinario() {
		File entrada;
		File salida;
		int valor;

		gestorFicheros.setFileFilter(filtroCfb);
		valor = gestorFicheros.showOpenDialog(null);

		if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".cfb")) {
			entrada = gestorFicheros.getSelectedFile();

			gestorFicheros.setFileFilter(filtroTxt);
			valor = gestorFicheros.showOpenDialog(null);

			if (valor == JFileChooser.APPROVE_OPTION && gestorFicheros.getSelectedFile().getName().endsWith(".txt")) {
				salida = gestorFicheros.getSelectedFile();

				gestor.getVentanaRegistro().escribirEnElRegistro("DESCRIFRANDO ARCHIVO BINARIO:");
				if (gestor.getMain().descifrarArchivoEnBinario(entrada, salida))
					mensaje("Archivo descifrado con exito");

				else
					error("Ha ocurrido un error inesperado al descifrar el archivo");

			} else if (valor != JFileChooser.CANCEL_OPTION)
				error("El archivo debe tener una extension tipo cifrado normal (.txt)");

		} else if (valor != JFileChooser.CANCEL_OPTION)
			error("El archivo debe tener una extension tipo fichero de texto (.cfb)");
	}

	private void cargarHash() {
		advertencia("Disponible en la version 2.1 (Flores)");
	}

	private void eliminarHash() {
		advertencia("Disponible en la version 2.1 (Flores)");
	}

	private void mostrarRegistro() {
		gestor.getVentanaRegistro().setVisible(true);
	}

	private void mostrarAcerca() {
		gestor.getVentanaAcerca().setVisible(true);
	}

	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public Gestor_Ventanas getGestor() {
		return gestor;
	}
}
