package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Pessoa;
import model.Socio;

public class SocioControlador {
	private List<Socio> socios;

	private Random random;
	private PessoaControlador pessoaControlador;

	public SocioControlador() {
		this.socios = new ArrayList<>();
		this.random = new Random();
	}

	public PessoaControlador getPessoaControlador() {
		return pessoaControlador;
	}

	public void setPessoaControlador(PessoaControlador pessoaControlador) {
		this.pessoaControlador = pessoaControlador;
	}

	public Socio buscarSocioPorCpf(String cpf) {
		for (Socio socio : socios) {
			if (socio.getCpf().equals(cpf)) {
				return socio;
			}
		}
		return null;
	}

	public String inserirSocio(String cpf, int clubeId) {
		if (buscarSocioPorCpf(cpf) != null) {
			return "Erro: Sócio com este CPF já existe!";
		}

		Pessoa pessoa = pessoaControlador.buscarPessoaPorCpf(cpf);
		if (pessoa == null) {
			return "Erro: Pessoa ainda não registrada!";
		}

		String nome = pessoa.getNome();
		String endereco = pessoa.getEndereco();
		int matricula = 10000 + random.nextInt(90000);
		boolean ativo = true;

		Socio socio = new Socio(cpf, nome, endereco, matricula, ativo, clubeId);
		socios.add(socio);

		return "Sócio inserido com sucesso";
	}

	public String atualizarSocio(String cpf, boolean ativo, int clubeId) {
		Socio socio = buscarSocioPorCpf(cpf);
		if (socio != null) {
			socio.setAtivo(ativo);
			socio.setClubeId(clubeId);
			return "Sócio atualizado com sucesso";
		}
		return "Sócio não encontrado";
	}

	public boolean sincronizarPessoa(String cpf, String nome, String endereco) {
		Socio socio = buscarSocioPorCpf(cpf);
		if (socio != null) {
			socio.setNome(nome);
			socio.setEndereco(endereco);
			return true;
		}
		return false;
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

	public String listarSociosPorClube(int clubeId) {
		if (socios.isEmpty()) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(socios.size()).append("\n");
		for (Socio socio : socios) {
			if (socio.getClubeId() == clubeId)
				sb.append(socio.toString()).append("\n");
		}
		return sb.toString();
	}
}
