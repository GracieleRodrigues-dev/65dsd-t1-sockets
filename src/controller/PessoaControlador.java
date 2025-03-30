package controller;

import java.util.ArrayList;
import java.util.List;

import model.Pessoa;

public class PessoaControlador {
	private List<Pessoa> pessoas = new ArrayList<>();

	private Pessoa buscarPessoaPorCpf(String cpf) {
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
