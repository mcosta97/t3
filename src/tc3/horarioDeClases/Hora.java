package tc3.horarioDeClases;

public class Hora {
	private String codMateria;
	private String profesor;
	/**
	 * @param codMateria
	 * @param codProfesor
	 */
	public Hora(String codigo, String nombre) {
		this.codMateria = codigo;
		this.profesor = nombre;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public String getCodigo() {
		return codMateria;
	}
	@Override
	public String toString() {
		return "Hora [codMateria=" + codMateria + ", profesor=" + profesor + "]";
	}
}
