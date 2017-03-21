package tc3.horarioDeClases;

public class Materia {
	private String codigo;
	private String nombre;
	/**
	 * @param codigo
	 * @param nombre
	 */
	public Materia(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	@Override
	public String toString() {
		return "Materia [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}