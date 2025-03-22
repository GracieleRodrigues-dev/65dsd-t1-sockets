package model;

import java.util.ArrayList;
import java.util.List;

public class Clube {
    private String nome;
    private int capacidade;
    private List<Pessoa> pessoas;

    public Clube(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.pessoas = new ArrayList<>();
    }


    public void adicionarPessoa(Pessoa pessoa) {
        if (pessoas.size() < capacidade) {
            pessoas.add(pessoa);
        } else {
            System.out.println("Capacidade máxima atingida.");
        }
    }

    public Pessoa buscarPessoa(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean removerPessoa(String cpf) {
        Pessoa pessoa = buscarPessoa(cpf);
        if (pessoa != null) {
            pessoas.remove(pessoa);
            return true;
        }
        return false;
    }

    public List<Pessoa> listarPessoas() {
        return pessoas;
    }
}
