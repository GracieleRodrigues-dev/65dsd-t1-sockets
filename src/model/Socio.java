package model;

public class Socio extends Pessoa {
	private int matricula;
	private boolean ativo;
	private int clubeId;

	public Socio(String cpf, String nome, String endereco, int matricula, boolean ativo, int clubeId) {
		super(cpf, nome, endereco);
		this.matricula = matricula;
		this.ativo = ativo;
		this.clubeId = clubeId;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + matricula + ";" + ativo + ";" + clubeId;
	}

	public int getClubeId() {
		return clubeId;
	}

	public void setClubeId(int clubeId) {
		this.clubeId = clubeId;
	}
}
