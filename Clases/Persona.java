package Clases;

class Persona {
	protected int id_persona, telefono;
	protected String correo, contrase�a;
	
	protected Persona(int id_persona, int telefono, String correo, String contrase�a) {
		this.id_persona = id_persona;
		this.telefono = telefono;
		this.correo = correo;
		this.contrase�a = contrase�a;
	}

	protected int getId_persona() {
		return id_persona;
	}

	protected void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	protected int getTelefono() {
		return telefono;
	}

	protected void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	protected String getCorreo() {
		return correo;
	}

	protected void setCorreo(String correo) {
		this.correo = correo;
	}

	protected String getContrase�a() {
		return contrase�a;
	}

	protected void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
}
