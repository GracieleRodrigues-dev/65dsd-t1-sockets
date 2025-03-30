package controller;

import java.util.ArrayList;
import java.util.List;

import model.Visitante;

public class VisitanteControlador {
    private static VisitanteControlador instancia;
    private List<Visitante> visitantes;

    private VisitanteControlador() {
        this.visitantes = new ArrayList<>();
    }

    public static VisitanteControlador getInstancia() {
        if (instancia == null) {
            synchronized (VisitanteControlador.class) {
                if (instancia == null) {
                    instancia = new VisitanteControlador();
                }
            }
        }
        return instancia;
    }

    public Visitante buscarVisitantePorCpf(String cpf) {
        for (Visitante visitante : visitantes) {
            if (visitante.getCpf().equals(cpf)) {
                return visitante;
            }
        }
        return null;
    }

    public String inserirVisitante(String cpf, String nome, String endereco, int codigoAcesso, String email,
            boolean acompanhante) {
        if (buscarVisitantePorCpf(cpf) != null) {
            return "Erro: Visitante com este CPF já existe";
        }
        Visitante visitante = new Visitante(cpf, nome, endereco, codigoAcesso, email, acompanhante);
        visitantes.add(visitante);
        return "Visitante inserido com sucesso";
    }

    public String atualizarVisitante(String cpf, String nome, String endereco, int codigoAcesso, String email,
            boolean acompanhante) {
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

    public String obterVisitante(String cpf) {
        Visitante visitante = buscarVisitantePorCpf(cpf);
        return visitante != null ? visitante.toString() : "Visitante não encontrado";
    }

    public String removerVisitante(String cpf) {
        Visitante visitante = buscarVisitantePorCpf(cpf);
        if (visitante != null) {
            visitantes.remove(visitante);
            return "Visitante removido com sucesso";
        }
        return "Visitante não encontrado";
    }

    public String listarVisitantes() {
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
}
