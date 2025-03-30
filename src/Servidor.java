
import java.io.*;
import java.net.*;

import controller.ServidorControlador;

public class Servidor {
	public static void main(String[] args) {
		ServidorControlador servidorControlador = new ServidorControlador();

		try (ServerSocket serverSocket = new ServerSocket(8080)) {
			System.out.println("Servidor iniciado na porta 8080...");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado: " + socket.getInetAddress());

				new Thread(() -> {
					try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

						String mensagem = in.readLine();
						System.out.println("Mensagem recebida: " + mensagem);

						String resposta = servidorControlador.processarMensagem(mensagem);
						out.println(resposta);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}