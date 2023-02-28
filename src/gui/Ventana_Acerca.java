package gui;

import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class Ventana_Acerca extends JDialog {
/* CLASE IMPORTADA CON PLANTILLA_POO V.2 */
	
	//////////////////////////////////////////
	// ATRIBUTOS /////////////////////////////
	//////////////////////////////////////////
	
	private static final long serialVersionUID = 1L;
	private Gestor_Ventanas gestor;
	private JLabel labelCambios;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	//////////////////////////////////////////
	// CONSTRUCTOR ///////////////////////////
	//////////////////////////////////////////
	
	public Ventana_Acerca(Gestor_Ventanas gestor) {
		setResizable(false);
		setSize(new Dimension(350, 260));
		setTitle("Acerca de: " + gestor.getVentanaPrincipal().getTitle());
		setMinimumSize(new Dimension(300, 200));
		getContentPane().setSize(new Dimension(300, 200));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		this.lblNewLabel = new JLabel("Desarrollador:");
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		this.lblNewLabel_1 = new JLabel("Versi\u00F3n:");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		this.lblNewLabel_2 = new JLabel("Correo Electronico:");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		this.lblNewLabel_3 = new JLabel("Notas del Parche:");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel labelDesarrollador = new JLabel("New label");
		labelDesarrollador.setText(gestor.getMain().getAcerca().getDesarrollador());
		
		JLabel labelVersion = new JLabel("New label");
		labelVersion.setText(gestor.getMain().getAcerca().getVersion());
		
		JLabel labelCorreo = new JLabel("New label");
		labelCorreo.setText(gestor.getMain().getAcerca().getCorreo());
		
		this.labelCambios = new JLabel("New label");
		labelCambios.setText(gestor.getMain().getAcerca().getCambios());
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(this.lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(labelDesarrollador, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(32))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(this.lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelVersion, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(this.lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED, 67, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(labelCorreo, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(32))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(this.lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(this.labelCambios, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addGap(32)))
					.addGap(171))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(this.lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelDesarrollador)
					.addGap(18)
					.addComponent(this.lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelVersion)
					.addGap(18)
					.addComponent(this.lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelCorreo)
					.addGap(18)
					.addComponent(this.lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.labelCambios)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		this.gestor = gestor;
	}
	
	//////////////////////////////////////////
	// METODOS ///////////////////////////////
	//////////////////////////////////////////
	
	//////////////////////////////////////////
	// GET/SET ///////////////////////////////
	//////////////////////////////////////////
	
	public Gestor_Ventanas getGestor() {
		return gestor;
	}
}
