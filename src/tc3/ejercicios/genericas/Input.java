package tc3.ejercicios.genericas;

import java.util.Scanner;

public class Input {

	private static Scanner input = new Scanner(System.in);

	public static int pedirEntero(String mensaje) {
		mostrarMensaje(mensaje);
		return Integer.parseInt(input.nextLine());
	}

	public static int pedirNumero(String mensaje, int min) {
		int n = pedirEntero(mensaje);
		while (n < min) {
			System.out.println("El valor debe ser menor o igual a " + min);
			n = pedirEntero(mensaje);
		}
		return n;
	}

	public static int pedirNumero(String mensaje, int min, int max) {
		int n = pedirEntero(mensaje + " (" + min + " a " + max + ")");
		while (n < min || n > max) {
			System.out.println("El valor debe estar entre " + min + " y " + max);
			n = pedirEntero(mensaje + " (" + min + " a " + max + ")");
		}
		return n;
	}

	public static int pedirNumero(String mensaje, int min, int max, int valorCorte) {
		int n = pedirEntero(mensaje + " (" + min + " a " + max + ", " + valorCorte + " para terminar)");
		while (n != valorCorte && (n < min || n > max)) {
			System.out.println("El valor debe estar entre " + min + " y " + max + " (Fin = " + valorCorte + ")");
			n = pedirEntero(mensaje + " (" + min + " a " + max + ", " + valorCorte + " para terminar)");
		}
		return n;
	}

	public static double pedirReal(String mensaje) {
		mostrarMensaje(mensaje);
		return Double.parseDouble(input.nextLine());
	}

	public static double pedirNumero(String mensaje, double min) {
		double n = pedirReal(mensaje);
		while (n < min) {
			System.out.println("El valor debe ser >= a " + min);
			n = pedirReal(mensaje);
		}
		return n;
	}

	public static double pedirNumero(String mensaje, double min, double max) {
		double n = pedirReal(mensaje);
		while (n < min || n > max) {
			System.out.println("El valor debe estar entre " + min + " y " + max);
			n = pedirReal(mensaje);
		}
		return n;
	}

	public static double pedirNumero(String mensaje, double min, double max, double valorCorte) {
		double n = pedirReal(mensaje);
		while (n != valorCorte && (n < min || n > max)) {
			System.out.println("El valor debe estar entre " + min + " y " + max + " (Fin = " + valorCorte + ")");
			n = pedirReal(mensaje);
		}
		return n;
	}

	public static String pedirString(String mensaje, boolean noVacio) {
		mostrarMensaje(mensaje);
		String s = input.nextLine();
		while (noVacio && s.equals("")) {
			System.out.println("El valor no puede ser \"\"");
			s = input.nextLine();
		}
		return s;
	}

	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

}
