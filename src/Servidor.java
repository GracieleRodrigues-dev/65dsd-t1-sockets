
import java.io.*;
import java.net.*;

import controller.ServidorControlador;

public class Servidor {
	public static void main(String[] args) {
		ServidorControlador servidorControlador = new ServidorControlador();

		try (ServerSocket serverSocket = new ServerSocket(8080)) {
			System.out.println("Servidor iniciado na porta 8080...");

			while (true) {
				try {
					Socket socket = serverSocket.accept();
					String clientAddress = socket.getInetAddress().getHostAddress();
					int clientPort = socket.getPort();
					System.out.println("Cliente conectado: " + clientAddress + ":" + clientPort);

					new Thread(() -> {
						try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

							String mensagem = in.readLine();
							System.out.println("Mensagem recebida: " + mensagem);

							String resposta = servidorControlador.processarMensagem(mensagem);
							out.println(resposta);

						} catch (IOException e) {
							System.out.println("Erro na comunicação com o cliente " + clientAddress + ":" + clientPort + " - " + e.getMessage());
						} finally {
							try {
								socket.close();
								System.out.println("Conexão encerrada - Cliente: " + clientAddress + ":" + clientPort);
							} catch (IOException e) {
								System.out.println("Erro ao fechar socket do cliente " + clientAddress + ":" + clientPort);
							}
						}
					}).start();
				}catch (IOException e) {
					System.out.println("Erro ao aceitar conexão do cliente: " + e.getMessage());
				}
			}
		}catch (IOException e) {
			System.out.println("Erro crítico no servidor: " + e.getMessage());
			System.out.println("Servidor encerrado.");
		}
	}
}