import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma operação:");
            System.out.println("1 - Inserir Pessoa");
            System.out.println("2 - Inserir Sócio");
            System.out.println("3 - Inserir Visitante");
            System.out.println("4 - Atualizar Pessoa");
            System.out.println("5 - Obter Pessoa");
            System.out.println("6 - Remover Pessoa");
            System.out.println("7 - Listar Pessoas");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (opcao == 0) break;

            String mensagem = "";
            switch (opcao) {
                case 1:
                    mensagem = criarMensagemInsertPessoa(scanner);
                    break;
                case 2:
                    mensagem = criarMensagemInsertSocio(scanner);
                    break;
                case 3:
                    mensagem = criarMensagemInsertVisitante(scanner);
                    break;
                case 4:
                    mensagem = criarMensagemUpdatePessoa(scanner);
                    break;
                case 5:
                    mensagem = criarMensagemGetPessoa(scanner);
                    break;
                case 6:
                    mensagem = criarMensagemDeletePessoa(scanner);
                    break;
                case 7:
                    mensagem = "LIST_PESSOAS";
                    break;
                default:
                    System.out.println("Opção inválida");
                    continue;
            }

            try (Socket socket = new Socket("localhost", 12345);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(mensagem);
                String resposta = in.readLine();
                System.out.println("Resposta do servidor: " + resposta);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    private static String criarMensagemInsertPessoa(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        return "INSERT_PESSOA;" + cpf + ";" + nome + ";" + endereco;
    }

    private static String criarMensagemInsertSocio(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Ativo (true/false): ");
        boolean ativo = scanner.nextBoolean();
        scanner.nextLine(); // Consumir a nova linha
        return "INSERT_SOCIO;" + cpf + ";" + nome + ";" + endereco + ";" + matricula + ";" + ativo;
    }

    private static String criarMensagemInsertVisitante(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Código de Acesso: ");
        int codigoAcesso = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Acompanhante (true/false): ");
        boolean acompanhante = scanner.nextBoolean();
        scanner.nextLine(); // Consumir a nova linha
        return "INSERT_VISITANTE;" + cpf + ";" + nome + ";" + endereco + ";" + codigoAcesso + ";" + email + ";" + acompanhante;
    }

    private static String criarMensagemUpdatePessoa(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        return "UPDATE_PESSOA;" + cpf + ";" + nome + ";" + endereco;
    }

    private static String criarMensagemGetPessoa(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        return "GET_PESSOA;" + cpf;
    }

    private static String criarMensagemDeletePessoa(Scanner scanner) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        return "DELETE_PESSOA;" + cpf;
    }
}