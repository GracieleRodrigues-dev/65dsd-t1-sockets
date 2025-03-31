package controller;

import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

public class PessoaControlador {
	private List<Pessoa> pessoas;
	private SocioControlador socioControlador;
	private VisitanteControlador visitanteControlador;

	public PessoaControlador() {
		this.pessoas = new ArrayList<>();

		pessoas.add(new Pessoa("99999999", "Bruno", "Rua XXX"));
		pessoas.add(new Pessoa("88888888", "Ana", "Rua YYY"));
		pessoas.add(new Pessoa("77777777", "Maria", "Rua ZZZ"));
	}

	public SocioControlador getSocioControlador() {
		return socioControlador;
	}

	public void setSocioControlador(SocioControlador socioControlador) {
		this.socioControlador = socioControlador;
	}

	public VisitanteControlador getVisitanteControlador() {
		return visitanteControlador;
	}

	public void setVisitanteControlador(VisitanteControlador visitanteControlador) {
		this.visitanteControlador = visitanteControlador;
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

			String message = "";

			String resultadoRemocaoSocio = socioControlador.removerSocio(cpf);
			String resultadoRemocaoVisitante = visitanteControlador.removerVisitante(cpf);

			if ("Sócio removido com sucesso".equals(resultadoRemocaoSocio)) {
				message += "\nRemovido um registro de sócio relacionado a pessoa";
			}

			if ("Visitante removido com sucesso".equals(resultadoRemocaoVisitante)) {
				message += "\nRemovido um registro de visitante relacionado a pessoa";
			}

			pessoas.remove(pessoa);

			message += "\nPessoa removida com sucesso";
			return message;
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
