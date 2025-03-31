package controller;

import java.util.ArrayList;
import java.util.List;

import model.Pessoa;
import model.Socio;
import model.Visitante;

public class VisitanteControlador {
	private List<Visitante> visitantes;

	PessoaControlador pessoaControlador;

	public VisitanteControlador() {
		this.visitantes = new ArrayList<>();
	}

	public PessoaControlador getPessoaControalador() {
		return pessoaControlador;
	}

	public void setPessoaControalador(PessoaControlador pessoaControlador) {
		this.pessoaControlador = pessoaControlador;
	}

	public Visitante buscarVisitantePorCpf(String cpf) {
		for (Visitante visitante : visitantes) {
			if (visitante.getCpf().equals(cpf)) {
				return visitante;
			}
		}
		return null;
	}

	public String inserirVisitante(String cpf, int codigoAcesso, String email, boolean acompanhante) {
		if (buscarVisitantePorCpf(cpf) != null) {
			return "Erro: Visitante com este CPF já existe";
		}

		Pessoa pessoa = pessoaControlador.buscarPessoaPorCpf(cpf);
		if (pessoa == null) {
			return "Erro: Pessoa ainda não registrada!";
		}

		String nome = pessoa.getNome();
		String endereco = pessoa.getEndereco();

		Visitante visitante = new Visitante(cpf, nome, endereco, codigoAcesso, email, acompanhante);
		visitantes.add(visitante);

		return "Visitante inserido com sucesso";
	}

	public String atualizarVisitante(String cpf, int codigoAcesso, String email, boolean acompanhante) {
		Visitante visitante = buscarVisitantePorCpf(cpf);
		if (visitante != null) {
			visitante.setCodigoAcesso(codigoAcesso);
			visitante.setEmail(email);
			visitante.setAcompanhante(acompanhante);
			return "Visitante atualizado com sucesso";
		}
		return "Visitante não encontrado";
	}

	public boolean sincronizarPessoa(String cpf, String nome, String endereco) {
		Visitante visitante = buscarVisitantePorCpf(cpf);
		if (visitante != null) {
			visitante.setNome(nome);
			visitante.setEndereco(endereco);
			return true;
		}
		return false;
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
