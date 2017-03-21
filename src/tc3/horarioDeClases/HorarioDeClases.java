package tc3.horarioDeClases;

public class HorarioDeClases {
	private static Curso curso;
	
	public static void main(String args[]) {
		curso = new Curso(2, 1, 'X');
		cargarDatos();
		System.out.println(curso.getNombre());
		curso.mostrarHorario();
	}
	
	private static void cargarDatos() {
	}
}