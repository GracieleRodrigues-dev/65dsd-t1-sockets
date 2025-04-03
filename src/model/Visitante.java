package model;

public class Visitante extends Pessoa {
	private int codigoAcesso;
	private String email;
	private boolean acompanhante;
	private int clubeId;

	public Visitante(String cpf, String nome, String endereco, int codigoAcesso, String email, boolean acompanhante,
			int clubeId) {
		super(cpf, nome, endereco);
		this.codigoAcesso = codigoAcesso;
		this.email = email;
		this.acompanhante = acompanhante;
		this.clubeId = clubeId;
	}

	public int getCodigoAcesso() {
		return codigoAcesso;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAcompanhante() {
		return acompanhante;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + codigoAcesso + ";" + email + ";" + acompanhante;
	}

	public void setCodigoAcesso(int codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAcompanhante(boolean acompanhante) {
		this.acompanhante = acompanhante;
	}

	public int getClubeId() {
		return clubeId;
	}

	public void setClubeId(int clubeId) {
		this.clubeId = clubeId;
	}

}
