package utils;

import java.util.Scanner;

public class Formulario {

	private static Scanner scanner = new Scanner(System.in);

	public String coletarValor(String campo) {
		System.out.print(campo + " :");
		String valor = scanner.nextLine();
		return valor;
	}

	public String coletarCPF() {
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();
		return cpf;
	}

	public String coletarNome() {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		return nome;
	}

	public String coletarEndereco() {
		System.out.print("Endereço: ");
		String endereco = scanner.nextLine();
		return endereco;
	}

	public String coletarStatus() {
		System.out.print("Ativo (true/false): ");
		String status = scanner.nextLine();
		return status;
	}

	public String coletarCodigoDeAcesso() {
		System.out.print("Código de Acesso: ");
		String codigoDeAcesso = scanner.nextLine();
		return codigoDeAcesso;
	}

	public String coletarEmail() {
		System.out.print("Email: ");
		String email = scanner.nextLine();
		return email;
	}

	public String coletarAcompanhante() {
		System.out.print("Acompanhante (true/false): ");
		String acompanhante = scanner.nextLine();
		return acompanhante;
	}

	public String coletarClubeId() {
		System.out.print("ID do Clube: ");
		String clubeId = scanner.nextLine();
		return clubeId;
	}

	public String coletarCapacidade() {
		System.out.print("Capacidade: ");
		String capacidade = scanner.nextLine();
		return capacidade;
	}

}
