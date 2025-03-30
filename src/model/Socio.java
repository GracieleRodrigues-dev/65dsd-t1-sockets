package model;

public class Socio extends Pessoa {
	private int matricula;
	private boolean ativo;

	public Socio(String cpf, String nome, String endereco, int matricula, boolean ativo) {
		super(cpf, nome, endereco);
		this.matricula = matricula;
		this.ativo = ativo;
	}

	// Getters e Setters
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
}
