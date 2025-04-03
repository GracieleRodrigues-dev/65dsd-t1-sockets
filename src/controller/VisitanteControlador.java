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

		visitantes.add(new Visitante("321372187931", "Jonas", "Rua 2", 3213213, "jonas@email.com", false, 1));
		visitantes.add(new Visitante("382938712893", "Carla", "Rua 3", 3123124, "carla@email.com", true, 1));
	}

	public PessoaControlador getPessoaControalador() {
		return pessoaControlador;
	}

	public void setPessoaControlador(PessoaControlador pessoaControlador) {
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

	public String inserirVisitante(String cpf, int codigoAcesso, String email, boolean acompanhante, int clubeId) {
		if (buscarVisitantePorCpf(cpf) != null) {
			return "Erro: Visitante com este CPF já existe";
		}

		Pessoa pessoa = pessoaControlador.buscarPessoaPorCpf(cpf);
		if (pessoa == null) {
			return "Erro: Pessoa ainda não registrada!";
		}

		String nome = pessoa.getNome();
		String endereco = pessoa.getEndereco();

		Visitante visitante = new Visitante(cpf, nome, endereco, codigoAcesso, email, acompanhante, clubeId);
		visitantes.add(visitante);

		return "Visitante inserido com sucesso";
	}

	public String atualizarVisitante(String cpf, int codigoAcesso, String email, boolean acompanhante, int clubeId) {
		Visitante visitante = buscarVisitantePorCpf(cpf);
		if (visitante != null) {
			visitante.setCodigoAcesso(codigoAcesso);
			visitante.setEmail(email);
			visitante.setAcompanhante(acompanhante);
			visitante.setClubeId(clubeId);

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
		isVisitantesEmpty();
		Visitante visitante = buscarVisitantePorCpf(cpf);
		return visitante != null ? visitante.toString() : "Visitante não encontrado";
	}

	public String removerVisitante(String cpf) {
		isVisitantesEmpty();
		Visitante visitante = buscarVisitantePorCpf(cpf);
		if (visitante != null) {
			visitantes.remove(visitante);
			return "Visitante removido com sucesso";
		}
		return "Visitante não encontrado";
	}

	public String removerVisitantesPorClube(int clubeId) {
		if (visitantes.isEmpty()) {
			return "0";
		}

		List<String> visitantesRemovidos = new ArrayList<>();

		for (int i = visitantes.size() - 1; i >= 0; i--) {
			Visitante visitante = visitantes.get(i);
			if (visitante.getClubeId() == clubeId) {
				visitantesRemovidos.add(visitante.toString());
				visitantes.remove(i);
			}
		}

		if (visitantesRemovidos.isEmpty()) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Visitantes removidos: ");

		for (String removido : visitantesRemovidos) {
			sb.append("\n").append("- ").append(removido);
		}

		return sb.toString();
	}

	public String listarVisitantes() {
		if (visitantes.isEmpty()) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Nº de visitantes: ").append(visitantes.size()).append("\n");

		for (Visitante visitante : visitantes) {
			sb.append(visitante.toString()).append("\n");
		}

		return sb.toString();
	}

	public String listarVisitantesPorClube(int clubeId) {
		if (visitantes.isEmpty()) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Nº de visitantes: ").append(visitantes.size()).append("\n");

		for (Visitante visitante : visitantes) {
			if (visitante.getClubeId() == clubeId)
				sb.append("- ").append(visitante.toString()).append("\n");
		}

		return sb.toString();
	}

	public String isVisitantesEmpty() {
		if (visitantes.isEmpty()) {
			return "Sem visitantes cadastrados";
		}
		return "";
	}
}
