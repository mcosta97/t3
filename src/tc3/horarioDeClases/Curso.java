package tc3.horarioDeClases;

import java.util.ArrayList;
import java.util.Iterator;

public class Curso {
	private int anio;
	private int cuatrimestre;
	private char cursada;
	private ArrayList<Materia> materias;
	private ArrayList<String> profesores;
	private Hora horario[][];

	public Curso(int anio, int cuatrimestre, char cursada) {
		// Debe inicializar correctamente todos sus atributos
		// independientemente de los parámetros que recibe.
		// Pensar bien cómo se va a inicializar la matriz de horario.
		this.anio = anio;
		this.cuatrimestre = cuatrimestre;
		this.cursada = cursada;
		materias = new ArrayList<>();
		profesores = new ArrayList<>();
		horario = new Hora[DiaClase.values().length][HoraClase.values().length];
	}
	
	public static void main (String[] args) {
		Curso curso = new Curso(1,2,'A');
		curso.addBloque(DiaClase.LUNES, HoraClase.PRIMERA, HoraClase.SEXTA,"", "Hola");
		curso.mostrarDia(0);
	}

	public String getNombre() {
		return String.format("%s°%s°%s", Integer.toString(this.anio, 10), this.cuatrimestre, this.cursada);
	}

	public boolean addProfesor(String nombre) {
		// Debe verificar que el profesor a agregar no exista,
		// sólo entonces debe agregarlo.
		// Devuelve si pudo o no agregar un nuevo profesor.
		boolean agrego = false;

		if (this.buscarProfesor(nombre) == -1) {
			agrego = true;
			profesores.add(nombre);
		}

		return agrego;
	}

	private int buscarProfesor(String nombre) {
		// Busca el nombre pasado por parámetro en la lista.
		// Devuelve -1 si no lo encuentra.
		Iterator<String> itProfesor;
		String profesor = "";
		itProfesor = profesores.iterator();
		boolean encontrado = false;
		int index = 0;

		while (itProfesor.hasNext() && !encontrado) {
			profesor = itProfesor.next();
			if (profesor.equals(nombre)) {
				encontrado = true;
			}

			index++;
		}

		if (!encontrado) {
			index = -1;
		}

		return index;
	}

	public void listarProfesores() {
		// Lista todos los profesores cargados.
		if (profesores.size() == 0) {
			System.out.println("No se cargaron profesores en este curso.");
		} else {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		}
	}

	public boolean addMateria(String codigo, String nombre) {
		// Debe verificar que la materia a agregar no exista
		// (buscándola por código) y sólo entonces debe agregarlo.
		// Devuelve si pudo o no agregar una nueva materia.
		boolean agrego = false;

		if (this.buscarMateria(codigo) == -1) {
			this.materias.add(new Materia(codigo, nombre));
			agrego = true;
		}

		return agrego;
	}

	private int buscarMateria(String codigo) {
		// Busca la materia el código pasado por parámetro.
		// Devuelve -1 si no la encuentra.

		Iterator<Materia> itMateria;
		Materia materia;
		itMateria = this.materias.iterator();
		boolean encontrado = false;
		int index = 0;

		while (itMateria.hasNext() && !encontrado) {
			materia = itMateria.next();
			if (materia.getCodigo().equals(codigo)) {
				encontrado = true;
			}

			index++;
		}

		if (!encontrado) {
			index = -1;
		}

		return index;
	}

	public void listarMaterias() {
		// Lista todas las materias cargadas.
		if(materias.size() == 0) {
			System.out.println("No hay materias cargadas en este curso.");
		} else {
			for (Materia materia : this.materias) {
				System.out.println(materia.getNombre());
			}
		}
	}

	public void listarDiasClase() {
		for (int d = 0; d < DiaClase.values().length; d++) {
			System.out.println(DiaClase.values()[d].toDisplay());
		}
	}

	private boolean bloqueLibre(DiaClase dia, HoraClase horaComienzo, HoraClase horaFin) {
		boolean isOK = false;
		int numdia = dia.ordinal();
		int hc = horaComienzo.ordinal();
		int hf = horaFin.ordinal();
		int ht = HoraClase.values().length;
		if (hc >= 0 && hc < ht) {
			if (hf > hc && hf < ht) {
				int h = hc;
				while (h <= hf && horario[numdia][h] == null) {
					h++;
				}
				isOK = h > hf;
			}
		}
		return isOK;
	}

	public boolean addBloque(DiaClase dia, HoraClase horaComienzo, HoraClase horaFin, String codMat, String profesor) {
		boolean pude = false;
		if (bloqueLibre(dia, horaComienzo, horaFin) && buscarMateria(codMat) != -1 && buscarProfesor(profesor) != -1) {
			for (int hora = horaComienzo.ordinal(); hora <= horaFin.ordinal(); hora++) {
				cargarHora(dia, hora, codMat, profesor);
			}
			pude = true;
		}
		return pude;
	}

	public boolean cargarHora(DiaClase dia, int hora, String codMateria, String profesor) {
		boolean pude = false;
		int numdia = dia.ordinal();
		int h = hora;
		if (horario[numdia][h] == null) {
			horario[numdia][h] = new Hora(codMateria, profesor);
			pude = true;
		}
		return pude;
	}

	// Aquí se procesa la misma información que en la rutina superior
	// pero aplicando *Corte de Control* para mostrar las horas
	// agrupadas por materia.
	public void mostrarHorario() {
		// Mostrar los datos de la matriz
		// recorriéndola por día de la semana.
		// Debe mostrarse en formato, por ejemplo
		// LU-LUNES
		// 1-3 : T3 - Taller 3
		// 4-6 : PO - Programación Orientada a Objetos
		// (usar la mostrarDia para hacer esta parte)
		for (int i = 0; i < 4; i++) {
			System.out.println(DiaClase.values()[i].toDisplay());
			mostrarDia(i);
		}
	}

	public void mostrarDia(int dia) {
		// Debe mostrar por pantalla, dinámicamente,
		// las materias del día, agrupando por materia
		// Ejemplo:
		// 1-3 : T3 - Taller 3
		// 4-6 : PO - Programación Orientada a Objetos
		// Investigar, si no se conoce, técnica de
		// "corte de control"
		int hora = 0;
		for(int i = 0; i < horario[dia].length; i++) {
			System.out.println(horario[dia][i].toString());
		}
	}

	private boolean sonLaMisma(Hora hora1, Hora hora2) {
		// Devuelve true o false comparando los valores
		// de hora1 y hora2.
		boolean iguales = false;
		if (hora1.getCodigo() == hora2.getCodigo() && hora1.getProfesor() == hora2.getProfesor()) {
			iguales = true;
		}
		return iguales;
	}
}