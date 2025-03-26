import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 0) break;

            String operacao = switch (opcao) {
                case 1 -> "INSERT";
                case 2 -> "UPDATE";
                case 3 -> "GET";
                case 4 -> "DELETE";
                case 5 -> "LIST";
                default -> null;
            };

            if (operacao == null) continue;

            String classe = selecionarClasse();
            if (classe == null) continue;

            String dados = coletarDados(operacao, classe);
            if (dados == null) continue;

            enviarMensagem(operacao + ";" + classe + ";" + dados);
        }
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Inserir");
        System.out.println("2. Atualizar");
        System.out.println("3. Consultar");
        System.out.println("4. Remover");
        System.out.println("5. Listar Todos");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static String selecionarClasse() {
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

    private static String coletarDados(String operacao, String classe) {
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

    private static String coletarDadosPessoa(String operacao) {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if (operacao.equals("GET") || operacao.equals("DELETE")) {
            return cpf;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        return cpf + ";" + nome + ";" + endereco;
    }

    private static String coletarDadosSocio(String operacao) {
        String dadosPessoa = coletarDadosPessoa(operacao);
        if (operacao.equals("GET") || operacao.equals("DELETE")) {
            return dadosPessoa;
        }

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Ativo (true/false): ");
        String ativo = scanner.nextLine();

        return dadosPessoa + ";" + matricula + ";" + ativo;
    }

    private static String coletarDadosVisitante(String operacao) {
        String dadosPessoa = coletarDadosPessoa(operacao);
        if (operacao.equals("GET") || operacao.equals("DELETE")) {
            return dadosPessoa;
        }

        System.out.print("Código de Acesso: ");
        String codigoAcesso = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Acompanhante (true/false): ");
        String acompanhante = scanner.nextLine();

        return dadosPessoa + ";" + codigoAcesso + ";" + email + ";" + acompanhante;
    }

    private static String coletarDadosClube(String operacao) {
        System.out.print("ID do Clube: ");
        String id = scanner.nextLine();

        if (operacao.equals("GET") || operacao.equals("DELETE")) {
            return id;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        return id + ";" + nome + ";" + endereco;
    }

    private static void enviarMensagem(String mensagem) {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(mensagem);
            String resposta = in.readLine();

            // Tratamento especial para respostas LIST
            if (resposta != null && resposta.contains("\n")) {
                String[] partes = resposta.split("\n", 2);
                int quantidade = Integer.parseInt(partes[0]);
                System.out.println("\nTotal de registros: " + quantidade);
                if (quantidade > 0) {
                    System.out.println("\nRegistros:");
                    System.out.println(partes[1]);
                }
            } else {
                System.out.println("\nResposta do servidor: " + resposta);
            }

        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
}