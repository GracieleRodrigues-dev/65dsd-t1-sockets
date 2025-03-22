import model.Clube;
import model.Pessoa;
import model.Socio;
import model.Visitante;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static Clube clube = new Clube("Clube dos Devs", 100);

    public static void main(String[] args) {
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

                        String resposta = processarMensagem(mensagem);
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

    private static String processarMensagem(String mensagem) {
        String[] partes = mensagem.split(";");
        String operacao = partes[0];

        switch (operacao) {
            case "INSERT_PESSOA":
                return inserirPessoa(partes[1], partes[2], partes[3]);
            case "INSERT_SOCIO":
                return inserirSocio(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Boolean.parseBoolean(partes[5]));
            case "INSERT_VISITANTE":
                return inserirVisitante(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), partes[5], Boolean.parseBoolean(partes[6]));
            case "UPDATE_PESSOA":
                return atualizarPessoa(partes[1], partes[2], partes[3]);
            case "GET_PESSOA":
                return obterPessoa(partes[1]);
            case "DELETE_PESSOA":
                return removerPessoa(partes[1]);
            case "LIST_PESSOAS":
                return listarPessoas();
            default:
                return "Operação inválida";
        }
    }

    private static String inserirPessoa(String cpf, String nome, String endereco) {
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        clube.adicionarPessoa(pessoa);
        return "Pessoa inserida com sucesso";
    }

    private static String inserirSocio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
        Socio socio = new Socio(cpf, nome, endereco, matricula, ativo);
        clube.adicionarPessoa(socio);
        return "Sócio inserido com sucesso";
    }

    private static String inserirVisitante(String cpf, String nome, String endereco, int codigoAcesso, String email, boolean acompanhante) {
        Visitante visitante = new Visitante(cpf, nome, endereco, codigoAcesso, email, acompanhante);
        clube.adicionarPessoa(visitante);
        return "Visitante inserido com sucesso";
    }

    private static String atualizarPessoa(String cpf, String nome, String endereco) {
        Pessoa pessoa = clube.buscarPessoa(cpf);
        if (pessoa != null) {
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);
            return "Pessoa atualizada com sucesso";
        }
        return "Pessoa não encontrada";
    }

    private static String obterPessoa(String cpf) {
        Pessoa pessoa = clube.buscarPessoa(cpf);
        if (pessoa != null) {
            return pessoa.toString();
        }
        return "Pessoa não encontrada";
    }

    private static String removerPessoa(String cpf) {
        if (clube.removerPessoa(cpf)) {
            return "Pessoa removida com sucesso";
        }
        return "Pessoa não encontrada";
    }

    private static String listarPessoas() {
        List<Pessoa> pessoas = clube.listarPessoas();
        if (pessoas.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pessoas.size()).append("\n");
        for (Pessoa pessoa : pessoas) {
            sb.append(pessoa.toString()).append("\n");
        }
        return sb.toString();
    }
}