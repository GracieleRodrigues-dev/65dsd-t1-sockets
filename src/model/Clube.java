package model;

import java.util.ArrayList;
import java.util.List;

public class Clube {
	private static int proximoId = 1;
	private int id;
	private String nome;
	private int capacidade;

	public Clube(String nome, int capacidade) {
		this.id = proximoId++;
		this.nome = nome;
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return nome + ";" + capacidade;
	}

	public int getId() {
		return id;
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
