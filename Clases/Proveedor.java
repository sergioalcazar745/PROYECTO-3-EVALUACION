package Clases;

public class Proveedor {
	protected int id_proveedor;
	protected String nombreCompa�ia;
	
	protected Proveedor(int id_proveedor, String nombreCompa�ia) {
		this.id_proveedor = id_proveedor;
		this.nombreCompa�ia = nombreCompa�ia;
	}

	protected int getId_proveedor() {
		return id_proveedor;
	}

	protected void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	protected String getNombreCompa�ia() {
		return nombreCompa�ia;
	}

	protected void setNombreCompa�ia(String nombreCompa�ia) {
		this.nombreCompa�ia = nombreCompa�ia;
	}
}
