package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana_Registro extends JFrame {
	/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */

	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////

	private static final long serialVersionUID = 1L;

	private DateTimeFormatter hora;
	private Gestor_Ventanas gestor;
	
	private Gestor_Archivos gestorArchivos;
	private FileNameExtensionFilter filtroLog;
	private JPanel panel;
	private JPanel panelRegistro;
	private JScrollPane scrollPane;
	private JTextArea registro;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem itemLimpiarRegistro;
	private JMenuItem itemExportarRegistro;

	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////

	@SuppressWarnings("deprecation")
	public Ventana_Registro(Gestor_Ventanas gestor) {
		this.gestor = gestor;
        hora = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		
		gestorArchivos = this.gestor.getGestorArchivos();

		filtroLog = new FileNameExtensionFilter("Fichero de registro de errores", "log");
		gestorArchivos.addChoosableFileFilter(filtroLog);

		setSize(new Dimension(500, 300));
		setTitle("Registro");

		this.panel = new JPanel();
		getContentPane().add(this.panel, BorderLayout.CENTER);

		this.panelRegistro = new JPanel();
		this.panelRegistro.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(this.panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(this.panelRegistro, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE).addGap(9)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(this.panelRegistro, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE).addContainerGap()));
		this.panelRegistro.setLayout(new BorderLayout(0, 0));

		this.scrollPane = new JScrollPane();
		this.panelRegistro.add(this.scrollPane, BorderLayout.CENTER);

		this.registro = new JTextArea();
		this.scrollPane.setViewportView(this.registro);
		this.panel.setLayout(gl_panel);

		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);

		this.menu = new JMenu("Acciones");
		this.menuBar.add(this.menu);

		this.itemLimpiarRegistro = new JMenuItem("Limpiar Registro");
		this.itemLimpiarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarRegistro();
			}
		});
		this.menu.add(this.itemLimpiarRegistro);

		this.itemExportarRegistro = new JMenuItem("Exportar Registro");
		this.itemExportarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarRegistro();
			}
		});
		this.menu.add(this.itemExportarRegistro);
		
		/* INICIALIZAR REGISTRO */
		registro.setText(hora.format(LocalDateTime.now()) + " | Registro Inicializado:");
		registro.disable();
	}

	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////

	public void escribirEnElRegistro(String mensaje) {
		String textoRegistro = registro.getText() + "\n";
		String nuevoRegisto = hora.format(LocalDateTime.now()) + " | " + mensaje;
		
		registro.setText(textoRegistro + nuevoRegisto);
	}

	private void limpiarRegistro() {
		try {
			if (JOptionPane.showInternalConfirmDialog(gestorArchivos, "¿Esta seguro de que desea limpiar el registro?") == 0) {
				registro.setText("");
				registro.setText(hora.format(LocalDateTime.now()) + " | Registro Limpiado:");
			}
		} catch (Exception e) {
			gestor.getVentanaRegistro().escribirEnElRegistro(e.getMessage());
		}
		
	}

	private void exportarRegistro() {
		File salida;
		int valor;

		gestorArchivos.setFileFilter(filtroLog);
		valor = gestorArchivos.showOpenDialog(null);

		if (valor == JFileChooser.APPROVE_OPTION && gestorArchivos.getSelectedFile().getName().endsWith(".log")) {
			salida = gestorArchivos.getSelectedFile();

			try (BufferedWriter escritura = new BufferedWriter(new FileWriter(salida, false))) {

				escritura.write(registro.getText());

			} catch (IOException e) {
				escribirEnElRegistro(e.getMessage());
			}

			gestor.getVentanaPrincipal().mensaje("Registro exportado correctamente");

		} else if (valor != JFileChooser.CANCEL_OPTION) {
			gestor.getVentanaPrincipal().error("El fichero tiene que terminar con la extension .log");
		}

	}

	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////

	public Gestor_Ventanas getGestor() {
		return gestor;
	}

}
