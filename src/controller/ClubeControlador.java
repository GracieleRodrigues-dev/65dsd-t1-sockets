package controller;

import java.util.ArrayList;
import java.util.List;

import model.Clube;

public class ClubeControlador {

	private List<Clube> clubes;
	private SocioControlador socioControlador;

	public ClubeControlador() {
		this.clubes = new ArrayList<>();

		clubes.add(new Clube("Batatas", 100));
	}

	public SocioControlador getSocioControlador() {
		return socioControlador;
	}

	public void setSocioControlador(SocioControlador socioControlador) {
		this.socioControlador = socioControlador;
	}

	public String inserirClube(String nome, int capacidade) {
		Clube clube = new Clube(nome, capacidade);
		clubes.add(clube);
		return "Clube inserido com sucesso";
	}

	public String atualizarClube(int id, String nome, int capacidade) {
		Clube clube = buscarClubePorId(id);
		if (clube != null) {
			clube.setNome(nome);
			clube.setCapacidade(capacidade);
			return "Clube atualizado com sucesso";
		}
		return "Clube não encontrado";
	}

	public String obterClube(int id) {
		Clube clube = buscarClubePorId(id);
		return clube != null ? clube.toString() : "Clube não encontrado";
	}

	public String removerClube(int id) {
		Clube clube = buscarClubePorId(id);

		if (clube != null) {
			clubes.remove(clube);

			StringBuilder sb = new StringBuilder();
			sb.append("Clube removido com sucesso!");
			sb.append("\n").append(socioControlador.removerSociosPorClube(id));

			return sb.toString();
		}
		return "Clube não encontrado";
	}

	public String listarClubes() {
		if (clubes.isEmpty()) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(clubes.size()).append("\n");
		for (Clube clube : clubes) {
			sb.append(clube.toString()).append("\n");
		}
		return sb.toString();
	}

	public Clube buscarClubePorId(int id) {
		for (Clube clube : clubes) {
			if (clube.getId() == id) {
				return clube;
			}
		}
		return null;
	}
}
