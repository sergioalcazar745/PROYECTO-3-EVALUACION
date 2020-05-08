package Graficos;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import Base_de_datos.Gestion;
import Base_de_datos.conexion;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class panelOferta extends JPanel implements ActionListener{
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel();
	private String [] Datos = new String [1];
	private JTextField tfPorcentaje;
	private ResultSet resultado;
	private Connection con;
	private conexion cx = new conexion();
	private Gestion gdb;
	private JComboBox comboBox_Nombres;
	private JComboBox comboBox_Categoria;
	private JButton btnA�adir;

	public panelOferta() {
		setBackground(Color.WHITE);
		setBounds(232, 11, 853, 544);
		setLayout(null);
		
		table = new JTable();
		modelo.addColumn("Articulo");
		modelo.addColumn("Categoria");
		modelo.addColumn("Porcentaje");
		table.setModel(modelo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(96, 140, 666, 339);
		add(scrollPane);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		comboBox_Nombres = new JComboBox();
		comboBox_Nombres.setBounds(175, 93, 146, 22);
		add(comboBox_Nombres);
		
		comboBox_Categoria = new JComboBox();
		comboBox_Categoria.setBounds(175, 40, 146, 22);
		add(comboBox_Categoria);
		
		tfPorcentaje = new JTextField();
		tfPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPorcentaje.setBounds(449, 71, 192, 22);
		add(tfPorcentaje);
		tfPorcentaje.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(644, 71, 17, 22);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Porcentaje de descuento");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(449, 46, 192, 16);
		add(lblNewLabel_1);
		
		JLabel lblArticulo = new JLabel("Articulo");
		lblArticulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblArticulo.setBounds(175, 67, 146, 27);
		add(lblArticulo);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setFont(new Font("Arial", Font.BOLD, 16));
		lblCategoria.setBounds(175, 13, 146, 27);
		add(lblCategoria);
		
		btnA�adir = new JButton("");
		btnA�adir.addActionListener(this);
		btnA�adir.setIcon(new ImageIcon(panelOferta.class.getResource("/Imagenes/plus.png")));
		btnA�adir.setBounds(354, 56, 62, 38);
		btnA�adir.setBorderPainted(false);
		btnA�adir.setOpaque(false);
		btnA�adir.setContentAreaFilled(false);
		btnA�adir.setFocusPainted(false);
		add(btnA�adir);
		
		insertarArticulos();
		insertarCategoria();
	}
	
	private void insertarArticulos(){
		
		try {
			con = (Connection) cx.getConexion();
			gdb = new Gestion();
			resultado = gdb.recorrerArticuloGenerico();
			comboBox_Nombres.addItem("");
			
			while(resultado.next()) {
				comboBox_Nombres.addItem(resultado.getString("nombre"));
			}
			
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Fallo al conectar");
			e1.printStackTrace();
		}		
	}
	
	private void insertarCategoria() {
		try {
			con = (Connection) cx.getConexion();
			gdb = new Gestion();
			resultado = gdb.recorrerCategorias();
			comboBox_Categoria.addItem("");
			
			while(resultado.next()) {
				comboBox_Categoria.addItem(resultado.getString("nombre"));
			}
			
		}catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Fallo al conectar");
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evento = e.getSource();
		
		if(evento.equals(btnA�adir)) {
			gdb.crearOfertas(comboBox_Nombres.getSelectedItem().toString(), comboBox_Categoria.getSelectedItem().toString(), tfPorcentaje.getText().toString());
		}
	}
	protected JComboBox getComboBox_Nombres() {
		return comboBox_Nombres;
	}
	protected JComboBox getComboBox_Categoria() {
		return comboBox_Categoria;
	}
	protected JButton getBtnA�adir() {
		return btnA�adir;
	}
}
