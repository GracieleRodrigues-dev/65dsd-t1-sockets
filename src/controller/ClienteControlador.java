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
	Formulario form = new Formulario();

	private String operacao;
	private String classe;
	private String dados;

	public String selecionarOperacao() {
		System.out.println("\n=== MENU PRINCIPAL ===");
		System.out.println("1. Inserir");
		System.out.println("2. Atualizar");
		System.out.println("3. Consultar");
		System.out.println("4. Remover");
		System.out.println("5. Listar Todos");
		System.out.println("0. Sair");
		System.out.print("Escolha: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		String operacaoSelecionada = switch (opcao) {
		case 1 -> "INSERT";
		case 2 -> "UPDATE";
		case 3 -> "GET";
		case 4 -> "DELETE";
		case 5 -> "LIST";
		default -> null;
		};

		this.operacao = operacaoSelecionada;
		return operacaoSelecionada;
	}

	public void selecionarClasse() {
		System.out.println("\nSelecione a classe:");
		System.out.println("1. Pessoa");
		System.out.println("2. Sócio");
		System.out.println("3. Visitante");
		System.out.println("4. Clube");
		System.out.println("0. Voltar");
		System.out.print("Escolha: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		String classeSelecionada = switch (opcao) {
		case 1 -> "PESSOA";
		case 2 -> "SOCIO";
		case 3 -> "VISITANTE";
		case 4 -> "CLUBE";
		default -> null;
		};

		this.classe = classeSelecionada;
	}

	public void coletarDados() {
		try {
			String dadosColetados = switch (classe) {
			case "PESSOA" -> coletarDadosPessoa(operacao);
			case "SOCIO" -> coletarDadosSocio(operacao);
			case "VISITANTE" -> coletarDadosVisitante(operacao);
			case "CLUBE" -> coletarDadosClube(operacao);
			default -> null;
			};

			this.dados = dadosColetados;
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public String coletarDadosPessoa(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return form.coletarCPF();
		}

		String cpf = form.coletarCPF();
		String nome = form.coletarNome();
		String endereco = form.coletarEndereco();

		return cpf + ";" + nome + ";" + endereco;
	}

	public String coletarDadosSocio(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("INSERT") || operacao.equals("GET") || operacao.equals("DELETE")) {
			return form.coletarCPF();
		}

		String cpf = form.coletarCPF();
		String ativo = form.coletarStatus();

		return cpf + ";" + ativo;
	}

	public String coletarDadosVisitante(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}

		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return form.coletarCPF();
		}

		String cpf = form.coletarCPF();
		String email = form.coletarEmail();
		String acompanhante = form.coletarAcompanhante();
		String codigoAcesso = form.coletarCodigoDeAcesso();

		return cpf + ";" + codigoAcesso + ";" + email + ";" + acompanhante;
	}

	public String coletarDadosClube(String operacao) {
		if (operacao.equals("LIST")) {
			return "";
		}
		if (operacao.equals("GET") || operacao.equals("DELETE")) {
			return form.coletarClubeId();
		}

		String nome = form.coletarNome();
		String capacidade = form.coletarCapacidade();

		return nome + ";" + capacidade;
	}

	public void enviarMensagem() {
		try (Socket socket = new Socket("localhost", 8080);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			out.println(operacao + ";" + classe + ";" + dados);

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
