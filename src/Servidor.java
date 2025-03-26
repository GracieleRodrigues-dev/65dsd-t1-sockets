import model.Clube;
import model.Pessoa;
import model.Socio;
import model.Visitante;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static List<Socio> socios = new ArrayList<>();
    private static List<Visitante> visitantes = new ArrayList<>();
    private static List<Clube> clubes = new ArrayList<>();

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
        String classe = partes[1];

        try {
            switch (operacao + ";" + classe) {
                // Operações para Pessoa
                case "INSERT;PESSOA":
                    return inserirPessoa(partes[2], partes[3], partes[4]);
                case "UPDATE;PESSOA":
                    return atualizarPessoa(partes[2], partes[3], partes[4]);
                case "GET;PESSOA":
                    return obterPessoa(partes[2]);
                case "DELETE;PESSOA":
                    return removerPessoa(partes[2]);
                case "LIST;PESSOA":
                    return listarPessoas();

                // Operações para Socio
                case "INSERT;SOCIO":
                    return inserirSocio(partes[2], partes[3], partes[4], Integer.parseInt(partes[5]), Boolean.parseBoolean(partes[6]));
                case "UPDATE;SOCIO":
                    return atualizarSocio(partes[2], partes[3], partes[4], Integer.parseInt(partes[5]), Boolean.parseBoolean(partes[6]));
                case "GET;SOCIO":
                    return obterSocio(partes[2]);
                case "DELETE;SOCIO":
                    return removerSocio(partes[2]);
                case "LIST;SOCIO":
                    return listarSocios();

                // Operações para Visitante
                case "INSERT;VISITANTE":
                    return inserirVisitante(partes[2], partes[3], partes[4], Integer.parseInt(partes[5]), partes[6], Boolean.parseBoolean(partes[7]));
                case "UPDATE;VISITANTE":
                    return atualizarVisitante(partes[2], partes[3], partes[4], Integer.parseInt(partes[5]), partes[6], Boolean.parseBoolean(partes[7]));
                case "GET;VISITANTE":
                    return obterVisitante(partes[2]);
                case "DELETE;VISITANTE":
                    return removerVisitante(partes[2]);
                case "LIST;VISITANTE":
                    return listarVisitantes();

                // Operações para Clube
                case "INSERT;CLUBE":
                    return inserirClube(partes[2], Integer.parseInt(partes[3]));
                case "UPDATE;CLUBE":
                    return atualizarClube(Integer.parseInt(partes[2]), partes[3],Integer.parseInt(partes[4]));
                case "GET;CLUBE":
                    return obterClube(Integer.parseInt(partes[2]));
                case "DELETE;CLUBE":
                    return removerClube(Integer.parseInt(partes[2]));
                case "LIST;CLUBE":
                    return listarClubes();

                default:
                    return "Operação inválida";
            }
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    // Métodos para Pessoa
    private static String inserirPessoa(String cpf, String nome, String endereco) {
        if (buscarPessoaPorCpf(cpf) != null) {
            return "Erro: Pessoa com este CPF já existe";
        }
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        pessoas.add(pessoa);
        return "Pessoa inserida com sucesso";
    }

    private static String atualizarPessoa(String cpf, String nome, String endereco) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        if (pessoa != null) {
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);
            return "Pessoa atualizada com sucesso";
        }
        return "Pessoa não encontrada";
    }

    private static String obterPessoa(String cpf) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        return pessoa != null ? pessoa.toString() : "Pessoa não encontrada";
    }

    private static String removerPessoa(String cpf) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            return "Pessoa removida com sucesso";
        }
        return "Pessoa não encontrada";
    }

    private static String listarPessoas() {
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

    // Métodos para Socio
    private static String inserirSocio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
        if (buscarSocioPorCpf(cpf) != null) {
            return "Erro: Sócio com este CPF já existe";
        }
        Socio socio = new Socio(cpf, nome, endereco, matricula, ativo);
        socios.add(socio);
        return "Sócio inserido com sucesso";
    }

    private static String atualizarSocio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
        Socio socio = buscarSocioPorCpf(cpf);
        if (socio != null) {
            socio.setNome(nome);
            socio.setEndereco(endereco);
            socio.setMatricula(matricula);
            socio.setAtivo(ativo);
            return "Sócio atualizado com sucesso";
        }
        return "Sócio não encontrado";
    }

    private static String obterSocio(String cpf) {
        Socio socio = buscarSocioPorCpf(cpf);
        return socio != null ? socio.toString() : "Sócio não encontrado";
    }

    private static String removerSocio(String cpf) {
        Socio socio = buscarSocioPorCpf(cpf);
        if (socio != null) {
            socios.remove(socio);
            return "Sócio removido com sucesso";
        }
        return "Sócio não encontrado";
    }

    private static String listarSocios() {
        if (socios.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(socios.size()).append("\n");
        for (Socio socio : socios) {
            sb.append(socio.toString()).append("\n");
        }
        return sb.toString();
    }

    // Métodos para Visitante
    private static String inserirVisitante(String cpf, String nome, String endereco, int codigoAcesso, String email, boolean acompanhante) {
        if (buscarVisitantePorCpf(cpf) != null) {
            return "Erro: Visitante com este CPF já existe";
        }
        Visitante visitante = new Visitante(cpf, nome, endereco, codigoAcesso, email, acompanhante);
        visitantes.add(visitante);
        return "Visitante inserido com sucesso";
    }

    private static String atualizarVisitante(String cpf, String nome, String endereco, int codigoAcesso, String email, boolean acompanhante) {
        Visitante visitante = buscarVisitantePorCpf(cpf);
        if (visitante != null) {
            visitante.setNome(nome);
            visitante.setEndereco(endereco);
            visitante.setCodigoAcesso(codigoAcesso);
            visitante.setEmail(email);
            visitante.setAcompanhante(acompanhante);
            return "Visitante atualizado com sucesso";
        }
        return "Visitante não encontrado";
    }

    private static String obterVisitante(String cpf) {
        Visitante visitante = buscarVisitantePorCpf(cpf);
        return visitante != null ? visitante.toString() : "Visitante não encontrado";
    }

    private static String removerVisitante(String cpf) {
        Visitante visitante = buscarVisitantePorCpf(cpf);
        if (visitante != null) {
            visitantes.remove(visitante);
            return "Visitante removido com sucesso";
        }
        return "Visitante não encontrado";
    }

    private static String listarVisitantes() {
        if (visitantes.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(visitantes.size()).append("\n");
        for (Visitante visitante : visitantes) {
            sb.append(visitante.toString()).append("\n");
        }
        return sb.toString();
    }

    // Métodos para Clube
    private static String inserirClube(String nome, int capacidade) {
        Clube clube = new Clube(nome,capacidade);
        clubes.add(clube);
        return "Clube inserido com sucesso";
    }

    private static String atualizarClube(int id, String nome, int capacidade) {
        Clube clube = buscarClubePorId(id);
        if (clube != null) {
            clube.setNome(nome);
            clube.setCapacidade(capacidade);
            return "Clube atualizado com sucesso";
        }
        return "Clube não encontrado";
    }

    private static String obterClube(int id) {
        Clube clube = buscarClubePorId(id);
        return clube != null ? clube.toString() : "Clube não encontrado";
    }

    private static String removerClube(int id) {
        Clube clube = buscarClubePorId(id);
        if (clube != null) {
            clubes.remove(clube);
            return "Clube removido com sucesso";
        }
        return "Clube não encontrado";
    }

    private static String listarClubes() {
        if (clubes.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(clubes.size()).append("\n");
        for (Clube clube : clubes) {
            sb.append(clube.toString()).append("\n");
        }
        return sb.toString();
    }

    // Métodos auxiliares de busca
    private static Pessoa buscarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    private static Socio buscarSocioPorCpf(String cpf) {
        for (Socio socio : socios) {
            if (socio.getCpf().equals(cpf)) {
                return socio;
            }
        }
        return null;
    }

    private static Visitante buscarVisitantePorCpf(String cpf) {
        for (Visitante visitante : visitantes) {
            if (visitante.getCpf().equals(cpf)) {
                return visitante;
            }
        }
        return null;
    }

    private static Clube buscarClubePorId(int id) {
        for (Clube clube : clubes) {
            if (clube.getId() == id) {
                return clube;
            }
        }
        return null;
    }
}