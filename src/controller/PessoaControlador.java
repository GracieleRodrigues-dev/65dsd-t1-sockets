package controller;

import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaControlador {
    private static PessoaControlador instancia;
    private List<Pessoa> pessoas = new ArrayList<>();


    private PessoaControlador() {
        pessoas.add(new Pessoa("99999999", "Bruno", "Rua XXX"));
        pessoas.add(new Pessoa("88888888", "Ana", "Rua YYY"));
        pessoas.add(new Pessoa("77777777", "Maria", "Rua ZZZ"));
    }

    public static PessoaControlador getInstancia() {
        if (instancia == null) {
            instancia = new PessoaControlador();
        }
        return instancia;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa buscarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    public String inserirPessoa(String cpf, String nome, String endereco) {
        if (buscarPessoaPorCpf(cpf) != null) {
            return "Erro: Pessoa com este CPF já existe";
        }
        Pessoa pessoa = new Pessoa(cpf, nome, endereco);
        pessoas.add(pessoa);
        return "Pessoa inserida com sucesso";
    }

    public String atualizarPessoa(String cpf, String nome, String endereco) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        if (pessoa != null) {
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);
            return "Pessoa atualizada com sucesso";
        }
        return "Pessoa não encontrada";
    }

    public String obterPessoa(String cpf) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        return pessoa != null ? pessoa.toString() : "Pessoa não encontrada";
    }

    public String removerPessoa(String cpf) {
        Pessoa pessoa = buscarPessoaPorCpf(cpf);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            return "Pessoa removida com sucesso";
        }
        return "Pessoa não encontrada";
    }

    public String listarPessoas() {
        if (pessoas == null || pessoas.isEmpty()) {
            return "00";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", pessoas.size())).append("\n");

        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                sb.append(pessoa.toString()).append("\n");
                System.out.println("DEBUG: Adicionando -> " + pessoa.toString());
            }
        }

        return sb.toString().trim();
    }
}
