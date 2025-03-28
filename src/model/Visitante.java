package model;

public class Visitante extends Pessoa {
    private int codigoAcesso;
    private String email;
    private boolean acompanhante;

    public Visitante(String cpf, String nome, String endereco, int codigoAcesso, String email, boolean acompanhante) {
        super(cpf, nome, endereco);
        this.codigoAcesso = codigoAcesso;
        this.email = email;
        this.acompanhante = acompanhante;
    }

    // Getters e Setters
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
}
