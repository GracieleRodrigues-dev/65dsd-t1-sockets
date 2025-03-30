
import java.util.Scanner;

import controller.ClienteControlador;
import utils.Formulario;

public class Cliente {

	private static Scanner scanner = new Scanner(System.in);
	Formulario formulario = new Formulario();

	public static void main(String[] args) {
		ClienteControlador clienteControlador = new ClienteControlador();

		while (true) {
			String operacao = clienteControlador.selecionarOperacao();
			if (operacao == null) {
				break;
			}
			clienteControlador.selecionarClasse();
			clienteControlador.coletarDados();
			clienteControlador.enviarMensagem();
		}
		scanner.close();
	}

}
