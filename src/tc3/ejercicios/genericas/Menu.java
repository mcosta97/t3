package tc3.ejercicios.genericas;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static Scanner input = new Scanner(System.in);
	private int maxOpciones;
	private String titulo;
	private ArrayList<Opcion> opciones;
	
	public Menu(String titulo, int tope){
		this.titulo = titulo;
		this.maxOpciones = tope;
		this.opciones = new ArrayList<Opcion>();
	}
	public boolean addOpcion(String descripcion) {
		boolean result = false;
		if (opciones.size() < maxOpciones) {
			//agrego un elemento a las opciones
			opciones.add(new Opcion(descripcion));
			result = true;
		}
		return result;
	}
	public int pedirOpcion(){
		int op;
		mostrar();
		op = input.nextInt();
		while(!opcionValida(op)) {
			System.out.println("Error, la opción debe estar entre 1 y " + opcionSalir());
			mostrar();
			op = input.nextInt();
		}
		return op;
	}
	public int opcionSalir() {
		return opciones.size() + 1;
	}
	private boolean opcionValida(int nro) {
		return (nro > 0 && nro <= opcionSalir());
	}
	private void mostrar() {
		int op; 
		System.out.println(titulo);
		for (op=0; op < opciones.size(); op++) {
			System.out.println((op + 1) + " - " + opciones.get(op).getDescripcion());
		}
		System.out.println((op + 1) + " - Salir");
		System.out.print("Ingrese la opción deseada: ");
	}
}
