package controller;

import java.util.ArrayList;
import java.util.List;

import model.Socio;

public class SocioControlador {
	private List<Socio> socios = new ArrayList<>();

	public Socio buscarSocioPorCpf(String cpf) {
		for (Socio socio : socios) {
			if (socio.getCpf().equals(cpf)) {
				return socio;
			}
		}
		return null;
	}

	public String inserirSocio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
		if (buscarSocioPorCpf(cpf) != null) {
			return "Erro: Sócio com este CPF já existe";
		}
		Socio socio = new Socio(cpf, nome, endereco, matricula, ativo);
		socios.add(socio);
		return "Sócio inserido com sucesso";
	}

	public String atualizarSocio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
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

	public String obterSocio(String cpf) {
		Socio socio = buscarSocioPorCpf(cpf);
		return socio != null ? socio.toString() : "Sócio não encontrado";
	}

	public String removerSocio(String cpf) {
		Socio socio = buscarSocioPorCpf(cpf);
		if (socio != null) {
			socios.remove(socio);
			return "Sócio removido com sucesso";
		}
		return "Sócio não encontrado";
	}

	public String listarSocios() {
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
}
