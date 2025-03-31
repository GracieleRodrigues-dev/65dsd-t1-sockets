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

	public int getMatricula() {
		return matricula;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + matricula + ";" + ativo;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public int getClubeId() {
		return clubeId;
	}

	public void setClubeId(int clubeId) {
		this.clubeId = clubeId;
	}
}
