package utils;

import java.util.Scanner;

public class Formulario {

	private static Scanner scanner = new Scanner(System.in);

	public String coletarValor(String campo) {
		System.out.print(campo + " :");
		String valor = scanner.nextLine();
		return valor;
	}

}
