
import java.util.Scanner;

import controller.ClienteControlador;
import utils.Formulario;

public class Cliente {

	private static Scanner scanner = new Scanner(System.in);
	Formulario formulario = new Formulario();

	public static void main(String[] args) {
		ClienteControlador clienteControlador = new ClienteControlador();

		while (true) {
			clienteControlador.exibirMenuPrincipal();
			int opcao = scanner.nextInt();
			scanner.nextLine();

			if (opcao == 0) {
				break;
			}

			String operacao = switch (opcao) {
			case 1 -> "INSERT";
			case 2 -> "UPDATE";
			case 3 -> "GET";
			case 4 -> "DELETE";
			case 5 -> "LIST";
			default -> null;
			};

			if (operacao == null) {
				continue;
			}

			String classe = clienteControlador.selecionarClasse();
			if (classe == null) {
				continue;
			}

			String dados = clienteControlador.coletarDados(operacao, classe);
			if (dados == null) {
				continue;
			}

			clienteControlador.enviarMensagem(operacao + ";" + classe + ";" + dados);
		}
		scanner.close();
	}

}
