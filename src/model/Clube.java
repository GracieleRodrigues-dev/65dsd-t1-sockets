package model;

import java.util.ArrayList;
import java.util.List;

public class Clube {
	private static int proximoId = 1;
	private int id;
	private String nome;
	private int capacidade;
	private List<Pessoa> pessoas;

	public Clube(String nome, int capacidade) {
		this.id = proximoId++;
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

	@Override
	public String toString() {
		return nome + ";" + capacidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
