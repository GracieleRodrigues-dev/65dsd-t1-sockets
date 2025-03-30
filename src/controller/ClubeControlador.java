package controller;

import java.util.ArrayList;
import java.util.List;

import model.Clube;

public class ClubeControlador {
	private List<Clube> clubes = new ArrayList<>();

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
			return "Clube removido com sucesso";
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
