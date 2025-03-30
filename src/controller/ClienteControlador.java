package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import utils.Formulario;

public class ClienteControlador {

	private static Scanner scanner = new Scanner(System.in);
	Formulario formulario = new Formulario();

	public void exibirMenuPrincipal() {
		System.out.println("\n=== MENU PRINCIPAL ===");
		System.out.println("1. Inserir");
		System.out.println("2. Atualizar");
		System.out.println("3. Consultar");
		System.out.println("4. Remover");
		System.out.println("5. Listar Todos");
		System.out.println("0. Sair");
		System.out.print("Escolha: ");
	}

	public String selecionarClasse() {
		System.out.println("\nSelecione a classe:");
		System.out.println("1. Pessoa");
		System.out.println("2. Sócio");
		System.out.println("3. Visitante");
		System.out.println("4. Clube");
		System.out.println("0. Voltar");
		System.out.print("Escolha: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		return switch (opcao) {
		case 1 -> "PESSOA";
		case 2 -> "SOCIO";
		case 3 -> "VISITANTE";
		case 4 -> "CLUBE";
		default -> null;
		};
	}

	public String coletarDados(String operacao, String classe) {
		try {
			switch (classe) {
			case "PESSOA":
				return coletarDadosPessoa(operacao);
			case "SOCIO":
				return coletarDadosSocio(operacao);
			case "VISITANTE":
				return coletarDadosVisitante(operacao);
			case "CLUBE":
				return coletarDadosClube(operacao);
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public String coletarDadosPessoa(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return formulario.coletarValor("CPF");
		}

		String cpf = formulario.coletarValor("CPF");
		String nome = formulario.coletarValor("Nome");
		String endereco = formulario.coletarValor("Endereço");

		return cpf + ";" + nome + ";" + endereco;
	}

	public String coletarDadosSocio(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("INSERT") || operacao.equals("GET") || operacao.equals("DELETE")) {
			return formulario.coletarValor("CPF");
		}

		String dadosPessoa = coletarDadosPessoa(operacao);
		String matricula = formulario.coletarValor("Endereço");
		String ativo = formulario.coletarValor("Ativo (true/false)");

		return dadosPessoa + ";" + matricula + ";" + ativo;
	}

	public String coletarDadosVisitante(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}

		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return formulario.coletarValor("CPF");
		}

		String dadosPessoa = coletarDadosPessoa(operacao);
		String codigoAcesso = formulario.coletarValor("Código de Acesso: ");
		String email = formulario.coletarValor("Email: ");
		String acompanhante = formulario.coletarValor("Acompanhante (true/false): ");

		return dadosPessoa + ";" + codigoAcesso + ";" + email + ";" + acompanhante;
	}

	public String coletarDadosClube(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return formulario.coletarValor("ID do Clube: ");
		}

		String nome = formulario.coletarValor("Nome: ");
		String capacidade = formulario.coletarValor("Capacidade: ");

		return nome + ";" + capacidade;
	}

	public void enviarMensagem(String mensagem) {
		try (Socket socket = new Socket("localhost", 8080);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			out.println(mensagem);

			String resposta = in.readLine();

			if (resposta != null) {
				System.out.println("\nResposta do servidor:\n" + resposta);

				String linha;
				while ((linha = in.readLine()) != null) {
					System.out.println(linha);
				}
			}

		} catch (IOException e) {
			System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
		}
	}
}
