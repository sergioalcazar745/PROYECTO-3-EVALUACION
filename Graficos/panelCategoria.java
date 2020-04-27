package Graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import Base_de_datos.Gestion;
import Base_de_datos.conexion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class panelCategoria extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String [] Datos = new String [1];
	private JTextField tfA�adir;
	private JButton btnA�adir;
	private conexion cx = new conexion();
	private Connection con;
	private ResultSet resultado;
	private Gestion gdb;
	private JButton btnAceptar;

	public panelCategoria() throws SQLException {
		setVisible(true);		
		setBounds(100, 100, 545, 398);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			table = new JTable();
			modelo.addColumn("Categoria");
			table.setModel(modelo);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(49, 67, 419, 217);
			contentPanel.add(scrollPane);
			scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
		}
		
		tfA�adir = new JTextField();
		tfA�adir.setFont(new Font("Arial", Font.PLAIN, 12));
		tfA�adir.setBounds(71, 34, 264, 25);
		contentPanel.add(tfA�adir);
		tfA�adir.setColumns(10);
		
		btnA�adir = new JButton("A\u00F1adir");
		btnA�adir.setFont(new Font("Arial", Font.PLAIN, 18));
		btnA�adir.addActionListener(this);
		btnA�adir.setBounds(359, 36, 97, 25);
		contentPanel.add(btnA�adir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(this);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}

		introducirDatos();
	}
	public JButton getBtnA�adir() {
		return btnA�adir;
	}
	public JTextField getTfA�adir() {
		return tfA�adir;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		
		if(evento.equals(btnA�adir)) {
			
			if(tfA�adir.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Si escribes algo mejor bro.");
			}else {
				Datos [0] = tfA�adir.getText();
				modelo.addRow(Datos);
				
				gdb = new Gestion();
				if(gdb.insertarCategoria(tfA�adir.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Categoria insertada");
				}else{
					JOptionPane.showMessageDialog(null, "Categoria no insertada");
				}
			}		
		}else if(evento.equals(btnAceptar)) {
			dispose();
		}
	}
	
	private void introducirDatos() {
		
		try {
			con = (Connection) cx.getConexion();
			System.out.println("Conexion realizada");
			gdb = new Gestion();
			resultado = gdb.recorrerCategorias();
			
			while(resultado.next()) {
				Datos [0] = resultado.getString("nombre");
				modelo.addRow(Datos);
			}
			
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Fallo al conectar");
			e1.printStackTrace();
		}
	}
	protected JButton getBtnAceptar() {
		return btnAceptar;
	}
}
