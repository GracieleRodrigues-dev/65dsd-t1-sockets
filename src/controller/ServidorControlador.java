package controller;

public class ServidorControlador {

	SocioControlador socioControlador;
	ClubeControlador clubeControlador;
	PessoaControlador pessoaControlador;
	VisitanteControlador visitanteControlador;

	public ServidorControlador() {
		this.socioControlador = new SocioControlador();
		this.clubeControlador = new ClubeControlador();
		this.pessoaControlador = new PessoaControlador();
		this.visitanteControlador = new VisitanteControlador();

		pessoaControlador.setSocioControlador(socioControlador);
		pessoaControlador.setVisitanteControlador(visitanteControlador);
		socioControlador.setPessoaControlador(pessoaControlador);
		visitanteControlador.setPessoaControlador(pessoaControlador);
		clubeControlador.setSocioControlador(socioControlador);
	}

	public String processarMensagem(String mensagem) {
		String[] partes = mensagem.split(";");
		String operacao = partes[0];
		String classe = partes[1];

		try {
			switch (operacao + ";" + classe) {

			case "INSERT;PESSOA":
				return pessoaControlador.inserirPessoa(partes[2], partes[3], partes[4]);
			case "UPDATE;PESSOA":
				return pessoaControlador.atualizarPessoa(partes[2], partes[3], partes[4]);
			case "GET;PESSOA":
				return pessoaControlador.obterPessoa(partes[2]);
			case "DELETE;PESSOA":
				return pessoaControlador.removerPessoa(partes[2]);
			case "LIST;PESSOA":
				return pessoaControlador.listarPessoas();

			case "INSERT;SOCIO":
				return socioControlador.inserirSocio(partes[2], Integer.parseInt(partes[3]));
			case "UPDATE;SOCIO":
				return socioControlador.atualizarSocio(partes[2], Boolean.parseBoolean(partes[3]), Integer.parseInt(partes[4]));
			case "GET;SOCIO":
				return socioControlador.obterSocio(partes[2]);
			case "DELETE;SOCIO":
				return socioControlador.removerSocio(partes[2]);
			case "LIST;SOCIO":
				return socioControlador.listarSocios();

			case "INSERT;VISITANTE":
				return visitanteControlador.inserirVisitante(partes[2], Integer.parseInt(partes[3]), partes[4],
						Boolean.parseBoolean(partes[5]));
			case "UPDATE;VISITANTE":
				return visitanteControlador.atualizarVisitante(partes[2], Integer.parseInt(partes[3]), partes[4],
						Boolean.parseBoolean(partes[5]));
			case "GET;VISITANTE":
				return visitanteControlador.obterVisitante(partes[2]);
			case "DELETE;VISITANTE":
				return visitanteControlador.removerVisitante(partes[2]);
			case "LIST;VISITANTE":
				return visitanteControlador.listarVisitantes();

			case "INSERT;CLUBE":
				return clubeControlador.inserirClube(partes[2], Integer.parseInt(partes[3]));
			case "UPDATE;CLUBE":
				return clubeControlador.atualizarClube(Integer.parseInt(partes[2]), partes[3],
						Integer.parseInt(partes[4]));
			case "GET;CLUBE":
				return clubeControlador.obterClube(Integer.parseInt(partes[2]));
			case "DELETE;CLUBE":
				return clubeControlador.removerClube(Integer.parseInt(partes[2]));
			case "LIST;CLUBE":
				return clubeControlador.listarClubes();

			default:
				return "Operação inválida";
			}
		} catch (Exception e) {
			return "Erro: " + e.getMessage();
		}
	}
}
