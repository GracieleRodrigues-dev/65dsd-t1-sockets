
import java.util.Scanner;

import controller.ClienteControlador;
import utils.Formulario;

public class Cliente {

	private static Scanner scanner = new Scanner(System.in);
	Formulario formulario = new Formulario();

	public static void main(String[] args) {
		System.out.print("Digite o IP do servidor: ");
		String ip = scanner.nextLine();

		System.out.print("Digite a porta do servidor: ");
		int porta = scanner.nextInt();
		scanner.nextLine();

		ClienteControlador clienteControlador = new ClienteControlador();

		while (true) {
			String operacao = clienteControlador.selecionarOperacao();
			if (operacao == null) {
				break;
			}
			clienteControlador.selecionarClasse();
			clienteControlador.coletarDados();
			clienteControlador.enviarMensagem(ip, porta);
		}
		scanner.close();
	}

}
